package lux.fontys.tracking.controller;

import lux.fontys.tracking.message.Receiver;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class Application extends javax.ws.rs.core.Application {
	@Inject
	Receiver r;

	@PostConstruct
	private void postInit(){
		r.GetMessages();
	}
}
