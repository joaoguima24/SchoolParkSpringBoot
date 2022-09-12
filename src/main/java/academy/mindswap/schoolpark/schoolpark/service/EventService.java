package academy.mindswap.schoolpark.schoolpark.service;

import academy.mindswap.schoolpark.schoolpark.model.Event;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents();

    Event createEvent(Event event);

    Event addTeacherToEvent(Integer eventID, Integer teacherID);

    List<Event> getAllEventsByTeacherID(Integer teacherID);
}
