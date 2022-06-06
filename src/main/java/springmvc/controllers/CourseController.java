package springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springmvc.models.Course;
import springmvc.services.CompanyService;
import springmvc.services.CourseService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;
    private final CompanyService companyService;

    public CourseController(CourseService courseService, CompanyService companyService) {
        this.courseService = courseService;
        this.companyService = companyService;
    }
    @ModelAttribute("courses")
    public List<Course> courses(){
        return courseService.findAllCourses();
    }
    @GetMapping("find/by/{companyId}")
    public String findAllCoursesByCompanyId(@PathVariable UUID companyId, Model model) {

        List<Course> courses = courseService.findCompanyById(companyId);
        model.addAttribute("courses", courses);
        model.addAttribute("companyId", companyId);
        return "courses/allCourses";
    }

    @GetMapping("/save/{companyId}")
    public String showCourseSavePage(@PathVariable UUID companyId, Model model) {
        model.addAttribute("companyId", companyId);
        model.addAttribute("emptyCourse", new Course());
        return "courses/saveNewCourse";

    }
    @PostMapping("/save/{companyId}")
    public String saveCourse(Course course, @PathVariable UUID companyId){
        courseService.saveCourse(course, companyId);
        return "redirect:/api/courses/find/by/"+companyId;

    }
    @GetMapping("/update/{courseId}")
    public String updateCourse(Model model, @PathVariable UUID courseId){
        Course course = courseService.findCourseById(courseId);
        model.addAttribute("updateCourse", course);
        return "courses/updateCourse";
    }

    @PostMapping ("/update/{courseId}")
    public String updateCourse(Course course, @PathVariable UUID courseId){
        Course courseById = courseService.findCourseById(courseId);
        UUID id = courseById.getCompany().getId();
        courseService.updateCourse(courseId, course);
        return "redirect:/api/courses/find/by/" + id;
    }
    @GetMapping("/delete/{courseId}")
    public String deleteCourse(@PathVariable UUID courseId){
        Course course = courseService.findCourseById(courseId);
        UUID id = course.getCompany().getId();
        courseService.deleteCourseById(courseId);
        return "redirect:/api/courses/find/by/"+id;
    }
}


