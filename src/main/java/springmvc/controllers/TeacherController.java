package springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springmvc.models.Teacher;
import springmvc.services.TeacherService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    @GetMapping("/find/by/{courseId}")
    public String findTeacherByCourseId(@PathVariable UUID courseId, Model model) {
        List<Teacher> teachers = teacherService.findCourseById(courseId);
        model.addAttribute("courseId", courseId);
        model.addAttribute("teachers", teacherService.findAllTeachers());
        return "/teachers/allTeachers";
    }

    @GetMapping("/save/{courseId}")
    public String showTeacherSavePage(@PathVariable UUID courseId, Model model) {
        model.addAttribute("courseId", courseId);
        model.addAttribute("emptyTeacher", new Teacher());
        return "/teachers/saveNewTeacher";
    }

    @PostMapping("/save/{courseId}")
    public String saveTeacher(@PathVariable("courseId") UUID courseId, Teacher teacher) {
        teacherService.saveTeacher(teacher, courseId);
        return "redirect:/api/teachers/find/by/" + courseId;
    }
    @GetMapping("/update/{teacherId}")
    public String updateTeacher(Model model, @PathVariable UUID teacherId){
        Teacher teacher = teacherService.findTeacherById(teacherId);
        model.addAttribute("updateTeacher", teacher);
        return "/teachers/updateTeacher";
    }
    @PostMapping("/update/{teacherId}")
    public String updateTeacher(Teacher teacher, @PathVariable UUID teacherId){
        Teacher id = teacherService.findTeacherById(teacherId);
        UUID id1 = id.getCourse().getId();
        teacherService.updateTeacher(teacherId,teacher);
        return "redirect:/api/teachers/find/by/" + id1;
    }

    @GetMapping("/delete/{teacherId}")
    public String deleteTeacher(@PathVariable("teacherId") UUID teacherId, Model model){
        System.out.println("teacherId = " + teacherId);

        Teacher teacher = teacherService.findTeacherById(teacherId);

        UUID id1 = teacher.getCourse().getId();

        teacherService.deleteTeacherById(teacherId);


        model.addAttribute("courseId", id1);
        model.addAttribute("teachers", teacherService.findAllTeachers());

        return "/teachers/allTeachers";
    }
}
