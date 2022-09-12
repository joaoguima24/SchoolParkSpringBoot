package academy.mindswap.schoolpark.schoolpark.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@ToString(exclude = "teachers")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer ID;
    private String location;
    private String typeOfEvent;

    @ManyToMany
    @JsonIgnore
    private List<Teacher> teachers;

    public Event (){

    }

}
