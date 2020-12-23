package com.turing.circuit_analysis_website.dao;

import com.turing.circuit_analysis_website.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author CHEN
 * @date 2020/10/12  17:27
 */
public interface UserDao  extends JpaRepository<User, Long>, PagingAndSortingRepository<User, Long> {
    User findUserByUserId( Long id);
    User findUserByUsername( String string);
}