package com.demo.renderview.demo.events;

import com.demo.renderview.demo.repository.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {
	private User user;


	public OnRegistrationCompleteEvent(User user) {
		super(user);
		this.user = user;
	}
}
