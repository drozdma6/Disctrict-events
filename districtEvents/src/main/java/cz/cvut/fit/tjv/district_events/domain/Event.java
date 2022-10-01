package cz.cvut.fit.tjv.district_events.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Event{
    @Id
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private LocalDateTime dateTime;
    private String description;
    @ManyToOne
    @JoinColumn(name = "author_user_id", nullable = false)
    private User author;
    @ManyToMany
    private Set<Village> locations = new HashSet<>();

    public Event(){
    }

    public Event(Long id, String name, LocalDateTime dateTime, String description, User author,
                 Set<Village> locations){
        this.id = id;
        this.name = name;
        this.dateTime = dateTime;
        this.description = description;
        this.author = author;
        this.locations = locations;
    }

    public LocalDateTime getDateTime(){
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime){
        this.dateTime = dateTime;
    }

    public String getDescription(){
        return description;
    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public User getAuthor(){
        return author;
    }

    public Set<Village> getLocations(){
        return locations;
    }

    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Event event = (Event) o;
        return id.equals(event.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    private String villagesToString(){
        StringBuilder res = new StringBuilder();
        for (Village village : locations){
            res.append(village.toString());
        }
        return res.toString();
    }


    @Override
    public String toString(){
        return "Event{" + "id='" + id.toString() + '\'' + "name='" + name + '\'' + "author='" +
               author.toString() + '\'' + "description='" + description + '\'' + "location='" +
               villagesToString() + '\'' + '}';
    }
}
