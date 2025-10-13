package umc.server.domain.mission.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.server.domain.store.entity.Store;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "mission")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "conditional", nullable = false)
    private String conditional;

    @Column(name = "point", nullable = false, columnDefinition = "int default 0")
    private Integer point;
}
