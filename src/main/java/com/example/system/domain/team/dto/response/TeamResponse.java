package com.example.system.domain.team.dto.response;

import com.example.system.domain.team.Team;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamResponse {

    private Long teamId;
    private String name;
    private LocalDateTime createdAt;

    public static TeamResponse from(Team team) {
        return new TeamResponse(team.getId(), team.getName(), team.getCreatedAt());
    }
}
