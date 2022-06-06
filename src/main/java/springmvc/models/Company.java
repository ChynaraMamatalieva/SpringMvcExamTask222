package springmvc.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
@Getter
@Setter
@ToString
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "located_country")
    private String locatedCountry;
    @OneToMany (mappedBy ="company", cascade = CascadeType.REMOVE)
    private List<Course>courses=new ArrayList<>();

    public void setCourse(Course course) {
        this.courses.add(course);
    }

}
