package top.yekongle.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.yekongle.redis.entity.Student;
import top.yekongle.redis.service.StudentService;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/queryStudent")
    public Student queryStudent(String sno) {
       return studentService.queryStudentBySno(sno);
    }

    @PostMapping("/updateStudent")
    public Student updateStudent(@RequestBody Student student) {
        studentService.update(student);
        return studentService.queryStudentBySno(student.getSno());
    }

    @DeleteMapping("/deleteStudent")
    public String deleteStudent(String sno) {
        studentService.deleteStudentBySno(sno);
        return "Success";
    }
}
