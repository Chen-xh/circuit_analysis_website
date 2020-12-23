package com.turing.circuit_analysis_website.dao;

import com.turing.circuit_analysis_website.pojo.Chapter;
import com.turing.circuit_analysis_website.pojo.Role;
import com.turing.circuit_analysis_website.pojo.TestSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author CHEN
 * @date 2020/10/12  17:27
 */
public interface RoleDao extends JpaRepository<Role, Long>, PagingAndSortingRepository<Role, Long> {
    Role findByRid(Long id);
}