package umc.server.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.server.domain.store.enums.RestaurantCategory;
import umc.server.domain.store.enums.StoreStatus;
import umc.server.global.entity.BaseEntity;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "store")
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "store_status", nullable = false)
    private StoreStatus storeStatus;

    @Column(name = "phone_num", nullable = false)
    private String phoneNum;


    @Enumerated(EnumType.STRING)
    @Column(name = "Restaurant_category", nullable = false)
    private RestaurantCategory restaurantCategory;
}
