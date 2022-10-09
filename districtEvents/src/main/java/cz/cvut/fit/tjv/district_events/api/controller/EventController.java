package cz.cvut.fit.tjv.district_events.api.controller;

import cz.cvut.fit.tjv.district_events.api.conventer.EventConverter;
import cz.cvut.fit.tjv.district_events.business.EntityStateException;
import cz.cvut.fit.tjv.district_events.business.EventService;
import cz.cvut.fit.tjv.district_events.domain.Event;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    Collection<EventDto> all() {
        return EventConverter.toDtoMany(eventService.readAll());
    }

    @PostMapping("/events")
    EventDto newEvent(@RequestBody EventDto eventDto) {
        if (eventDto.getName() == null || eventDto.getAuthor() == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Name or author is null");
        }
        Event event = EventConverter.fromDto(eventDto);
        try {
            eventService.create(event);
        } catch (EntityStateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicate id");
        }
        event = this.eventService.readById(event.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Not Found"));
        return EventConverter.toDto(event);
    }


    @PutMapping("/events/{id}")
    EventDto updateUser(@RequestBody EventDto eventDto, @PathVariable Long id) {
        eventService.readById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Not Found"));
        Event event = EventConverter.fromDto(eventDto);
        try {
            this.eventService.update(event);
        } catch (EntityStateException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event ID is not unique",
                                              exception);
        }
        return eventDto;
    }

    @DeleteMapping("events/{id}/{userId}")
    void deleteVillage(@PathVariable Long id, @PathVariable Long userId){
        eventService.deleteById(id, userId);
        throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Event has been deleted");
    }

    @GetMapping("events/author/{id}")
    Collection<EventDto> getEventsCreatedByAuthor(@PathVariable Long id){
        return EventConverter.toDtoMany(eventService.getEventsCreatedByAuthor(id));
    }
}
