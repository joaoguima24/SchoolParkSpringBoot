package academy.mindswap.schoolpark.schoolpark.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@Builder
@ToString(exclude = "owner")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private String typeOfVehicle;
    @ManyToOne
    @JsonIgnore
    private Teacher owner;

    public Vehicle (){

    }

}
