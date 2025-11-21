package umc.server.domain.mission.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class MissionReqDTO {
    @Getter
    public static class ChallengeDTO {

        @NotNull(message = "회원 ID는 필수입니다.")
        private Long memberId;

        @NotNull(message = "미션 ID는 필수입니다.")
        private Long missionId;
    }
}
