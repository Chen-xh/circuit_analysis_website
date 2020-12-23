package com.turing.circuit_analysis_website.dao;

import com.turing.circuit_analysis_website.pojo.Resources;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/12  17:27
 */
public interface ResourcesDao extends JpaRepository<Resources, Long>, PagingAndSortingRepository<Resources, Long> {
    Resources findByResId(Long id);
    List<Resources> findAllByType(String type);
    Page<Resources> findAllByType(String type, Pageable pageable);
}