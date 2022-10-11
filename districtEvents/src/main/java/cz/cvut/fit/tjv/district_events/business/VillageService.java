package cz.cvut.fit.tjv.district_events.business;

import cz.cvut.fit.tjv.district_events.dao.VillageJpaRepository;
import cz.cvut.fit.tjv.district_events.domain.Village;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class VillageService extends AbstractCrudService<Village, Long, VillageJpaRepository>{
    public VillageService(VillageJpaRepository repository){
        super(repository);
    }

    @Override
    public boolean exists(Village entity){
        return repository.existsById(entity.getId());
    }

    public boolean exists(Set<Village> villages){
        return villages.stream().allMatch(village -> repository.existsById(village.getId()));
    }

    @Override
    public void deleteById(Long id) throws EntityNotFoundException{
        if (repository.isAnyEventInVillage(id)){
            //cannot delete village where events are being organized
            throw new UnsupportedOperationException();
        }
        super.deleteById(id);

    }
}
