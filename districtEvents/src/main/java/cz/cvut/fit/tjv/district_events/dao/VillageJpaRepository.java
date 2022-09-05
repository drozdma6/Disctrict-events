package cz.cvut.fit.tjv.district_events.dao;

import cz.cvut.fit.tjv.district_events.domain.Village;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VillageJpaRepository extends JpaRepository<Village, Long> {
}
