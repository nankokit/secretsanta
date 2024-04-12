package com.example.secretsanta.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModelDto {
  private Long id;
  private String name;
  private String email;
  private String password;
}