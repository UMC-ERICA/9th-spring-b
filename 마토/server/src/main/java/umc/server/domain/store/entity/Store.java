package umc.server.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "store")

public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Column(name = "store_name", nullable = false)
    private String name;

    @Column(name = "store_address", nullable = false)
    private String address;

    @Column(name = "store_detail_address")
    private String detailAddress;
}
