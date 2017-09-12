package regapp.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import regapp.domain.Student;
import regapp.service.StudentService;

@Path("/student")
public class StudentController {
	
	@GET
	public Student getStudent(@QueryParam("id") int id)  {
		StudentService ss = new StudentService();
		Student student = ss.getStudent(id);
		
		return student;
	}

}
