package academy.mindswap.schoolpark.schoolpark.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@ToString(exclude = "teachers")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String roleName;
    @ManyToMany
    @JsonIgnore
    private List<Teacher> teachers;

    public Role() {
    }
}
