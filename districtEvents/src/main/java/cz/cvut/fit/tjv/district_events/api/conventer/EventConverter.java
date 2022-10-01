package cz.cvut.fit.tjv.district_events.api.conventer;

import cz.cvut.fit.tjv.district_events.api.controller.EventDto;
import cz.cvut.fit.tjv.district_events.domain.Event;

import java.util.ArrayList;
import java.util.Collection;

public class EventConverter{
    public static Event fromDto(EventDto eventDto){
        return new Event(eventDto.getId(), eventDto.getName(), eventDto.getDateTime(),
                         eventDto.getDescription(), eventDto.getAuthor(), eventDto.getLocations());
    }

    public static EventDto toDto(Event event){
        return new EventDto(event.getId(), event.getName(), event.getDateTime(),
                            event.getDescription(), event.getAuthor(), event.getLocations());
    }

    public static Collection<Event> fromDtoMany(Collection<EventDto> villageDtos){
        Collection<Event> users = new ArrayList<>();
        villageDtos.forEach((u) -> users.add(fromDto(u)));
        return users;
    }

    public static Collection<EventDto> toDtoMany(Collection<Event> villages){
        Collection<EventDto> eventDtos = new ArrayList<>();
        villages.forEach((e) -> eventDtos.add(toDto(e)));
        return eventDtos;
    }
}
