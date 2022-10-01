package cz.cvut.fit.tjv.district_events.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Village {
    @Id
    private Long id;
    @Column(nullable = false)
    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Village(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Village() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Village village = (Village) o;
        return id.equals(village.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Village{" + "id='" + id.toString() + '\'' + "name='" + name + '\'' + '}';
    }
}
