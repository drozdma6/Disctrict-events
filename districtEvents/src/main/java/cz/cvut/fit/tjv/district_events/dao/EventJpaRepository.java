package cz.cvut.fit.tjv.district_events.dao;

import cz.cvut.fit.tjv.district_events.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface EventJpaRepository extends JpaRepository<Event, Long>{
    Collection<Event> findAllByAuthor_Id(Long author_id);
}
