package com.pozzo.tpcintegration.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.ip.dsl.Tcp;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNioServerConnectionFactory;
import org.springframework.integration.ip.tcp.serializer.ByteArrayCrLfSerializer;

@Configuration
@EnableIntegration
public class TcpServerConfig {

	@Value(value = "${tcp.server.port}")
	private int tcpServerPort;

	@Bean
	public IntegrationFlow commandServerFlow() {
		return IntegrationFlows.from(Tcp.inboundGateway(serverConnectionFactory()))
				.handle((payload, headers) -> {
					String message = new String((byte[]) payload);
					System.out.println("Message received: " + message);

					// Aquí puedes implementar lógica para generar una respuesta automática
					String response = generateAutomaticResponse(message);

					return response;
				})
				.get();
	}

	// Método para generar una respuesta automática basada en el mensaje recibido
	private String generateAutomaticResponse(String message) {
		// Aquí puedes implementar la lógica para generar la respuesta automática
		// Por ejemplo, puedes devolver un mensaje predefinido o basado en la entrada recibida
		return "Automated Response to: " + message;
	}

	public AbstractServerConnectionFactory serverConnectionFactory() {
		TcpNioServerConnectionFactory tcpNetServerConnectionFactory = new TcpNioServerConnectionFactory(tcpServerPort);
		// socket will be used once
		tcpNetServerConnectionFactory.setSingleUse(true);
		tcpNetServerConnectionFactory.setSerializer(codec());
		tcpNetServerConnectionFactory.setDeserializer(codec());
		return tcpNetServerConnectionFactory;
	}

	public ByteArrayCrLfSerializer codec() {
		ByteArrayCrLfSerializer crLfSerializer = new ByteArrayCrLfSerializer();
		crLfSerializer.setMaxMessageSize(204800000);
		return crLfSerializer;
	}
}