package com.amdocs.assignment.assignment.service;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.amdocs.assignment.assignment.UpdateEventStram;
import com.amdocs.assignment.assignment.vo.ProfileServiceData;

@Service
public class UpdateEventService {
	private final UpdateEventStram updateEventStream;

	public UpdateEventService(UpdateEventStram updateStream) {

		this.updateEventStream = updateStream;
	}

	public void sendUpdateEvent(ProfileServiceData profileData) {
		MessageChannel channel=updateEventStream.updateChannel();
		channel.send(MessageBuilder
                .withPayload(profileData)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
  
    }
}
