package lux.fontys.tracking.messaging;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lux.fontys.tracking.facade.LocationFacade;
import lux.fontys.tracking.messaging.model.TripMessage;
import lux.fontys.tracking.model.Trip;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class Listener {

    private final String hostName = "84.24.202.19";
    private final String queueName = "TrackingQueue2";
    private List<TripMessage> tripMessageList;
    @Inject
    LocationFacade locationFacade;

    @PostConstruct
    public void init() {
        if (tripMessageList == null) tripMessageList = new ArrayList<>();
        if (locationFacade == null) {
            System.out.println("NOOOOO");
        }
        (new Thread(() -> {
            while (true) {
                if(tripMessageList.size()==0)System.out.println("NOOOOOOOOOOOOOO!");
                tripMessageList.forEach(o -> {
                    locationFacade.saveFromMessaging(o);
                    tripMessageList.remove(o);
                });
            }
        })).start();
    }

    public void MyListener() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(hostName);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(queueName, true, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                try {
                    saveTrip(new String(delivery.getBody(), StandardCharsets.UTF_8));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private void saveTrip(String json) {
        TripMessage tm = new Gson().fromJson(json, TripMessage.class);
        tripMessageList.add(tm);
    }

}

