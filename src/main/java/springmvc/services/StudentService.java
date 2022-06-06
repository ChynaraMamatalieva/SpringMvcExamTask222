package springmvc.services;

import org.springframework.stereotype.Service;
import springmvc.models.Group;
import springmvc.models.Student;
import springmvc.repositories.GroupRepository;
import springmvc.repositories.StudentRepository;


import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public StudentService(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }
    @Transactional
    public void saveStudent(Student student, UUID groupId) {
        Group group = groupRepository.findGroupById(groupId);
        group.setStudent(student);
        student.setGroup(group);
        studentRepository.saveStudent(student);
    }

    @Transactional
    public Student findStudentById(UUID studentId) {
        return studentRepository.findStudentById(studentId);
    }

    @Transactional
    public List<Student> findAllStudents() {
        return studentRepository.findAllStudents();
    }
    @Transactional
    public void deleteStudentById(UUID studentId) {
        studentRepository.deleteStudentById(studentId);
    }
    @Transactional
    public List<Student> findGroupById(UUID groupId) {
        return studentRepository.findGroupById(groupId);
    }
    @Transactional
    public void updateStudent(UUID studentId, Student student) {
        studentRepository.updateStudent(studentId,student );
    }
}
