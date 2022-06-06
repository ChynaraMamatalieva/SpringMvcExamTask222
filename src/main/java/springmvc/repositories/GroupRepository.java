package springmvc.repositories;

import org.springframework.stereotype.Repository;
import springmvc.models.Group;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public class GroupRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public GroupRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Transactional
    public void saveGroup(Group group) {
        entityManager.persist(group);

    }

    @Transactional
    public Group findGroupById(UUID groupID) {
        return entityManager.find(Group.class, groupID);
    }

    @Transactional
    public List<Group> findAllGroups() {
        return entityManager.createQuery("select g from Group g", Group.class)
                .getResultList();
    }
    @Transactional
    public void removeGroupById(UUID groupId) {
        entityManager.remove(findGroupById(groupId));
    }
    @Transactional
    public List<Group> findCourseById(UUID courseId) {
        return entityManager.createQuery("select g from Group g where (select c from Course c where c.id = ?1) member of g.courses", Group.class)
                .setParameter(1, courseId).getResultList();
    }
    @Transactional
    public void updateGroup(UUID groupId, Group group) {
        Group group1 = findGroupById(groupId);
        group1.setGroupName(group.getGroupName());
        group1.setDateOfStart(group.getDateOfStart());
        group1.setDateOfFinish(group.getDateOfFinish());
        entityManager.persist(group1);
    }
}
