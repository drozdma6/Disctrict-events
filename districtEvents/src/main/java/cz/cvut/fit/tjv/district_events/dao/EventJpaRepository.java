package cz.cvut.fit.tjv.district_events.dao;

import cz.cvut.fit.tjv.district_events.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventJpaRepository extends JpaRepository<Event, Long> {
}
