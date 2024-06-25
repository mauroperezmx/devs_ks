package com.pozzo.tpcintegration;

import com.pozzo.tpcintegration.service.MiServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TpcClientApplication implements CommandLineRunner
{

	@Autowired
	private MiServicio miServicio;

	public static void main(String[] args) {
		SpringApplication.run(TpcClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Llamar a miServicio.enviarMensaje con el mensaje que desees enviar
		miServicio.enviarMensaje("Hola, servidor TCP!");
		miServicio.enviarMensaje("Yo de nuevo, servidor TCP!");
	}

}
