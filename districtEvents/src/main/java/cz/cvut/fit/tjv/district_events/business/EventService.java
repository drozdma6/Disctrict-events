package cz.cvut.fit.tjv.district_events.business;

import cz.cvut.fit.tjv.district_events.dao.EventJpaRepository;
import cz.cvut.fit.tjv.district_events.domain.Event;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EventService extends AbstractCrudService<Event, Long, EventJpaRepository> {
    private final UserService userService;
    private final VillageService villageService;

    public EventService(EventJpaRepository repository, UserService userService, VillageService villageService) {
        super(repository);
        this.userService = userService;
        this.villageService = villageService;
    }

    @Override
    public boolean exists(Event entity) {
        return repository.existsById(entity.getId());
    }

    /**
     * This operation is not supported for Post. It always throws an exception.
     *
     * @param id key of the post to be deleted
     * @throws UnsupportedOperationException always
     */
    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException();
    }

    /**
     * Attempts to delete a post. Checks whether the caller is the same user as the author of the event.
     *
     * @param id          The event ID that is being deleted.
     * @param actedUponId The user that initiates this action.
     * @throws AccessDeniedException In case the caller is not the author of the event being deleted.
     */
    @SuppressWarnings("unused")
    public void deleteById(Long id, Long actedUponId) {
        Optional<Event> optionalEvent = readById(id);
        if (optionalEvent.isEmpty())
            return;
        if (optionalEvent.get().getAuthor().getId().equals(actedUponId))
            this.repository.deleteById(id);
        else
            throw new AccessDeniedException("Only author can delete their event");

    }

    @Override
    public void create(Event entity) throws EntityStateException {
        if (entity.getAuthor() == null || exists(entity) || !userService.exists(
                entity.getAuthor()) || !villageService.exists(entity.getLocations())) {
            throw new EntityStateException(entity);
        }
        repository.save(entity);
    }
}