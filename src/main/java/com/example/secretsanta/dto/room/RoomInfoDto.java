package com.example.secretsanta.dto.room;

import com.example.secretsanta.dto.user.UserInfoDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomInfoDto {
  private Long id;
  private UserInfoDto master;
  private List<UserInfoDto> roomParticipants;
}
