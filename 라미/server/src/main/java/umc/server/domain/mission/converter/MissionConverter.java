package umc.server.domain.mission.converter;

import umc.server.domain.member.entity.Member;
import umc.server.domain.member.entity.mapping.MemberMission;
import umc.server.domain.mission.enums.MissionStatus;
import umc.server.domain.mission.dto.res.MissionResDTO;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.store.entity.Store;

public class MissionConverter {

    // Request DTO + Entity → MemberMission Entity
    public static MemberMission toMemberMission(Member member, Mission mission, Store store) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .store(store)
                .status(MissionStatus.IN_PROGRESS)  // 도전 중 상태로 시작
                .build();
    }

    // MemberMission Entity → Response DTO
    public static MissionResDTO.ChallengeDTO toChallengeDTO(MemberMission memberMission) {
        return MissionResDTO.ChallengeDTO.builder()
                .memberMissionId(memberMission.getId())
                .memberId(memberMission.getMember().getId())
                .memberName(memberMission.getMember().getName())
                .missionId(memberMission.getMission().getId())
                .missionContent(memberMission.getMission().getContent())
                .missionPoint(memberMission.getMission().getPoint())
                .missionDeadline(memberMission.getMission().getDeadline())
                .storeId(memberMission.getStore().getId())
                .storeName(memberMission.getStore().getName())
                .status(memberMission.getStatus())
                .challengedAt(memberMission.getCreatedAt())
                .build();
    }
}