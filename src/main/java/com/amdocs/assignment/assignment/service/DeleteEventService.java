package com.amdocs.assignment.assignment.service;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.amdocs.assignment.assignment.DeleteEventStream;

@Service
public class DeleteEventService {
	private final DeleteEventStream deleteEventStream;

	public DeleteEventService(DeleteEventStream deleteStream) {

		this.deleteEventStream = deleteStream;
	}
	  public void sendDeleteEvent(final String user) {
	        
	        MessageChannel messageChannel = deleteEventStream.deleteChannel();
	        messageChannel.send(MessageBuilder
	                .withPayload(user)
	                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
	                .build());
	    }
}
