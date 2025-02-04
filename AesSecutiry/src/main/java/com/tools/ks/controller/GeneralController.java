package com.tools.ks.controller;

import com.tools.ks.aes.AESDTO;
import com.tools.ks.aes.ResponseSerializable;
import com.tools.ks.encryptation.EncryptationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping(value = "/tools")
@CrossOrigin
public class GeneralController
{

  @Autowired
  private EncryptationService encryptationService;

  @PostMapping(value = "/128")
  public ResponseEntity<?> aes128(@RequestBody String body){

    return ResponseEntity.status(422).body(ResponseSerializable.<AESDTO>builder()
        .message("Se origino un error al ejecutar la operacion indicada").object(null)
        .build()
    );
  }

  @PostMapping(value = "/sha256")
  public ResponseEntity<?> sha256(@RequestBody String body)
  {
    log.info("Message: {}", body);
    log.info("Message 1: {}", encryptationService.encryptsha512(body));
    log.info("Message 2: {}", encryptationService.encrypt2(body));
    return ResponseEntity.status(200).body(ResponseSerializable.<AESDTO>builder()
        .message(encryptationService.encrypt2(body)).object(null)
        .build()
    );

  }
}
