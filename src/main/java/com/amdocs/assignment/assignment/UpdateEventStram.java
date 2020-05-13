package com.amdocs.assignment.assignment;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface UpdateEventStram {
	String INPUT = "update-in";
	@Input(INPUT)
	SubscribableChannel updateChannel();
}
