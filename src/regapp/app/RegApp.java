package regapp.app;

import java.util.List;

import regapp.domain.Student;
import regapp.service.StudentService;

public class RegApp {

	public static void main(String[] args) {
		
		StudentService ss = new StudentService();
		
		List<Student> students = ss.getAllStudents();
		
		System.out.println("all students are " + students);
		
		Student s1 = ss.getStudent(10);
		
		if(s1 != null) {
			System.out.println("s1 is " + s1);
		}
		else {
			System.out.println("id 1 not found");
		}
		
		Student newStudent = new Student("Mary", 22);
		
		newStudent = ss.addStudent(newStudent);
		
		students = ss.getAllStudents();
		
		System.out.println("After add, list is " + students);
		
	}
}
