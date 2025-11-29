package umc.server.domain.mission.converter;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import umc.server.domain.mission.dto.res.MissionChallengeResDto;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.entity.MissionChallenge;

@Component
public class MissionChallengeConverter {
    public MissionChallengeResDto.ChallengePreviewDTO toChallengePreviewDTO(MissionChallenge challenge) {

        Mission mission = challenge.getMission();

        return MissionChallengeResDto.ChallengePreviewDTO.builder()
                .missionChallengeId(challenge.getId())
                .missionId(mission.getId())
                .storeId(mission.getStore().getId())
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .status(challenge.getStatus())
                .build();
    }

    public MissionChallengeResDto.ChallengePreviewListDTO toChallengePreviewListDTO(
            Page<MissionChallenge> page
    ) {
        return MissionChallengeResDto.ChallengePreviewListDTO.builder()
                .challengeList(
                        page.getContent().stream()
                                .map(this::toChallengePreviewDTO)
                                .toList()
                )
                .listSize(page.getSize())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }

    public MissionChallengeResDto.ChallengeDTO toChallengeDTO(MissionChallenge challenge) {
        return MissionChallengeResDto.ChallengeDTO.builder()
                .missionChallengeId(challenge.getId())
                .missionId(challenge.getMission().getId())
                .userId(challenge.getUserId())
                .status(challenge.getStatus())
                .build();
    }
}
