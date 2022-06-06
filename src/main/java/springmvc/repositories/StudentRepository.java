package springmvc.repositories;

import org.springframework.stereotype.Repository;
import springmvc.models.Student;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public class StudentRepository {
    @PersistenceContext
    private final EntityManager entityManager;


    public StudentRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }
    @Transactional
    public void saveStudent(Student student) {
        entityManager.persist(student);
    }

    @Transactional
    public Student findStudentById(UUID studentId) {
        return entityManager.find(Student.class, studentId);
    }
    @Transactional
    public List<Student> findAllStudents() {
        return entityManager.createQuery("select s from Student s", Student.class)
                .getResultList();
    }
    @Transactional
    public void deleteStudentById(UUID studentId) {
        entityManager.createQuery("delete from Student s where s.id = ?1")
                .setParameter(1, studentId)
                .executeUpdate();
    }

    @Transactional
    public List<Student> findGroupById(UUID groupId) {
        return entityManager.createQuery("select s from Student s where s.group.id=?1", Student.class)
                .setParameter(1, groupId).getResultList();
    }
    @Transactional
    public void updateStudent(UUID studentId, Student student) {
        Student student1 = findStudentById(studentId);
        student1.setFirstName(student.getFirstName());
        student1.setEmail(student.getEmail());
        student1.setLastName(student.getLastName());
        student1.setStudyFormat(student.getStudyFormat());
    }
}
