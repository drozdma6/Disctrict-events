package cz.cvut.fit.tjv.district_events.business;

public class EntityNotFoundException extends Exception{
    public <K> EntityNotFoundException(K id){
        super("Entity not found" + id);
    }
}
