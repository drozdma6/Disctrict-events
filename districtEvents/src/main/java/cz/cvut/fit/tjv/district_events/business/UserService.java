package cz.cvut.fit.tjv.district_events.business;

import cz.cvut.fit.tjv.district_events.dao.UserJpaRepository;
import cz.cvut.fit.tjv.district_events.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserService extends AbstractCrudService<User, Long, UserJpaRepository> {

    protected UserService(UserJpaRepository repository) {
        super(repository);
    }

    @Override
    public boolean exists(User entity) {
        return repository.existsById(entity.getId());
    }
}
