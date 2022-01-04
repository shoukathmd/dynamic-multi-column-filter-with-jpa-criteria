package com.spring.multifield.filter.predicate.rest.student;

import com.spring.multifield.filter.predicate.dao.entity.StudentDAO;
import com.spring.multifield.filter.predicate.services.StudentService;
import com.spring.multifield.filter.predicate.dto.FilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(
            StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/apply", method = RequestMethod.PUT)
    public List<StudentDAO> apply(@RequestBody(required = false) FilterRequest filterRequest) {
        return studentService.applyFilter(filterRequest);
    }
}


