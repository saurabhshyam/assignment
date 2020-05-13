package com.amdocs.assignment.assignment.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import com.amdocs.assignment.assignment.repository.ProfileServiceRepository;
import com.amdocs.assignment.assignment.service.DeleteEventService;
import com.amdocs.assignment.assignment.service.UpdateEventService;
import com.amdocs.assignment.assignment.vo.ProfileServiceData;

@SpringBootTest
public class ProfileServiceControllerTest {
	@Mock
	ProfileServiceRepository profileServiceRepository;
	@Mock
	DeleteEventService deleteEventService;
	@Mock
	UpdateEventService UpdateEventService;
	@Mock
	ProfileServiceData profileServiceData;
	ProfileServiceController testObj;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		testObj = new ProfileServiceController();
		testObj.profileServiceRepository = profileServiceRepository;
		testObj.deleteEventService = deleteEventService;
		testObj.UpdateEventService = UpdateEventService;
		when(profileServiceRepository.save(anyObject())).thenReturn(Optional.of(profileServiceData).get());

	}

	@Test(expected = ResponseStatusException.class)
	public void testCreateProfile() {
		when(profileServiceRepository.findById(anyString())).thenReturn(Optional.of(profileServiceData));
		testObj.profile(profileServiceData);

	}

	@Test
	public void testCreateNewProfile() {
		when(profileServiceRepository.findById(anyString())).thenReturn(Optional.empty());
		ProfileServiceData info = testObj.profile(profileServiceData);
		assertNotNull(info);
	}

	@Test(expected = ResponseStatusException.class)
	public void testDeleteNoProfile() {
		when(profileServiceRepository.findById(anyString())).thenReturn(Optional.empty());
		testObj.deleteProfile(anyString());

	}

	@Test
	public void testDeleteProfile() {
		when(profileServiceRepository.findById(anyString())).thenReturn(Optional.of(profileServiceData));
		assertNotNull(testObj.deleteProfile(anyString()));

	}

	@Test(expected = ResponseStatusException.class)
	public void testUpdateNoProfile() {
		when(profileServiceRepository.findById("user")).thenReturn(Optional.empty());
		testObj.updateProfile("user", anyObject());

	}

	@Test
	public void testUpdateProfile() {
		when(profileServiceRepository.findById("user")).thenReturn(Optional.of(profileServiceData));
		assertNotNull(testObj.updateProfile("user", anyObject()));

	}

}
