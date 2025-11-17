package umc.server.domain.mission.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.exception.code.MemberErrorCode;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.mission.converter.MemberMissionConverter;
import umc.server.domain.mission.dto.res.MemberMissionResDTO;
import umc.server.domain.mission.entity.MemberMission;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.exception.MissionException;
import umc.server.domain.mission.exception.code.MissionErrorCode;
import umc.server.domain.mission.repository.MemberMissionRepository;
import umc.server.domain.mission.repository.MissionRepository;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRespository;

    // 가게의 미션을 도전 중인 미션에 추가
    @Override
    @Transactional
    public MemberMissionResDTO.ChallengeResponse challengeMission(Long memberId, Long missionId) {
        // Member, Mission 조회 (없으면 예외)
        Member member = memberRespository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        Mission mission = missionRepository.findById(memberId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND));

        // MemberMission 엔티티 생성
        MemberMission memberMission = MemberMissionConverter.toMemberMission(member, mission);

        // 저장
        MemberMission saved = memberMissionRepository.save(memberMission);

        // DTO 변환 후 반환
        return MemberMissionConverter.toChallengeResponseDTO(saved);
    }
}
