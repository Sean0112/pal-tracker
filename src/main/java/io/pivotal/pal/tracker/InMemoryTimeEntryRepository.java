package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    HashMap<Long, TimeEntry> collection = new HashMap<Long, TimeEntry>();

//    public Long getLastUsedTimeEntryId() {
//        return lastUsedTimeEntryId;
//    }
    long timeEntryCounter = 1;
    //Long lastUsedTimeEntryId = 1L;

    @Override
    public TimeEntry create(TimeEntry any) {
        //any.timeEntryId = any.getId();

        any.setTimeEntryId(timeEntryCounter);
        collection.put(timeEntryCounter, any);

        //lastUsedTimeEntryId++;
        timeEntryCounter++;
        return any;
    }

    @Override
    public TimeEntry find(long timeEntryId) {

        TimeEntry entryfound =  null;
        if(collection.containsKey(timeEntryId))
        return collection.get(timeEntryId);
        else
            return entryfound;
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(collection.values());
    }

    @Override
    public TimeEntry update(long eq, TimeEntry any) {
        //if something in the collection
        //return any
       // TimeEntry preexistingTimeEntry = collection.get(eq);

        TimeEntry preexistingTimeEntry = find(eq);
        if(preexistingTimeEntry != null) {
            any.setTimeEntryId(eq);
            collection.put(eq,any);
            return any;
        }
        else {
            return null;
        }

        //otherwise
        //return null

    }

    @Override
    public void delete(long timeEntryId) {
        collection.remove(timeEntryId);
    }

    public void set(long timeEntryId){

    }
}
