package lux.fontys.tracking.message;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.nio.charset.StandardCharsets;

@ApplicationScoped
public class Receiver {

	private final String host = "localhost";
	private final String queue = "TrackingQueue";

	@Inject
	Converter c;
	public void GetMessages() {
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(host);
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			channel.queueDeclare(queue, false, false, false, null);

			DeliverCallback deliverCallback = (consumerTag, delivery) -> {
				String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
				c.toTracker(message);
			};
			channel.basicConsume(queue, true, deliverCallback, consumerTag -> {
			});
			System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
