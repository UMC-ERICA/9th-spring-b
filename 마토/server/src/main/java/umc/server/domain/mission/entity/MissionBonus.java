package umc.server.domain.mission.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "mission_bonus")
public class MissionBonus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_bonus_id")
    private Long missionBonusId;

    @Column(name = "threshold", nullable = false)
    private Integer threshold;

    @Column(name = "bonus_point", nullable = false)
    private Integer bonusPoint;
}
