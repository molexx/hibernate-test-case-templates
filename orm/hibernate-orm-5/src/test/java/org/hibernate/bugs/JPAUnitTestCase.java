package org.hibernate.bugs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.bugs.model.Parent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	// Entities are auto-discovered, so just add them anywhere on class-path
	// Add your tests, using standard JUnit.
	@Test
	public void hhh123Test() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		
		System.out.println("creating new parent");
		Parent detached = new Parent();
		detached.id = 1L;
		
		detached.parentProperty = "prop1 value";
		
		
		System.out.println("merging detached parent...: " + detached);
		/*
		 Look at the SQL logged to stdout during the merge below.
		 It first does a SELECT, but why does that SELECT include a left outer join to setA?
		 Whilst there is a cascade it is lazy, and setA has not been set in the detached Object.
		 
		 And then why does it not also outer join to setB?
		 */
		entityManager.merge(detached);
		System.out.println("merge done, formerly detached parent now: " + detached);
		
		System.out.println("committing...");
		
		
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
