package com.turing.circuit_analysis_website.service.impl;

import com.turing.circuit_analysis_website.dao.RoleDao;
import com.turing.circuit_analysis_website.dao.UserDao;
import com.turing.circuit_analysis_website.pojo.Role;
import com.turing.circuit_analysis_website.util.MD5Util;
import com.turing.circuit_analysis_website.vo.UserAdminVo;
import com.turing.circuit_analysis_website.vo.UserVo;
import com.turing.circuit_analysis_website.enums.MyCustomizeErrorCode;
import com.turing.circuit_analysis_website.exception.CustomizeRuntimeException;
import com.turing.circuit_analysis_website.pojo.User;
import com.turing.circuit_analysis_website.service.UserService;
import com.turing.circuit_analysis_website.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/13  19:08
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;

    @Override
    public User getUserById(Long id) {
        User user = userDao.findUserByUserId(id);
        if (user == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_USER);
        }
        return user;
    }

    @Override
    public User getUserByUserName(String username) {
        User tem = userDao.findUserByUsername(username);
        if (tem == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_USER);
        }
        return tem;
    }

    @Override
    public List<User> findAll() {
        List<User> users = userDao.findAll();
        if (users == null || users.size() <= 0) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_USER);
        }
        return users;
    }

    @Override
    public void add(UserAdminVo user, Long role_id) {
        User tem = userDao.findUserByUsername(user.getUsername());
        if (tem != null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.USER_HAVE_EXISTENCE);
        }
        if (role_id == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_ROLE);
        }
        Role role = roleDao.findByRid(role_id);
        if (role == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_ROLE);
        }

        User newOne = new User();
        newOne.setPassword(MD5Util.getHexPassword(user.getPassword()));
        newOne.setUsername(user.getUsername());
        newOne.setCollege(user.getCollege());
        newOne.setGender(user.getGender());
        newOne.setMajor(user.getMajor());
        newOne.setUser_class(user.getUser_class());
        newOne.setName(user.getName());
        List<Role> roles = new LinkedList<>();
        roles.add(role);
        newOne.setRoles(roles);
        userDao.saveAndFlush(newOne);
    }

    @Override
    public void add(UserVo user, Long role_id) {
        User tem = userDao.findUserByUsername(user.getUsername());
        if (tem != null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.USER_HAVE_EXISTENCE);
        }
        if (role_id == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_ROLE);
        }
        Role role = roleDao.findByRid(role_id);
        if (role == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_ROLE);
        }

        User newOne = new User();
        newOne.setPassword(MD5Util.getHexPassword(user.getPassword()));
        newOne.setUsername(user.getUsername());
        newOne.setCollege(user.getCollege());
        newOne.setGender(user.getGender());
        newOne.setMajor(user.getMajor());
        newOne.setUser_class(user.getUser_class());
        newOne.setName(user.getName());
        List<Role> roles = new LinkedList<>();
        roles.add(role);
        newOne.setRoles(roles);
        userDao.saveAndFlush(newOne);

    }

    @Override
    public void update(UserAdminVo user, Long role_id) {
        if (user.getId() == 0L) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.ROOT_ADMIN_CANT_NOT_CHANGE);
        }
        User tem = userDao.findUserByUserId(user.getId());
        if (tem == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_USER);
        }
        if(!user.getUsername().equals(tem.getUsername())) {//判重复
            User tem2 = userDao.findUserByUsername(user.getUsername());
            if (tem2 != null) {
                throw new CustomizeRuntimeException(MyCustomizeErrorCode.USER_HAVE_EXISTENCE);
            }
        }
        if (role_id != null) {
            Role role = roleDao.findByRid(role_id);
            if (role == null) {
                throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_ROLE);
            }
            List<Role> roles = new LinkedList<>();
            roles.add(role);
            tem.setRoles(roles);
        }
        //密码不为空更新密码
        if(user.getPassword()!=null&&user.getPassword().length()>0){
            tem.setPassword(MD5Util.getHexPassword(user.getPassword()));
        }
        tem.setUsername(user.getUsername());
        tem.setCollege(user.getCollege());
        tem.setGender(user.getGender());
        tem.setMajor(user.getMajor());
        tem.setUser_class(user.getUser_class());
        tem.setName(user.getName());

        userDao.saveAndFlush(tem);
    }

    @Override
    public void update(UserVo user, Long role_id) {
        if (user.getId() == 0L) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.ROOT_ADMIN_CANT_NOT_CHANGE);
        }
        User tem = userDao.findUserByUserId(user.getId());
        if (tem == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_USER);
        }
        if(!user.getUsername().equals(tem.getUsername())) {//判重复
            User tem2 = userDao.findUserByUsername(user.getUsername());
            if (tem2 != null) {
                throw new CustomizeRuntimeException(MyCustomizeErrorCode.USER_HAVE_EXISTENCE);
            }
        }
        if (role_id != null) {
            Role role = roleDao.findByRid(role_id);
            if (role == null) {
                throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_ROLE);
            }
            List<Role> roles = new LinkedList<>();
            roles.add(role);
            tem.setRoles(roles);
        }
        //密码不为空更新密码
        if(user.getPassword()!=null&&user.getPassword().length()>0){
            tem.setPassword(MD5Util.getHexPassword(user.getPassword()));
        }
        tem.setUsername(user.getUsername());
        tem.setCollege(user.getCollege());
        tem.setGender(user.getGender());
        tem.setMajor(user.getMajor());
        tem.setUser_class(user.getUser_class());
        tem.setName(user.getName());

        userDao.saveAndFlush(tem);

    }

    @Override
    public void delete(Long id) {
        if (id == 0L) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.ROOT_ADMIN_CANT_NOT_CHANGE);
        }
        User user = userDao.findUserByUserId(id);
        if (user == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_USER);
        }
        userDao.delete(user);

    }
}
