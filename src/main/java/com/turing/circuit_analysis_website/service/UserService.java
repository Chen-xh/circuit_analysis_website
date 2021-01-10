package com.turing.circuit_analysis_website.service;

import com.turing.circuit_analysis_website.pojo.User;
import com.turing.circuit_analysis_website.util.JsonResult;
import com.turing.circuit_analysis_website.vo.UserAdminVo;
import com.turing.circuit_analysis_website.vo.UserVo;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/13  19:07
 */
public interface UserService {
    User getUserById(Long id);

    User getUserByUserName(String username);

    List<User> findAll();

    void add(UserAdminVo user , Long role_id);
    void add(UserVo user ,Long role_id);

    void update(UserAdminVo user ,Long role_id);
    void update(UserVo user ,Long role_id);

    void delete(Long id);
}
