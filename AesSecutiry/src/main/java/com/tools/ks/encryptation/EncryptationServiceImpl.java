package com.tools.ks.encryptation;

import com.tools.ks.ConfigurationForFlows;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@NoArgsConstructor
public class EncryptationServiceImpl implements EncryptationService
{
  @Autowired
  private ConfigurationForFlows configurationForFlows;

  private byte[] salt;
  public String encryptsha512(String message) {
    try {
      salt = configurationForFlows.getSemilla().getBytes(StandardCharsets.UTF_8);
      // Crear instancia de MessageDigest para SHA-512
      MessageDigest digest = MessageDigest.getInstance("SHA-512");
      // Combinar la semilla con el mensaje
      byte[] messageBytes = message.getBytes();
      byte[] messageWithSalt = new byte[salt.length + messageBytes.length];
      // Copiar semilla y mensaje al nuevo array
      System.arraycopy(salt, 0, messageWithSalt, 0, salt.length);
      System.arraycopy(messageBytes, 0, messageWithSalt, salt.length, messageBytes.length);
      // Calcular el hash
      byte[] hash = digest.digest(messageWithSalt);
      // Convertir a formato hexadecimal
      StringBuilder hexString = new StringBuilder();
      for (byte b : hash) {
        String hex = Integer.toHexString(0xff & b);
        if (hex.length() == 1) hexString.append('0');
        hexString.append(hex);
      }
      return hexString.toString();
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("Error al calcular el hash SHA-512", e);
    }
  }

  @Override
  public String encrypt2(String message)
  {
    try
    {
      // Crear una instancia de Mac con HMAC-SHA512
      Mac sha512Hmac = Mac.getInstance("HmacSHA512");

      // Convertir la clave secreta a bytes
      SecretKeySpec secretKeySpec = new SecretKeySpec(configurationForFlows.getSemilla().getBytes(StandardCharsets.UTF_8), "HmacSHA512");

      // Inicializar el Mac con la clave secreta
      sha512Hmac.init(secretKeySpec);

      // Calcular el hash con la clave secreta y el mensaje
      byte[] hmacBytes = sha512Hmac.doFinal(message.getBytes(StandardCharsets.UTF_8));

      // Convertir el hash a una cadena hexadecimal
      return bytesToHex(hmacBytes);
    }
    catch (NoSuchAlgorithmException e)
    {
      log.error("ERROR: No se pudo encontrar el algoritmo HmacSHA512 / {}", e);
    }
    catch (InvalidKeyException e)
    {
      log.error("ERROR: Clave secreta inválida para HmacSHA512 / {}", e);
    }
    return "";
  }

  // Método para convertir el array de bytes a un String hexadecimal
  private String bytesToHex(byte[] bytes)
  {
    StringBuilder hexString = new StringBuilder();
    for (byte b : bytes)
    {
      String hex = Integer.toHexString(0xff & b);
      if (hex.length() == 1)
      {
        hexString.append('0');
      }
      hexString.append(hex);
    }
    return hexString.toString();
  }
}
