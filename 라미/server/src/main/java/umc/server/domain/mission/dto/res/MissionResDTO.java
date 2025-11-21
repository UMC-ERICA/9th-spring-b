package umc.server.domain.mission.dto.res;

import lombok.Builder;
import lombok.Getter;
import umc.server.domain.mission.enums.MissionStatus;

import java.time.LocalDateTime;

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
}
