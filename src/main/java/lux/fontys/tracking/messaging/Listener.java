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
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
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

			channel.queueDeclare(queueName, false, false, false, null);
			DeliverCallback deliverCallback = (consumerTag, delivery) -> {
				try {
					Gson g = new Gson();
					String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
					System.out.println(" [x] Received '" + message + "'");

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
