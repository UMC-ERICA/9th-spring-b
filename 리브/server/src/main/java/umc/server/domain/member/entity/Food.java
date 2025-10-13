package umc.server.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.server.domain.member.enums.FoodType;
import umc.server.global.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "food")
public class Food extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "food_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberFood> memberFoodList = new ArrayList<>();
}
