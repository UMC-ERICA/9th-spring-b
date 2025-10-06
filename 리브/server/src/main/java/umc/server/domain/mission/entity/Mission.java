package umc.server.domain.mission.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.server.domain.store.entity.Store;
import umc.server.global.entity.BaseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "mission")
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mission_title", nullable = false)
    private String missionTitle;

    @Column(name = "mission_description", nullable = false)
    private String missionDescription;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    @Column(name = "reward_points", nullable = false)
    private int rewardPoints;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "mission")
    private List<MemberMission> memberMissionList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}
