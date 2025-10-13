package umc.server.domain.mapping.entitiy;

import jakarta.persistence.*;
import lombok.*;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.store.entity.Store;
import umc.server.domain.user.entity.User;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "user_mission")
public class UserMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_mission_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;


    @Column(name = "is_completed", nullable = false)
    private Boolean isCompleted;
}
