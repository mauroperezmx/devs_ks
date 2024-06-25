package com.pozzo.tpcintegration.service;

import com.pozzo.tpcintegration.client.TcpClientGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MiServicio {

  @Autowired
  private TcpClientGateway tcpClientGateway;

  public void enviarMensaje(String mensaje) {
    String respuesta = tcpClientGateway.send(mensaje);
    System.out.println("Respuesta del servidor TCP: " + respuesta);
  }
}

