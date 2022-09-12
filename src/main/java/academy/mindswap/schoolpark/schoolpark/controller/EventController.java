package academy.mindswap.schoolpark.schoolpark.controller;

import academy.mindswap.schoolpark.schoolpark.model.Event;
import academy.mindswap.schoolpark.schoolpark.service.EventService;
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
    public List<Event> getAllEvents(){
        return eventService.getAllEvents();
    }
    @PostMapping
    public Event createEvent(@RequestBody Event event){
        return eventService.createEvent(event);
    }

    @PutMapping("/{eventID}/{teacherID}")
    public Event addTeacherToEvent(@PathVariable Integer eventID,@PathVariable Integer teacherID){
        return eventService.addTeacherToEvent(eventID,teacherID);
    }
    @GetMapping("/{teacherID}")
    public List<Event> getAllEventsByTeacherID(@PathVariable Integer teacherID){
        return eventService.getAllEventsByTeacherID(teacherID);
    }
}
