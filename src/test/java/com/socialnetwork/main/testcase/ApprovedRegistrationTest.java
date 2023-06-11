package com.socialnetwork.main.testcase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.socialnetwork.main.model.ApprovedRegistration;

public class ApprovedRegistrationTest {
	 @Test
	    public void testGetLastName() {
	        ApprovedRegistration registration = new ApprovedRegistration();
	        registration.setLastName("Doe");
	        assertEquals("Doe", registration.getLastName());
	    }

	    @Test
	    public void testGetAge() {
	        ApprovedRegistration registration = new ApprovedRegistration();
	        registration.setAge(25);
	        assertEquals(25, registration.getAge());
	    }
	    @Test
	    public void testSetAge() {
	        ApprovedRegistration registration = new ApprovedRegistration();
	        registration.setAge(30);
	        assertEquals(30, registration.getAge());
	    }

	    @Test
	    public void testGetPassword() {
	        ApprovedRegistration registration = new ApprovedRegistration();
	        registration.setPassword("secretpassword");
	        assertEquals("secretpassword", registration.getPassword());
	    }
}
