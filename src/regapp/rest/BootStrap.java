package regapp.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

//@ApplicationPath("/rest")
public class BootStrap extends Application{

	@Override
	public Set<Class<?>> getClasses() {

		Set<Class<?>> classes = new HashSet<>();
		
		classes.add(AccountController.class);
		
		return classes;
	}

	@Override
	public Set<Object> getSingletons() {

		Set<Object> clases = new HashSet<>();
		
		StudentController sc = new StudentController();
		System.out.println("In bootratp sc hash = " + sc.hashCode());
		clases.add(sc);
		
		return clases;
	}
}
