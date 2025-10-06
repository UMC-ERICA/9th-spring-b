package umc.server.domain.alarm.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.server.domain.mission.entity.MemberMission;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "reviewRequestAlarm")
public class ReviewRequestAlarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alarm_id")
    private Alarm alarm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_mission_id")
    private MemberMission memberMission;
}
