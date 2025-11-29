package umc.server.domain.mission.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class MissionResDto {
    @Getter
    @Builder
    public static class MissionPreviewDTO {
        private Long missionId;
        private LocalDate endDate;
        private String conditional;
        private Integer point;
    }

    @Getter
    @Builder
    public static class MissionPreviewListDTO {
        private List<MissionPreviewDTO> missionList;
        private int listSize;
        private int totalPage;
        private long totalElements;
        private boolean isFirst;
        private boolean isLast;
    }
}
