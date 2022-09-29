package cz.cvut.fit.tjv.district_events.api.controller;

import cz.cvut.fit.tjv.district_events.domain.User;
import cz.cvut.fit.tjv.district_events.domain.Village;

import java.util.HashSet;
import java.util.Set;

public class EventDto {
    private Long id;
    private String name;
    private String description;
    private User author;
    private Set<Village> locations = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<Village> getLocations() {
        return locations;
    }

    public void setLocations(Set<Village> locations) {
        this.locations = locations;
    }

    public EventDto(Long id, String name, String description, User author, Set<Village> locations) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.author = author;
        this.locations = locations;
    }

    public EventDto() {
    }
}
