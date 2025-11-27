package umc.server.domain.mission.converter;

import org.springframework.data.domain.Page;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.entity.mapping.MemberMission;
import umc.server.domain.mission.enums.MissionStatus;
import umc.server.domain.mission.dto.res.MissionResDTO;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.store.entity.Store;

import java.time.LocalDate;

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

    public static MissionResDTO.StoreMissionDTO toStoreMissionDTO(Mission mission) {
        return MissionResDTO.StoreMissionDTO.builder()
                .storeName(mission.getStore().getName())
                .missionContent(mission.getContent())
                .point(mission.getPoint())
                .deadline(LocalDate.from(mission.getDeadline()))
                .build();
    }

    public static MissionResDTO.StoreMissionListDTO toStoreMissionListDTO(Page<Mission> result) {
        return MissionResDTO.StoreMissionListDTO.builder()
                .storeMissionDTOList(result.getContent().stream()
                        .map(MissionConverter::toStoreMissionDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static MissionResDTO.MyMissionDTO toMyMissionDTO(MemberMission memberMission) {
        return MissionResDTO.MyMissionDTO.builder()
                .missionContent(memberMission.getMission().getContent())
                .point(memberMission.getMission().getPoint())
                .deadline(LocalDate.from(memberMission.getMission().getDeadline()))
                .storeName(memberMission.getMission().getStore().getName())
                .status(memberMission.getStatus())
                .build();
    }

    public static  MissionResDTO.MyMissionListDTO toMyMissionListDTO(Page<MemberMission> result) {
        return MissionResDTO.MyMissionListDTO.builder()
                .myMissionDTOList(result.getContent().stream()
                        .map(MissionConverter::toMyMissionDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

}