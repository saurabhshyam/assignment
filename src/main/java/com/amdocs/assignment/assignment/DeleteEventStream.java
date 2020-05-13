package com.amdocs.assignment.assignment;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface DeleteEventStream {
	 String INPUT = "delete-in";
	    
	    @Input(INPUT)
	    SubscribableChannel deleteChannel();
	    
}
