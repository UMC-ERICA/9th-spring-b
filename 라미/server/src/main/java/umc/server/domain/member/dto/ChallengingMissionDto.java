package umc.server.domain.member.dto;

import lombok.Getter;
import umc.server.domain.mission.enums.MissionStatus;

import java.time.LocalDateTime;

@Getter
public class ChallengingMissionDto {

    private final Long missionId;
    private final String content;
    private final int missionPoint; // String reward -> int missionPoint
    private final LocalDateTime deadline;
    private final String storeName;
    private final String missionStatus;

    public ChallengingMissionDto(Long missionId, String content, int missionPoint, LocalDateTime deadline, String storeName, MissionStatus missionStatus) {
        this.missionId = missionId;
        this.content = content;
        this.missionPoint = missionPoint; // reward -> missionPoint
        this.deadline = deadline;
        this.storeName = storeName;
        this.missionStatus = missionStatus.toString();
    }
}
