package academy.mindswap.schoolpark.schoolpark.model;

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
@ToString (exclude = "events")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @OneToMany (mappedBy = "owner")
    private List<Vehicle> vehicle;

    @ManyToMany (mappedBy = "teachers")
    private List<Event> events;

    public Teacher (){

    }

}
