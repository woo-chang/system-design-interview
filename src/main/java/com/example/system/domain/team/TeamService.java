package com.example.system.domain.team;

import com.example.system.domain.team.dto.request.TeamCreateRequest;
import com.example.system.domain.team.dto.request.TeamUpdateRequest;
import com.example.system.domain.team.dto.response.TeamResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Transactional
    public Long createTeam(TeamCreateRequest request) {
        Team team = Team.builder()
                .name(request.getName())
                .build();
        Team savedTeam = teamRepository.save(team);
        return savedTeam.getId();
    }

    public List<TeamResponse> findAll() {
        return teamRepository.findAll()
                .stream()
                .map(TeamResponse::from)
                .toList();
    }

    @Transactional
    public void updateTeam(Long teamId, TeamUpdateRequest request) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 팀입니다."));
        team.update(request.getName());
    }

    @Transactional
    public void deleteMemberById(Long teamId) {
        teamRepository.deleteById(teamId);
    }
}
