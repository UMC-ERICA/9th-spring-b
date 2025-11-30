package umc.server.domain.mission.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class MissionChallengeResDto {
    @Getter
    @Builder
    public static class ChallengePreviewDTO {
        private Long missionChallengeId;
        private Long missionId;
        private Long storeId;
        private String conditional;
        private Integer point;
        private String status;
    }

    @Getter
    @Builder
    public static class ChallengePreviewListDTO {
        private List<ChallengePreviewDTO> challengeList;
        private int listSize;
        private int totalPage;
        private long totalElements;
        private boolean isFirst;
        private boolean isLast;
    }

    //단건 응답용 DTO (미션 도전 시작할 때 사용)
    @Getter
    @Builder
    public static class ChallengeDTO {
        private Long missionChallengeId;
        private Long missionId;
        private Long userId;
        private String status;
    }
}
