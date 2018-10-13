package com.scp.java.hibernate.springint;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
	@Id
	private int student_no;
	@Column(name="s_name")
	private String stud_name;
	@Column(name="s_class")
	private String stud_class;

	/*public Student() {
		super();
	}*/

	
	public Student() {
		// TODO Auto-generated constructor stub
	}

	public int getStudent_no() {
		return student_no;
	}

	public void setStudent_no(int student_no) {
		this.student_no = student_no;
	}

	public String getStud_name() {
		return stud_name;
	}

	public void setStud_name(String stud_name) {
		this.stud_name = stud_name;
	}

	public String getStud_class() {
		return stud_class;
	}

	public void setStud_class(String stud_class) {
		this.stud_class = stud_class;
	}

	@Override
	public String toString() {
		return "\n Student [student_no=" + student_no + ", stud_name=" + stud_name + ", stud_class=" + stud_class + "]";
	}

}
