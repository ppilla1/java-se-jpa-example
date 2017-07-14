package org.disknotfound.jpa.app;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.disknotfound.jpa.app.entity.Address;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestAddress {
	private static final Logger LOG = LoggerFactory.getLogger(TestAddress.class);
	
	private static EntityManager em;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		em = PersistenceManager.INSTANCE.getEntityManager();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		em.close();
		PersistenceManager.INSTANCE.close();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testSelectUpdate() {
		em.getTransaction().begin();
		Address address = em.find(Address.class, 0); // Get Address
		LOG.info("{}",address);
		assertNotNull(address);
		address.setProvince("unknown"); // Update Address
		em.getTransaction().commit(); // Save Address
		address = em.find(Address.class, 0); // Get Address
		LOG.info("{}",address);
		assertTrue(address.getProvince().equals("unknown"));
	}

	@Test
	public void testSelectInsertSelect(){
		Address address1 = new Address();
		address1.setCity("Bangalore")
		   .setCountry("India")
		   .setPostcode("560037")
		   .setProvince("Karnataka")
		   .setStreet("Kundallahalli Colony");

		em.getTransaction().begin();
		Address address = em.find(Address.class, 0); // Get Address
		LOG.info("{}",address);
		//assertTrue(address.getProvince().equals("unknown"));

		em.persist(address1);
		em.getTransaction().commit(); // Save Address
		address = em.find(Address.class, 1); // Get Address
		LOG.info("{}",address);
		assertNotNull(address);
	}
}
