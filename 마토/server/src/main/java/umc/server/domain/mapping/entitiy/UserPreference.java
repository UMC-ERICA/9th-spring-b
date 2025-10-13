package umc.server.domain.mapping.entitiy;

import jakarta.persistence.*;
import lombok.*;
import umc.server.domain.user.entity.PreferenceOption;
import umc.server.domain.user.entity.User;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "user_preference")
public class UserPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_preference_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preference_option_id", nullable = false)
    private PreferenceOption preferenceOption;
}
