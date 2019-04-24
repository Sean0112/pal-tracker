package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @RequestMapping(value = "/time-entries/{timeEntryId}", method=RequestMethod.GET)
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry timeEntryToCreate = this.timeEntryRepository.find(timeEntryId);
        HttpStatus status = null;
        if(timeEntryToCreate == null)
            status = HttpStatus.NOT_FOUND;
        else
            status = HttpStatus.OK;
        return new ResponseEntity(timeEntryToCreate, status);
    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
         //if(no ids) current id = 1
        // otherwise: current id + 1;
//        timeEntryToCreate.timeEntryId = this.timeEntryRepository;
        TimeEntry createdTimeEntry = this.timeEntryRepository.create(timeEntryToCreate);
        return new ResponseEntity(createdTimeEntry, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> listTimeEntries = this.timeEntryRepository.list();
        return new ResponseEntity(listTimeEntries, HttpStatus.OK);
    }

    @RequestMapping(value = "/time-entries/{timeEntryId}", method=RequestMethod.PUT)
    public ResponseEntity update(@PathVariable long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry updated = this.timeEntryRepository.update(timeEntryId, expected);
        HttpStatus status = null;
        if(updated == null)
            status = HttpStatus.NOT_FOUND;
        else
            status = HttpStatus.OK;
        return new ResponseEntity(updated, status);
    }

    @RequestMapping(value = "/time-entries/{timeEntryId}", method=RequestMethod.DELETE)
    public ResponseEntity<TimeEntry> delete(@PathVariable long timeEntryId) {
        this.timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
