package com.tools.ks.aes;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping(value = "/AES")
@CrossOrigin
public class AesController
{

  @PostMapping(value = "/128")
  public ResponseEntity<?> aes128(@RequestBody String body){

    return ResponseEntity.status(422).body(ResponseSerializable.<AESDTO>builder()
        .message("Se origino un error al ejecutar la operacion indicada").object(null)
        .build()
    );

  }

}
