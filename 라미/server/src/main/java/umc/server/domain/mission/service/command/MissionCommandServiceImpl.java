package umc.server.domain.mission.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.entity.mapping.MemberMission;
import umc.server.domain.member.exception.MemberMissionException;
import umc.server.domain.member.exception.code.MemberMissionErrorCode;
import umc.server.domain.member.repository.MemberMissionRepository;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.mission.converter.MemberMissionConverter;
import umc.server.domain.mission.converter.MissionConverter;
import umc.server.domain.mission.dto.req.MissionReqDTO;
import umc.server.domain.mission.dto.res.MissionResDTO;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.enums.MissionStatus;
import umc.server.domain.mission.repository.MissionRepository;
import umc.server.domain.store.entity.Store;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionCommandServiceImpl implements MissionCommandService {
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    // 미션 도전하기
    @Override
    public MemberMission challengeMission(Long storeId, MissionReqDTO.ChallengeDTO request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new IllegalArgumentException("미션을 찾을 수 없습니다."));

        // Mission이 해당 Store의 미션인지 확인
        if (!mission.getStore().getId().equals(storeId)) {
            throw new IllegalArgumentException("해당 가게의 미션이 아닙니다.");
        }

        // Store 조회 (Mission에서 가져오기)
        Store store = mission.getStore();

        // 이미 도전 중인지 확인 (중복 방지)
        // TODO: memberMissionRepository에 쿼리 메서드 추가

        // MemberMission 생성
        MemberMission memberMission = MissionConverter.toMemberMission(member, mission, store);

        return memberMissionRepository.save(memberMission);
    }

    @Override
    public MissionResDTO.CompletedMissionDTO completeMission(Long memberMissionId) {
        // MemberMission 존재 확인
        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new MemberMissionException(MemberMissionErrorCode.NOT_FOUND));

        if (memberMission.getStatus() != MissionStatus.IN_PROGRESS) {
            throw new MemberMissionException(MemberMissionErrorCode.ALREADY_COMPLETED);
        }
        // 상태 변경
        memberMission.complete();
        return MemberMissionConverter.toCompletedMissionDTO(memberMission);
    }

}
