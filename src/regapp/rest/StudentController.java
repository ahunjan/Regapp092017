package regapp.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import regapp.domain.ResultObject;
import regapp.domain.Student;
import regapp.service.StudentService;

@Path("/student")
public class StudentController {
	
	@GET
	@Path("/{id}")
	public Response getStudent(@PathParam("id") int id)  {
		StudentService ss = new StudentService();
		Student student = ss.getStudent(id);
		
		ResultObject ro = new ResultObject();
		if(student != null) {
			ro.setResult(student);
			ro.setAppStatus(200);
		}
		else {
			ro.setAppStatus(897);
			ro.setError("Id " + id + " not found");
		}
		
		return Response.ok().entity(ro).build();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Student> getAllStudents() {
		StudentService ss = new StudentService();
		
		return ss.getAllStudents();
	}
	
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response addStudent(Student student) {
		
		StudentService ss = new StudentService();
		Student newStudent = ss.addStudent(student);
		
		return Response.status(Status.CREATED).entity(newStudent).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteStudent(@PathParam("id") int id) {
		
		StudentService ss = new StudentService();
		boolean result = ss.deleteStudent(id);
		ResultObject ro = new ResultObject();
		if(result) {
			ro.setAppStatus(200);
		}
		else {
			ro.setAppStatus(897);
			ro.setError("Id " + id + "not found");
		}
		return Response.ok().entity(ro).build();
	}
}
