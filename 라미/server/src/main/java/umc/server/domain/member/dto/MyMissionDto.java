package umc.server.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.server.domain.mission.MissionStatus;

import java.time.LocalDateTime;

@Getter
public class MyMissionDto {

    private Long memberMissionId;
    private String missionContent;
    private String storeName;
    private int missionPoint;
    private String status;
    private LocalDateTime createdAt;

    public MyMissionDto(Long memberMissionId, String missionContent, String storeName, int missionPoint, MissionStatus status, LocalDateTime createdAt) {
        this.memberMissionId = memberMissionId;
        this.missionContent = missionContent;
        this.storeName = storeName;
        this.missionPoint = missionPoint;
        this.status = status.toString();
        this.createdAt = createdAt;
    }
}
