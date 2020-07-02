package org.sevod.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.sevod.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			int studentId = 1;
						
			// start a transaction			
			session.beginTransaction();
						
			//retrieve student based on the id: primary key
			//System.out.println("\nGetting student with id: " + studentId);
			
			//Student myStudent = session.get(Student.class, studentId);
			
			// delete the student
			//System.out.println("Deleting student");
			//session.delete(myStudent);
			
			// delete the student HQL			
			session.createQuery("delete from Student where id=2").executeUpdate();
									
			//commit the transaction
			session.getTransaction().commit();		
						
			System.out.println("Done!");
				
			
		} finally {
			factory.close();
		}
	}

}
