package lux.fontys.tracking.messaging;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lux.fontys.tracking.facade.LocationFacade;
import lux.fontys.tracking.messaging.model.Trip_Message;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@ApplicationScoped
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

			channel.queueDeclare(queueName, false, false, false, null);
			DeliverCallback deliverCallback = (consumerTag, delivery) -> {
				try {
					String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
					System.out.println(" [x] Received '" + message + "'");
					Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
					Trip_Message trip_message = new Trip_Message();
					trip_message.setLatitude("ala");
					trip_message.setLongitude("lol");
					trip_message.setTrackedAt("lla");
					trip_message.setTripID("lalala");
					trip_message.setTrackerID("akkakaka");
					String message2 = g.toJson(trip_message);
					Trip_Message tm = g.fromJson(message, Trip_Message.class);
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
