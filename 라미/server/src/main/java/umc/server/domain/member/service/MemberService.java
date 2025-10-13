package umc.server.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.dto.ChallengingMissionDto;
import umc.server.domain.member.dto.MyMissionDto;
import umc.server.domain.member.dto.MyPageDto;
import umc.server.domain.member.repository.MemberMissionRepository;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.mission.MissionStatus;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;


    public MyPageDto getMyPageInfo(Long memberId) {
        MyPageDto myPageInfo = memberRepository.findMyPageInfoById(memberId);

        if (myPageInfo == null) {
            throw new IllegalArgumentException("사용자 정보를 찾을 수 없습니다.");
        }
        return myPageInfo;
    }

    public Page<MyMissionDto> getMyMissions(Long memberId, Pageable pageable) {
        List<MissionStatus> statuses = List.of(MissionStatus.IN_PROGRESS, MissionStatus.COMPLETED);

        return memberRepository.findMyMissions(memberId, statuses, pageable);
    }

    public Page<ChallengingMissionDto> getMyChallengingMissionsInRegion(Long memberId, Long regionId, Pageable pageable) {
        MissionStatus status = MissionStatus.IN_PROGRESS;

        return memberMissionRepository.findChallengingMissions(memberId, regionId, status, pageable);
    }
}
