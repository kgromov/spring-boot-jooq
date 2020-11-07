package guru.springframework.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by jt on 6/13/17.
 */
@Getter
@Setter
@Entity
@Table(name = "unit_of_measure")
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
}
