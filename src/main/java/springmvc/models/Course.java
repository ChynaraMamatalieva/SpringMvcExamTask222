package springmvc.models;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.swing.event.DocumentEvent.EventType.REMOVE;

@Entity
@Getter
@Setter
@ToString
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "course_name")
    private String courseName;

    private int  duration;

    @ManyToOne
    private Company company;

    @ManyToMany(mappedBy = "courses",cascade = CascadeType.REMOVE)
    private List<Group> groups=new ArrayList<>();

    @OneToOne(mappedBy = "course", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Teacher teacher;

    public void setGroup(Group group){
        this.groups.add(group);
    }



}


