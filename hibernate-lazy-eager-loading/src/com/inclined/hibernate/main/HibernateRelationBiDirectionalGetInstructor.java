package com.inclined.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.inclined.hibernate.table.relations.Course;
import com.inclined.hibernate.table.relations.Instructor;
import com.inclined.hibernate.table.relations.InstructorDetail;

public class HibernateRelationBiDirectionalGetInstructor {

	public static void main(String[] args) {
			
		// Session Start
		// Session, SessionFactory implements AutoClosable()
		// try with resources
		try(SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
				Session session = factory.getCurrentSession();)
		{
			session.beginTransaction();
			
			// below line will load instructor, instDetail - related courses and it's related instructor
			// Can be the reason of slow application
			// Only benefit is only 1 time db call, rest all code is just showing the data.
			Instructor instructor = session.get(Instructor.class, 7);
			System.out.println("XXXXX:: Instructor"+ instructor);
			
			System.out.println("XXXXX:: Courses"+ instructor.getCourse());

			// commit Transaction
			session.getTransaction().commit();	
		
			session.close();
			
			// works because lazy data already loaded into instructor object !!
			System.out.println("!!!!!:: After Closing Courses"+ instructor.getCourse());

		}
		// Always close the session (session.close), factory.close() won't close the session ( if finally is used )
		// , As to prevent leaks. And keep application running ! 
		
	}

}
