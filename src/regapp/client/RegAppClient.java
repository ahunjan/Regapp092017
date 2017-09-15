package regapp.client;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import regapp.domain.ResultObject;
import regapp.domain.Student;

public class RegAppClient {

	private String targetUrl;
	private ClientConfig clientConfig;
	private Client client;
	private WebTarget root;
	private WebTarget studentsResource;
	private WebTarget oneStudentResource;

	public RegAppClient() {

		targetUrl = "http://localhost:8080/regapp";
		clientConfig = new ClientConfig();
		clientConfig.register(JacksonJsonProvider.class);

		client = ClientBuilder.newClient(clientConfig);

		root = client.target(targetUrl);
		studentsResource = root.path("student");
		oneStudentResource = studentsResource.path("{id}");
	}

	public void getStudent(int id) {

		GenericType<ResultObject<Student>> gt =
				new GenericType<ResultObject<Student>>() {
		};
		ResultObject<Student> result = oneStudentResource
				.resolveTemplate("id", id)
				.request(MediaType.APPLICATION_JSON)
				.get(gt);

		if (result.getError() != null) {
			System.out.println("Got error: " + result.getError());
		} else {
			System.out.println("student is " + result.getResult());
		}
	}

	public Student addStudent(Student s) {
		Response response = studentsResource
				.request("application/json")
				.post(Entity.entity(s, "application/xml"));

		if (response.getStatus() >= 400) {
			throw new WebApplicationException(response);
		}

		GenericType<ResultObject<Student>> gt =
				new GenericType<ResultObject<Student>>() {
		};
				
		ResultObject<Student> result = response.readEntity(gt);
		if (result.getError() != null) {
			throw new WebApplicationException(result.getError());
			
		}
		
		Student student = result.getResult();
		System.out.println("student is " + student);

		response.close();

		return student;

	}

	public static void main(String[] args) {
		RegAppClient clientStarter = new RegAppClient();
		clientStarter.getStudent(1);

		Student student = new Student("Dhario", 55);

		student = clientStarter.addStudent(student);
	}

}
