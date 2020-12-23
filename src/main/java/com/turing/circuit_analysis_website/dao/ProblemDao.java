package com.turing.circuit_analysis_website.dao;

import com.turing.circuit_analysis_website.pojo.Chapter;
import com.turing.circuit_analysis_website.pojo.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/12  17:27
 */
public interface ProblemDao extends JpaRepository<Problem, Long>, PagingAndSortingRepository<Problem, Long> {
    Problem findByProId(Long id);
}