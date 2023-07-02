package com.example.system.domain.member;

import com.example.system.domain.member.dto.request.MemberCreateRequest;
import com.example.system.domain.member.dto.request.MemberUpdateRequest;
import com.example.system.domain.member.dto.response.MemberResponse;
import com.example.system.domain.team.Team;
import com.example.system.domain.team.TeamRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    @Transactional
    public Long createMember(MemberCreateRequest request) {
        Team team = teamRepository.findById(request.getTeamId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 팀입니다."));
        Member member = Member.builder()
                .team(team)
                .name(request.getName())
                .age(request.getAge())
                .sex(request.getSex())
                .profileImage(request.getProfileImage())
                .build();
        Member savedMember = memberRepository.save(member);
        return savedMember.getId();
    }

    public List<MemberResponse> findAll() {
        return memberRepository.findAll()
                .stream()
                .map(MemberResponse::from)
                .toList();
    }

    @Transactional
    public void updateMember(Long memberId, MemberUpdateRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Team team = teamRepository.findById(request.getTeamId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 팀입니다."));
        member.update(
                team,
                request.getName(),
                request.getAge(),
                request.getSex(),
                request.getProfileImage()
        );
    }

    @Transactional
    public void deleteMemberById(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
