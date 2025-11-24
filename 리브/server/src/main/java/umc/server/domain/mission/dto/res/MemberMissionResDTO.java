package umc.server.domain.mission.dto.res;

import lombok.Builder;
import umc.server.domain.mission.enums.MissionStatus;

import java.time.LocalDate;
import java.util.List;

public class MemberMissionResDTO {

    @Builder
    public record ChallengeResponse(
            MissionStatus missionStatus,
            Long memberMissionId,
            Long missionId,
            Long memberId
    ) {}

    @Builder
    public record MyMissionPreViewListDTO(
            List<MyMissionPreViewDTO> memberMissionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record MyMissionPreViewDTO(
            String storeName,
            String storeCategory,
            String missionTitle,
            String missionDescription,
            Integer rewardPoints,
            LocalDate deadline
    ) {}

}
