package springmvc.services;

import org.springframework.stereotype.Service;
import springmvc.models.Course;
import springmvc.models.Group;
import springmvc.repositories.CourseRepository;
import springmvc.repositories.GroupRepository;


import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;

    public GroupService(GroupRepository groupRepository, CourseRepository courseRepository) {
        this.groupRepository = groupRepository;
        this.courseRepository = courseRepository;
    }
    @Transactional
    public void saveGroup(Group group, UUID courseId) {
        Course course = courseRepository.findCourseById(courseId);
        group.setCourse(course);
        course.setGroup(group);
        groupRepository.saveGroup(group);
    }


    public Group findGroupById(UUID groupID) {
        return groupRepository.findGroupById(groupID);
    }


    public List<Group> findAllGroups() {
        return groupRepository.findAllGroups();
    }

    public void removeGroupById(UUID groupId) {
        groupRepository.removeGroupById(groupId);
    }

    public List<Group> findByCourseId(UUID courseId) {
        return groupRepository.findCourseById(courseId);
    }

    public void updateGroup(UUID groupId, Group group) {
        groupRepository.updateGroup(groupId, group);
    }
}
