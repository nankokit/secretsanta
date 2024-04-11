package com.example.secretsanta.dto.form;

import com.example.secretsanta.dto.user.UserNameDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormPairDto {
  private UserNameDto santa;
  private UserNameDto receiver;
}
