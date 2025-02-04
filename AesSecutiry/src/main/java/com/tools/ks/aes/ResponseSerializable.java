package com.tools.ks.aes;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseSerializable<T> implements Serializable
{

  private static final long serialVersionUID = 1L;
  private String message;
  private T object;
  private List<T> objects;
}
