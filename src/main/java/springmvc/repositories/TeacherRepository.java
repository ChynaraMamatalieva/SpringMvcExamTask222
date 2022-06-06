package springmvc.repositories;

import org.springframework.stereotype.Repository;
import springmvc.models.Teacher;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public class TeacherRepository {

    private final EntityManager entityManager;

    public TeacherRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }
    public void saveTeacher(Teacher teacher) {
        entityManager.getTransaction().begin();
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();
    }

    public Teacher findTeacherById(UUID teacherId) {
        return entityManager.find(Teacher.class, teacherId);
    }

    public List<Teacher> findAllTeachers() {
        return entityManager.createQuery("select t from Teacher t", Teacher.class)
                .getResultList();
    }

    @Transactional
    public void deleteTeacherById(UUID teacherId) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        entityManager.remove(entityManager.find(Teacher.class, teacherId));

        transaction.commit();
    }

    @Transactional
    public List<Teacher> findCourseById(UUID courseId) {
        List<Teacher> teachers = entityManager.createQuery("select t from Teacher t where t.course.id=?1", Teacher.class)
                .setParameter(1, courseId).getResultList();

        return teachers;
    }
    @Transactional
    public void updateTeacher(UUID teacherId, Teacher teacher) {
        Teacher teacher1 = findTeacherById(teacherId);
        teacher1.setFirstName(teacher.getFirstName());
        teacher1.setEmail(teacher.getEmail());
        teacher1.setLastName(teacher.getLastName());
        entityManager.persist(teacher1);
    }

}
