package umc.server.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.store.entity.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
