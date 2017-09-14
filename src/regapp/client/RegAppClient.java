package regapp.client;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
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
		studentsResource = root.path("/student");
		oneStudentResource = studentsResource.path("{id}");
	}

	public void getStudent(int id) {

		ResultObject result = oneStudentResource
				.resolveTemplate("id", id)
				.request(MediaType.APPLICATION_JSON)
				.get(ResultObject.class);

		if (result.getError() != null) {
			System.out.println("Got error: " + result.getError());
		} else {
			System.out.println("student is " + result.getResult());
		}
	}

	public Student addStudent(Student s) {
		Response response = studentsResource.request("application/json").post(Entity.entity(s, "application/xml"));

		if (response.getStatus() >= 400) {
			throw new WebApplicationException(response);
		}
		ResultObject result = response.readEntity(ResultObject.class);
		if (result.getError() != null) {
			throw new WebApplicationException(result.getError());
		}

		System.out.println("student is " + result.getResult());

		response.close();

		return (Student) result.getResult();
	}

	public static void main(String[] args) {
		RegAppClient clientStarter = new RegAppClient();
		clientStarter.getStudent(1);
		
		Student student = new Student("Dhario", 55);
		
		student = clientStarter.addStudent(student);
	}

}
