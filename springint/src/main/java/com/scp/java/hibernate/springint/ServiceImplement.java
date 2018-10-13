package com.scp.java.hibernate.springint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class ServiceImplement implements StudentService {
	public List<Student> studentlist = new ArrayList<Student>();
	HibernateTemplate template; 
	public static Logger logger = Logger.getLogger(ServiceImplement.class);
	static{
		logger.setLevel(Level.ALL);
	}
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addStudent(Student student) {
		Session session = this.sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.save(student);
		session.flush();
		tr.commit();
		logger.info("Student Record saved..., Details="+student);

		session.close();

	}

	public void updateStudent(Student student) throws RecordUnavailable {

		Session session = this.sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.update(student);
		session.flush();
		 logger.info("Student Record updated successfully, Student Details="+student);
		tr.commit();
		session.close();
		StudentRecordNotFound(student.getStudent_no());

	}

	public void deleteStudent(int studentID) throws RecordUnavailable, HibernateException {
		Session session=this.sessionFactory.openSession();
		Transaction tr=session.beginTransaction();
		Student stud=(Student)session.load(Student.class, studentID);
		if(null!=stud){
			session.delete(stud);
		}
		tr.commit();
		logger.info("Deleted Successfully...."+stud);
		session.close();
		StudentRecordNotFound(stud.getStudent_no());
	}

	public Student getParticular(int studdenID) throws RecordUnavailable {

		Session session = this.sessionFactory.openSession();
		Student stud = (Student) session.load(Student.class, new Integer(studdenID));
		session.close();
		// logger.info("Person loaded successfully, Person details="+p);
		StudentRecordNotFound(stud.getStudent_no());
		return stud;
	}

	@SuppressWarnings("unchecked")
	public List<Student> showAllStudents() {
		Session session = this.sessionFactory.openSession();
		List<Student> studentlist = session.createQuery("from Student").list();
		session.close();
		return studentlist;
	}

	private void StudentRecordNotFound(int student_no) throws RecordUnavailable {
		for (int i = 0; i < studentlist.size(); i++) {
			for (int j = i + 1; j < studentlist.size(); j++) {
				if (studentlist.get(i).getStudent_no() != studentlist.get(j).getStudent_no()) {
					throw new RecordUnavailable("Sorry Record Not Available!!!!");
				}
			}
		}

	}
}
