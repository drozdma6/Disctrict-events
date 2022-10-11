package cz.cvut.fit.tjv.district_events.dao;

import cz.cvut.fit.tjv.district_events.domain.Village;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VillageJpaRepository extends JpaRepository<Village, Long>{
    @Query(nativeQuery = true, value = "SELECT count(*)>0 FROM event_village_locations WHERE village_id = :village_id")
    boolean isAnyEventInVillage(Long village_id);
}
