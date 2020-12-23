package com.turing.circuit_analysis_website.dao;

import com.turing.circuit_analysis_website.pojo.Chapter;
import com.turing.circuit_analysis_website.pojo.Comment;
import com.turing.circuit_analysis_website.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author CHEN
 * @date 2020/10/12  17:27
 */
public interface ChapterDao extends JpaRepository<Chapter, Long>, PagingAndSortingRepository<Chapter, Long> {
    Chapter findByChaId(Long id);

}