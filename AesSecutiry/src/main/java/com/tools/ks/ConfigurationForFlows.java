package com.tools.ks;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class ConfigurationForFlows
{
  //Sha512
  @Value("${services.sha512.semilla}")
  private String semilla;

}
