package umc.server.domain.mission.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "mission_challenges")
public class MissionChallenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_challenge_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;  // 도전 미션

    @Column(name = "status", nullable = false)
    private String status;  // 도전 상태

    @Column(name = "user_id", nullable = false)
    private Long userId;  // 도전한 사용자 ID
}
