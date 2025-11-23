package umc.server.domain.mission.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.entity.mapping.MemberMission;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.exception.code.MemberErrorCode;
import umc.server.domain.member.repository.MemberMissionRepository;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.mission.converter.MissionConverter;
import umc.server.domain.mission.dto.res.MissionResDTO;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.enums.MissionStatus;
import umc.server.domain.mission.repository.MissionRepository;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.exception.StoreException;
import umc.server.domain.store.exception.code.StoreErrorCode;
import umc.server.domain.store.repository.StoreRepository;

import static umc.server.domain.mission.enums.MissionStatus.IN_PROGRESS;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MissionResDTO.StoreMissionListDTO findMission(Long storeId, Integer page){
        // 가게 존재 여부 검증
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));
        // 가게에 맞는 미션 가져오기 (offset 페이징)
        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        Page<Mission> result = missionRepository.findAllByStore(store, pageRequest);

        // 결과 응답을 DTO로 변환
        return MissionConverter.toStoreMissionListDTO(result);
    }

    @Override
    public MissionResDTO.MyMissionListDTO findMyMissions(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        Page<MemberMission> result = memberMissionRepository.findAllByMemberAndStatus(member, MissionStatus.IN_PROGRESS, pageRequest);

        return MissionConverter.toMyMissionListDTO(result);
    }
}
