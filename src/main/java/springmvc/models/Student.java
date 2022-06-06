package springmvc.models;

import lombok.Getter;
import lombok.Setter;
import springmvc.enums.StudyFormat;


import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "first_name")
    private String firstName;
    private String email;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "study_format")
    private StudyFormat studyFormat;
    @ManyToOne(cascade = {MERGE})
    private Group group;

}
