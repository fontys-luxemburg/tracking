package lux.fontys.tracking.messaging;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lux.fontys.tracking.facade.LocationFacade;
import lux.fontys.tracking.messaging.model.TripMessage;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.nio.charset.StandardCharsets;

@RequestScoped
public class Listener {

    private final String hostName = "localhost";
    private final String queueName = "TrackingQueue";

    @Inject
    LocationFacade locationFacade;

    public void MyListener() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(hostName);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(queueName, false, false, true, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                try {
                    Gson g = new Gson();
                    String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                    System.out.println(" [x] Received '" + message + "'");

                    TripMessage tm = g.fromJson(message, TripMessage.class);
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
