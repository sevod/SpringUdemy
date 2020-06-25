package org.sevod.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.sevod.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		
		// create session factory
				SessionFactory factory = new Configuration()
						.configure("hibernate.cfg.xml")
						.addAnnotatedClass(Student.class)
						.buildSessionFactory();
				
				// create session
				Session session = factory.getCurrentSession();
				
				try {
					// use the session object to save Java object
					System.out.println("Creating 3 students objects... ");
					Student tempStudent1 = new Student("John", "Doe", "John@sevod.org");
					Student tempStudent2 = new Student("Mary", "Public", "Mary@sevod.org");
					Student tempStudent3 = new Student("Bonita", "Applebaum", "Bonita@sevod.org");
					
					// start a transaction		
					session.beginTransaction();
					
					// save the student object
					System.out.println("Saving the student...");
					session.save(tempStudent1);
					session.save(tempStudent2);
					session.save(tempStudent3);
					 
					// commit transaction
					session.getTransaction().commit();
					
					System.out.println("Done!");
						
					
				} finally {
					factory.close();
				}
			

	}

}
