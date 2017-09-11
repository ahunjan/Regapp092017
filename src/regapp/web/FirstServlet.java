package regapp.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import regapp.domain.Student;
import regapp.service.StudentService;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {

	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		//Get Raw Data
		String idStr = request.getParameter("id");
		
		//Convert and validate
		int id = -1;
		try {
			id = Integer.parseInt(idStr);
		}
		catch(NumberFormatException e) {
			//conversion error
			out.println("Conversion error: id must be an integer " + idStr);
			return;
		}
		
		
		StudentService ss = new StudentService();;
		Student st = ss.getStudent(id);
		
		out.println("Found student: " + st);
		
		out.close();
	}
	
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		//Get Raw Data
		String ageStr = request.getParameter("age");
		
		//Convert and validate
		int age = -1;
		try {
			age = Integer.parseInt(ageStr);
		}
		catch(NumberFormatException e) {
			return;
		}
		
		String name = request.getParameter("name");
		
		Student student = new Student(name, age);
		StudentService ss = new StudentService();

		student = ss.addStudent(student);

		PrintWriter out = response.getWriter();
		out.println("added student is " + student);
		
		out.close();
		
	}
	
}
