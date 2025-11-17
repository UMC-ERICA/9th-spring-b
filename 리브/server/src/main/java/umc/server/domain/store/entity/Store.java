package umc.server.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;
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

    @Column(name = "store_name", nullable = false)
    private String storeName;

    @Column(name = "location_name", nullable = false)
    private String locationName;

    @Column(name = "store_address", nullable = false)
    private String storeAddress;

    @Column(name = "category", nullable = false)
    private String category;
}
