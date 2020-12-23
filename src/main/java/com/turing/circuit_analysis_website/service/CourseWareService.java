package com.turing.circuit_analysis_website.service;

import com.turing.circuit_analysis_website.pojo.Course;
import com.turing.circuit_analysis_website.pojo.CourseWare;
import com.turing.circuit_analysis_website.pojo.Video;
import com.turing.circuit_analysis_website.util.JsonResult;
import com.turing.circuit_analysis_website.vo.CourseWareVo;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/13  20:28
 */
public interface CourseWareService {
    List<CourseWare> findAll();
    Page<CourseWare> findAll(int size,int page);
    CourseWare findById(long id);
    void add(CourseWareVo courseWare, MultipartFile file);

    void update(CourseWareVo courseWare, MultipartFile file);

    void delete(Long id);
}
