package umc.server.domain.mission.dto.res;

import lombok.Builder;
import lombok.Getter;
import umc.server.domain.mission.enums.MissionStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {
    @Getter
    @Builder
    public static class ChallengeDTO {
        private Long memberMissionId;
        private Long memberId;
        private String memberName;
        private Long missionId;
        private String missionContent;
        private Integer missionPoint;
        private LocalDateTime missionDeadline;
        private Long storeId;
        private String storeName;
        private MissionStatus status;
        private LocalDateTime challengedAt;
    }

    @Builder
    public record StoreMissionDTO(
        String storeName,
        String missionContent,
        Integer point,
        LocalDate deadline
    ) {}

    @Builder
    public record StoreMissionListDTO(
            List<StoreMissionDTO> storeMissionDTOList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record CompletedMissionDTO(
            String storeName,
            String missionContent,
            Integer missionPoint,
            MissionStatus status
    ){}

    @Builder
    public record MyMissionDTO(
            MissionStatus status,
            String missionContent,
            Integer point,
            String storeName,
            LocalDate deadline
    ){}

    @Builder
    public record MyMissionListDTO(
            List<MyMissionDTO> myMissionDTOList,
            Integer listSize, // 현 페이지에 담긴 미션 개수
            Integer totalPage, // 전체 페이지 수
            Long totalElements, // 전체 미션 개수
            Boolean isFirst,
            Boolean isLast
    ){}
}
