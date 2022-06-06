package springmvc.services;

import org.springframework.stereotype.Service;
import springmvc.models.Company;
import springmvc.models.Course;
import springmvc.repositories.CompanyRepository;
import springmvc.repositories.CourseRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;

    public CourseService(CourseRepository courseRepository, CompanyRepository companyRepository) {
        this.courseRepository = courseRepository;
        this.companyRepository = companyRepository;
    }
    @Transactional
    public void saveCourse(Course course, UUID companyId) {
        Company company = companyRepository.findCompanyById(companyId);
        company.setCourse(course);
        course.setCompany(company);
        courseRepository.saveCourse(course);
    }

    public Course findCourseById(UUID courseId) {
        return courseRepository.findCourseById(courseId);
    }

    public List<Course> findAllCourses() {
        return courseRepository.findAllCourses();
    }

    public void deleteCourseById(UUID courseId) {
        courseRepository.deleteCourseById(courseId);
    }


    public List<Course> findCompanyById(UUID companyId) {
        return courseRepository.findCompanyById(companyId);
    }


    public void updateCourse(UUID courseID, Course course) {
        courseRepository.updateCourse(courseID,course );
    }
}
