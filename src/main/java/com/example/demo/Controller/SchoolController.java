package com.example.demo.Controller;

import com.example.demo.Bean.School;
import com.example.demo.Service.SchoolService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SchoolController {
    @Resource
    private SchoolService schoolService;

    @GetMapping("/getAllSchool")
    public List<School> selectSchool(){
        return schoolService.selectAllSchool();
    }

    @GetMapping("/getName")
    public String getName(String id){
        return "张三push";
    }
}
