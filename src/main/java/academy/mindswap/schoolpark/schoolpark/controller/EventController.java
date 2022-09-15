package academy.mindswap.schoolpark.schoolpark.controller;

import academy.mindswap.schoolpark.schoolpark.model.Event;
import academy.mindswap.schoolpark.schoolpark.permissions.Util;
import academy.mindswap.schoolpark.schoolpark.service.EventService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping
    @PreAuthorize(Util.ONLY_ADMIN_AND_USER_ROLE)
    public List<Event> getAllEvents(){
        return eventService.getAllEvents();
    }
    @PostMapping
    @PreAuthorize(Util.ONLY_ADMIN_AND_USER_ROLE)
    public Event createEvent(@RequestBody Event event){
        return eventService.createEvent(event);
    }

    @PutMapping("/{eventID}/{teacherID}")
    @PreAuthorize(Util.EDITOR_ROLE)
    public Event addTeacherToEvent(@PathVariable Integer eventID,@PathVariable Integer teacherID){
        return eventService.addTeacherToEvent(eventID,teacherID);
    }
    @GetMapping("/{teacherID}")
    @PreAuthorize(Util.ONLY_ADMIN_AND_USER_ROLE)
    public List<Event> getAllEventsByTeacherID(@PathVariable Integer teacherID){
        return eventService.getAllEventsByTeacherID(teacherID);
    }
}
