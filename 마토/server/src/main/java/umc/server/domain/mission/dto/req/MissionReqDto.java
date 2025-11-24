package umc.server.domain.mission.dto.req;

import java.time.LocalDate;

public class MissionReqDto {

    public record JoinDTO(
            Long storeId,
            LocalDate endDate,
            String conditional,
            Integer point
    ) {}
}
