package com.turing.circuit_analysis_website.service;

import com.turing.circuit_analysis_website.pojo.Course;
import com.turing.circuit_analysis_website.util.JsonResult;
import com.turing.circuit_analysis_website.vo.CourseVo;
import com.turing.circuit_analysis_website.vo.CourseWareVo;
import com.turing.circuit_analysis_website.vo.ProblemVo;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/13  20:28
 */
public interface CourseService {
    Page<Course> findAll(int size, int page);
    Course findById(long id);

    List<Course> findByCid(Long cid);

    void add(CourseVo courseVo, MultipartFile file);

    void update(CourseVo courseVo, MultipartFile file);

    void delete(Long id);


}
