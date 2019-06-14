package lux.fontys.tracking.controller;

import lux.fontys.tracking.messaging.Listener;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class Application extends javax.ws.rs.core.Application {

    @Inject
    Listener listener;

    @PostConstruct
    private void onInit(){
        listener = new Listener();
        listener.MyListener();
    }
}
