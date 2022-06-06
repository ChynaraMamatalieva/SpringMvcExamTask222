package springmvc.services;


import org.springframework.stereotype.Service;
import springmvc.models.Course;
import springmvc.models.Teacher;
import springmvc.repositories.CourseRepository;
import springmvc.repositories.TeacherRepository;


import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public TeacherService(TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    public void saveTeacher(Teacher teacher, UUID courseId) {
        Course course = courseRepository.findCourseById(courseId);
        course.setTeacher(teacher);
        teacher.setCourse(course);
        teacherRepository.saveTeacher(teacher);
    }

    @Transactional
    public Teacher findTeacherById(UUID teacherId) {
        return teacherRepository.findTeacherById(teacherId);
    }

    @Transactional
    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAllTeachers();
    }

    @Transactional
    public void deleteTeacherById(UUID teacherId) {
        teacherRepository.deleteTeacherById(teacherId);
    }
    @Transactional
    public List<Teacher> findCourseById(UUID courseId) {
        return teacherRepository.findCourseById(courseId);
    }
    public void updateTeacher(UUID teacherId, Teacher teacher){
        teacherRepository.updateTeacher(teacherId, teacher);
    }
}
