package com.example.demo.Service.Imp;

import com.example.demo.Bean.School;
import com.example.demo.Dao.SchoolDao;
import com.example.demo.Service.SchoolService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SchoolServiceImp implements SchoolService {
    @Resource
    private SchoolDao schoolDao;
    @Override
    public List<School> selectAllSchool() {
        return schoolDao.selectAllSchool();
    }
}
