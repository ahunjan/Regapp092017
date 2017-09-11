package regapp.dao;

import java.util.ArrayList;
import java.util.List;

import regapp.domain.Student;

public class StudentDao {
	private List<Student> students = new ArrayList<>();

	private static int nextId = 0;

	public StudentDao() {
		Student s1 = new Student("Rachna", 43);
		Student s2 = new Student("John", 50);
		Student s3 = new Student("Robert", 22);
		
		insert(s1);
		insert(s2);
		insert(s3);
	}
	
	public Student insert(Student s) {
		int id = nextId++;
		s.setId(id);

		students.add(s);
		return s;
	}

	public Student find(int id) {
		
		for(Student student : students) {
			if(student.getId() == id) {
				return student;
			}
		}
		
		return null;
	}

	public List<Student> findAll() {
		return students;
	}
}
