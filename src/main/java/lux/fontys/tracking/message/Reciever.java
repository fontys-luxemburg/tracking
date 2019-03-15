package lux.fontys.tracking.message;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Reciever {

	private final String host = "localhost";
	private final String queue = "TrackingQueue";

	public void GetMessages() {
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(host);
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			channel.queueDeclare(queue, false, false, false, null);

			DeliverCallback deliverCallback = (consumerTag, delivery) -> {
				String message = new String(delivery.getBody(), "UTF-8");
				System.out.println(" [x] Received '" + message + "'");
			};
			channel.basicConsume(queue, true, deliverCallback, consumerTag -> {
			});
			System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
