package com.scp.java.hibernate.springint;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
	public static void main(String[] args) throws InterruptedException, RecordUnavailable {
		ApplicationContext context = new ClassPathXmlApplicationContext("shbean.xml");
		StudentService sservice = (StudentService) context.getBean("studimpl");

		//add student
		Student student1 = new Student();
		student1.setStudent_no(123);
		student1.setStud_name("Rupali");
		student1.setStud_class("Ph.D");
		sservice.addStudent(student1);
		System.out.println("Student Details" + student1);
		
				
		// Update Student
		Student st1 = sservice.getParticular(123);
		System.out.println("Record Before Updation:" + st1);
		st1.setStudent_no(124);
		st1.setStud_name("Rupali");
		st1.setStud_class("M.tech");
		System.out.println("Updated Record:--" + st1);
		sservice.addStudent(st1);
		
		//Delete Record
		sservice.deleteStudent(123);
		System.out.println("Record Deleted!!!!");
		
		// Display All Records
		List<Student> studentlist = sservice.showAllStudents();
		for (Student student : studentlist) {
			System.out.println("Student List:--"+student);
		}
	}
}
