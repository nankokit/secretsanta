package com.example.secretsanta.dto.form;

import com.example.secretsanta.dto.user.UserNameDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormPairDTO {
    private UserNameDTO santa;
    private UserNameDTO receiver;
}
