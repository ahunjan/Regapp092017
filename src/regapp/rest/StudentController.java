package regapp.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import regapp.domain.Student;
import regapp.service.StudentService;

@Path("/student")
public class StudentController {
	
	@GET
	@Path("/{id}")
	public Student getStudent(@PathParam("id") int id)  {
		StudentService ss = new StudentService();
		Student student = ss.getStudent(id);
		
		return student;
	}

}
