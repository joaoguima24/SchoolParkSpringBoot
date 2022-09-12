package academy.mindswap.schoolpark.schoolpark.service;

import academy.mindswap.schoolpark.schoolpark.command.TeacherConverter;
import academy.mindswap.schoolpark.schoolpark.model.Event;
import academy.mindswap.schoolpark.schoolpark.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final TeacherService teacherService;

    public EventService(EventRepository eventRepository, TeacherService teacherService) {
        this.eventRepository = eventRepository;
        this.teacherService = teacherService;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event addTeacherToEvent(Integer eventID, Integer teacherID) {
        if (teacherService.findTeacherByID(teacherID) == null || eventRepository.findById(eventID).isEmpty() ){
            return null;
        }
        eventRepository.findById(eventID).get().getTeachers().add(TeacherConverter.convertToEntity(teacherService.findTeacherByID(teacherID)));
        return eventRepository.save(eventRepository.findById(eventID).get());
    }

    public List<Event> getAllEventsByTeacherID(Integer teacherID) {
        return eventRepository.findEventsByTeachersID(teacherID);
    }
}
