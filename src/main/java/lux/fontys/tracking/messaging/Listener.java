package lux.fontys.tracking.messaging;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lux.fontys.tracking.facade.LocationFacade;
import lux.fontys.tracking.messaging.model.TripMessage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.nio.charset.StandardCharsets;

@ApplicationScoped
public class Listener {

    private final String hostName = "84.24.202.19";
    private final String queueName = "TrackingQueue2";

    @Inject
    LocationFacade locationFacade;

    public void MyListener() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(hostName);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(queueName, true, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                try {
                    String json = new String(delivery.getBody(), StandardCharsets.UTF_8);
                    TripMessage tm = new Gson().fromJson(json, TripMessage.class);
                    locationFacade.saveFromMessaging(tm);
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
}

