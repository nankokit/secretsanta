package com.example.secretsanta.dto.room;

import com.example.secretsanta.dto.user.UserInfoDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomParticipantsDto {
  private List<UserInfoDto> roomParticipants;
}
