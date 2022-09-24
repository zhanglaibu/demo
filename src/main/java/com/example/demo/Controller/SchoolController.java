package com.example.demo.Controller;

import com.example.demo.Bean.School;
import com.example.demo.Service.SchoolService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SchoolController {
    @Resource
    private SchoolService schoolService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/getAllSchool")
    public List<School> selectSchool(){
        return schoolService.selectAllSchool();
    }

    @GetMapping("/getName/{id}")
    public String getName(@PathVariable String id){
        stringRedisTemplate.opsForValue().set("001","zhangsan");
        String name = stringRedisTemplate.opsForValue().get(id);
        return name;
    }
}
