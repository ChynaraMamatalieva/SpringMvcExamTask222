package springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springmvc.models.Group;
import springmvc.services.GroupService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/groups")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }


    @ModelAttribute("groups")
    public List<Group> findAllGroups() {
        return groupService.findAllGroups();
    }

    @GetMapping("find/by/{courseId}")
    public String findAllGroupsByCourseId(@PathVariable UUID courseId, Model model) {

        List<Group> groups = groupService.findByCourseId(courseId);
        model.addAttribute("courses", groups);
        model.addAttribute("courseId", courseId);
        return "groups/allGroups";
    }

    @GetMapping("/save/{courseId}")
    public String showCourseSavePage(@PathVariable UUID courseId, Model model) {
        model.addAttribute("courseId", courseId);
        model.addAttribute("emptyGroup", new Group());
        return "groups/saveNewGroup";
    }

    @PostMapping("/saveGroup/{courseId}")
    public String saveGroup(Group group, @PathVariable UUID courseId) {
        groupService.saveGroup(group, courseId);
        return "redirect:/api/groups/find/by/" + courseId;
    }

    @GetMapping("/update/{groupId}")
    public String updateGroup(Model model, @PathVariable UUID groupId) {
        Group group = groupService.findGroupById(groupId);
        model.addAttribute("updateGroup", group);
        return "groups/updateGroup";
    }

    @PostMapping("/update/{groupId}")
    public String updateGroup(Group group, @PathVariable UUID groupId) {

        Group byId = groupService.findGroupById(groupId);
        UUID id = byId.getCourses().get(0).getId();
        groupService.updateGroup(groupId, group);
        return "redirect:/api/groups/find/by/" + id;
    }
    @GetMapping("delete/{groupId}")
    public String deleteGroup(@PathVariable UUID groupId){
        Group group = groupService.findGroupById(groupId);
        UUID id = group.getCourses().get(0).getId();
        groupService.removeGroupById(groupId);
        return "redirect:/api/groups/find/by/"+id;
    }
}

