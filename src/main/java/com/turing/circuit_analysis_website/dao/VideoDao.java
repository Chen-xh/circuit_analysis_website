package com.turing.circuit_analysis_website.dao;

import com.turing.circuit_analysis_website.pojo.Chapter;
import com.turing.circuit_analysis_website.pojo.User;
import com.turing.circuit_analysis_website.pojo.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author CHEN
 * @date 2020/10/12  17:27
 */
public interface VideoDao extends JpaRepository<Video, Long>, PagingAndSortingRepository<Video, Long> {
    Video findByVidId(Long id);
}