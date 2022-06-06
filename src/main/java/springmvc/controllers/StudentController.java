package springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springmvc.models.Student;
import springmvc.services.StudentService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

//    @ModelAttribute("studentList")
//    public List<Student> courses() {
//        return studentService.findAllStudents();
//    }

    @GetMapping("find/by/{groupId}")
    public String findAllStudentsByGroupId(@PathVariable UUID groupId, Model model) {
        List<Student> students = studentService.findGroupById(groupId);
        model.addAttribute("students", students);
        model.addAttribute("groupId", groupId);
        return "students/allStudents";
    }

    @GetMapping("/save/{groupId}")
    public String showStudentSavePage(@PathVariable UUID groupId, Model model) {
        model.addAttribute("emptyStudent", new Student());
        model.addAttribute("groupId", groupId);
        return "students/saveNewStudent";
    }
    @PostMapping("/save/{groupId}")
    public String saveStudent(@PathVariable UUID groupId, Student student) {
        studentService.saveStudent(student, groupId);
        return "redirect:/api/students/find/by/" + groupId;
    }

    @GetMapping("/update/{studentId}")
    public String updateStudent(Model model, @PathVariable UUID studentId){
        Student student1 = studentService.findStudentById(studentId);
        model.addAttribute("updateStudent", student1);
        return "students/updateStudent";
    }
    @PostMapping ("/update/{studentId}")
    public String update(Student student, @PathVariable UUID studentId){
        Student byId = studentService.findStudentById(studentId);
        UUID id = byId.getGroup().getId();
        studentService.updateStudent(studentId, student);
        return "redirect:/api/students/find/by/" + id;
    }
    @GetMapping ("/delete/{studentId}")
    public String delete(@PathVariable UUID studentId){
        Student id1 = studentService.findStudentById(studentId);
        UUID uuid = id1.getGroup().getId();
        studentService.deleteStudentById(studentId);
        return "redirect:/api/students/find/by/"+uuid;
    }
}
