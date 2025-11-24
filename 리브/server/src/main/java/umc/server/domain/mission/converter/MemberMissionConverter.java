package umc.server.domain.mission.converter;

import org.springframework.data.domain.Page;
import umc.server.domain.member.entity.Member;
import umc.server.domain.mission.dto.res.MemberMissionResDTO;
import umc.server.domain.mission.entity.MemberMission;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.enums.MissionStatus;
import umc.server.domain.store.entity.Store;

public class MemberMissionConverter {

    // entity -> DTO
    public static MemberMissionResDTO.ChallengeResponse toChallengeResponseDTO(MemberMission memberMission) {
        return MemberMissionResDTO.ChallengeResponse.builder()
                .missionStatus(memberMission.getMissionStatus())
                .memberMissionId(memberMission.getId())
                .missionId(memberMission.getId())
                .memberId(memberMission.getId())
                .build();
    }

    // Member + Mission -> MemberMission entity
    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .missionStatus(MissionStatus.IN_PROGRESS)
                .build();
    }

    // Page<MemberMission> -> MemberMissionList DTO
    public static MemberMissionResDTO.MyMissionPreViewListDTO toMyMissionPreViewListDTO(Page<MemberMission> page) {
        return MemberMissionResDTO.MyMissionPreViewListDTO.builder()
                .memberMissionList(page.getContent().stream()
                        .map(MemberMissionConverter::toMyMissionPreViewDTO)
                        .toList())
                .listSize(page.getSize())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }

    // MemberMission -> Preview DTO (요약 정보)
    public static MemberMissionResDTO.MyMissionPreViewDTO toMyMissionPreViewDTO(MemberMission memberMission) {
        Mission mission = memberMission.getMission();
        Store store = mission.getStore();

        return MemberMissionResDTO.MyMissionPreViewDTO.builder()
                .storeName(store.getStoreName())
                .storeCategory(store.getCategory())
                .missionTitle(mission.getMissionTitle())
                .missionDescription(mission.getMissionDescription())
                .rewardPoints(mission.getRewardPoints())
                .deadline(mission.getDeadline())
                .build();
    }
}
