package com.turing.circuit_analysis_website.dao;

import com.turing.circuit_analysis_website.pojo.CourseWare;
import com.turing.circuit_analysis_website.pojo.Introduce;
import com.turing.circuit_analysis_website.pojo.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author CHEN
 * @date 2020/10/12  17:27
 */
public interface CourseWareDao extends JpaRepository<CourseWare, Long>, PagingAndSortingRepository<CourseWare, Long> {
    CourseWare findByCourId(Long id);

}