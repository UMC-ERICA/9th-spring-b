package umc.server.domain.mission.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.exception.code.MemberErrorCode;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.mission.converter.MemberMissionConverter;
import umc.server.domain.mission.dto.res.MemberMissionResDTO;
import umc.server.domain.mission.entity.MemberMission;
import umc.server.domain.mission.enums.MissionStatus;
import umc.server.domain.mission.repository.MemberMissionRepository;

@Service
@RequiredArgsConstructor
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<MemberMission> getMemberMissions(Long memberId, MissionStatus status, Pageable pageable) {
        return memberMissionRepository.findPageByMemberIdAndStatus(memberId, status, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public MemberMissionResDTO.MyMissionPreViewListDTO findMyOngoingMissions(Long memberId, Integer page) {
        // 회원을 가져온다 (회원 존재 여부 검증)
        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 회원에 맞는 미션을 가져온다 (Offset 페이징)
        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        Page<MemberMission> result = memberMissionRepository.findPageByMemberIdAndStatus(memberId, MissionStatus.IN_PROGRESS, pageRequest);

        // 결과를 응답 DTO로 변환한다 (컨버터 이용)
        return MemberMissionConverter.toMyMissionPreViewListDTO(result);
    }
}
