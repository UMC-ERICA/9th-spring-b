package umc.server.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.server.domain.user.enums.OptionName;


@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "preference_option")
public class PreferenceOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "preference_option_id")
    private Long preferenceOptionId;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private OptionName name;
}
