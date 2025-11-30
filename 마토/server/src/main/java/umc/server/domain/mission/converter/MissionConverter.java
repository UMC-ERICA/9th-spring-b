package umc.server.domain.mission.converter;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import umc.server.domain.mission.dto.res.MissionResDto;
import umc.server.domain.mission.entity.Mission;

@Component
public class MissionConverter {

    public MissionResDto.MissionPreviewDTO toPreviewDTO(Mission mission) {
        return MissionResDto.MissionPreviewDTO.builder()
                .missionId(mission.getId())
                .endDate(mission.getEndDate())
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .build();
    }

    public MissionResDto.MissionPreviewListDTO toPreviewListDTO(Page<Mission> result) {
        return MissionResDto.MissionPreviewListDTO.builder()
                .missionList(
                        result.getContent().stream()
                                .map(this::toPreviewDTO)
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
