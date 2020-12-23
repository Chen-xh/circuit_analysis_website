package com.turing.circuit_analysis_website.dao;

import com.turing.circuit_analysis_website.pojo.Chapter;
import com.turing.circuit_analysis_website.pojo.Course;
import com.turing.circuit_analysis_website.pojo.CourseWare;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/12  17:27
 */
public interface CourseDao extends JpaRepository<Course, Long>, PagingAndSortingRepository<Course, Long> {
    List<Course> findByChapter(Chapter chapter);
    Course findByCouId(Long id);

}