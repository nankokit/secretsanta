package com.example.secretsanta.dto.room;

import java.util.List;

import com.example.secretsanta.dto.user.UserInfoDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomInfoDTO {
    private Long id;
    private UserInfoDTO master;
    private List<UserInfoDTO> roomParticipants;
}
