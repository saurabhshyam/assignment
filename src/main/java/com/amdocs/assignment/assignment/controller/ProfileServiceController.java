package com.amdocs.assignment.assignment.controller;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.amdocs.assignment.assignment.DeleteEventStream;
import com.amdocs.assignment.assignment.UpdateEventStram;
import com.amdocs.assignment.assignment.repository.ProfileServiceRepository;
import com.amdocs.assignment.assignment.service.DeleteEventService;
import com.amdocs.assignment.assignment.service.UpdateEventService;
import com.amdocs.assignment.assignment.vo.ProfileServiceData;

@EnableBinding({ DeleteEventStream.class, UpdateEventStram.class })
@RestController
public class ProfileServiceController {
	@Autowired
	ProfileServiceRepository profileServiceRepository;
	@Autowired
	DeleteEventService deleteEventService;
	@Autowired
	UpdateEventService UpdateEventService;

	@PostMapping("/profile")
	public ProfileServiceData profile(@RequestBody ProfileServiceData profileData) {
		if (!profileServiceRepository.findById(profileData.getName()).isPresent()) {
			return profileServiceRepository.save(profileData);
		} else {
			throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "User Already Exists");
		}

	}

	@PutMapping("/profile/{user}")
	public Callable<ResponseEntity<String>> updateProfile(@PathVariable String user,
			@RequestBody ProfileServiceData profileData) {
		if (profileServiceRepository.findById(user).isPresent()) {
			UpdateEventService.sendUpdateEvent(profileData);

		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
		}
		return () -> ResponseEntity.ok("Deleted");
	}

	@DeleteMapping("/profile/{user}")
	public Callable<ResponseEntity<String>> deleteProfile(@PathVariable String user) {

		if (profileServiceRepository.findById(user).isPresent()) {
			deleteEventService.sendDeleteEvent(user);

		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
		}

		return () -> ResponseEntity.ok("Deleted");
	}
}
