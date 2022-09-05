package cz.cvut.fit.tjv.district_events.dao;

import cz.cvut.fit.tjv.district_events.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
}
