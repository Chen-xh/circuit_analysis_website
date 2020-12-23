package com.turing.circuit_analysis_website.service;

import com.turing.circuit_analysis_website.pojo.Teacher;
import com.turing.circuit_analysis_website.vo.TeacherVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/13  19:07
 */
public interface TeacherService {
    Teacher getTeacherById(Long id);

    List<Teacher> findAll();

    void add(TeacherVo Teacher, MultipartFile file);

    void update(TeacherVo Teacher, MultipartFile file);

    void delete(Long id);
}
