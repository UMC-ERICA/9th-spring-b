package umc.server.domain.mission.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.server.global.entity.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "mission")
public class Mission extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "point", nullable = false)
    private int point;

    @Column(name = "deadline", nullable = false)
    private LocalDateTime deadline;
}
