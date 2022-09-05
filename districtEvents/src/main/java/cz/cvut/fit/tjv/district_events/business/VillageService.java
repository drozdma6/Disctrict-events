package cz.cvut.fit.tjv.district_events.business;

import cz.cvut.fit.tjv.district_events.dao.VillageJpaRepository;
import cz.cvut.fit.tjv.district_events.domain.Village;
import org.springframework.stereotype.Component;

@Component
public class VillageService extends AbstractCrudService<Village, Long>{

    public VillageService(VillageJpaRepository repository) {
        super(repository);
    }

    @Override
    public boolean exists(Village entity) {
        return repository.existsById(entity.getId());
    }
}
