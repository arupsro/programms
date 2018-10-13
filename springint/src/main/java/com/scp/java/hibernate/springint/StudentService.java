package com.scp.java.hibernate.springint;

import java.util.List;

public interface StudentService {

	public void addStudent(Student student);

	public void updateStudent(Student student) throws RecordUnavailable;

	public void deleteStudent(int studentID) throws RecordUnavailable;

	public Student getParticular(int studdenID) throws RecordUnavailable;

	public List<Student> showAllStudents();

}
