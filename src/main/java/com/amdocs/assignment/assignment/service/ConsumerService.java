package com.amdocs.assignment.assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.amdocs.assignment.assignment.DeleteEventStream;
import com.amdocs.assignment.assignment.UpdateEventStram;
import com.amdocs.assignment.assignment.repository.ProfileServiceRepository;
import com.amdocs.assignment.assignment.vo.ProfileServiceData;

@EnableBinding(DeleteEventStream.class)
@Component
public class ConsumerService {
	@Autowired
	ProfileServiceRepository profileServiceRepository;

	@StreamListener(DeleteEventStream.INPUT)
	public void handleDelete(@Payload String user) {

		profileServiceRepository.deleteById(user);

	}

	@StreamListener(UpdateEventStram.INPUT)
	public void handleUpdate(@Payload ProfileServiceData profileData) {

		profileServiceRepository.save(profileData);

	}
}
