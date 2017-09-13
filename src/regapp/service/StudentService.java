package regapp.service;

import java.util.ArrayList;
import java.util.List;

import regapp.dao.StudentDao;
import regapp.domain.Student;

public class StudentService {
	
	
	private StudentDao studentDao = new StudentDao();
	
	public List<Student> getAllStudents() {
		return studentDao.findAll();
	}
	
	public Student  getStudent(int id) {
		return studentDao.find(id);
	}
	
	public Student addStudent(Student s) {
		return studentDao.insert(s);
	}

	public boolean deleteStudent(int id) {
		return studentDao.remove(id);
	}
}
