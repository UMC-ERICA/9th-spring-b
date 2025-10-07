package umc.server.domain.mission.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "mission")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long missionId;

    @Column(name = "store_id", nullable = false)
    private Long storeId;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "conditional", nullable = false)
    private String conditional;

    @Column(name = "point", nullable = false)
    private Integer point;
}
