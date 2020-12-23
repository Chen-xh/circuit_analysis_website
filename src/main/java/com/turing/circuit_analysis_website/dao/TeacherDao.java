package com.turing.circuit_analysis_website.dao;

import com.turing.circuit_analysis_website.pojo.Teacher;
import com.turing.circuit_analysis_website.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author CHEN
 * @date 2020/10/12  17:27
 */
public interface TeacherDao extends JpaRepository<Teacher, Long>, PagingAndSortingRepository<Teacher, Long> {
    Teacher findByTeacherId(Long id);

}