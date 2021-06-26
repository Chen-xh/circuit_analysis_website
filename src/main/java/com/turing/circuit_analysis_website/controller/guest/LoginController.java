package com.turing.circuit_analysis_website.controller.guest;


import com.turing.circuit_analysis_website.pojo.User;
import com.turing.circuit_analysis_website.vo.LoginVo;
import com.turing.circuit_analysis_website.service.UserService;
import com.turing.circuit_analysis_website.util.JWTToken;
import com.turing.circuit_analysis_website.util.JWTUtil;
import com.turing.circuit_analysis_website.util.JsonResult;
import com.turing.circuit_analysis_website.util.MD5Util;
import com.turing.circuit_analysis_website.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author CHEN
 * @date 2020/4/26 22:39
 */
@Api(tags = "登录接口")
@RestController
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
public class LoginController {
    @Autowired
    UserService userService;

    private static final Logger PLOG = LoggerFactory.getLogger(LoginController.class);

    @ApiOperation(value = "用户登录接口", notes = "默认账号tes 123 ，角色是admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", dataType = "String")
    })
    @PostMapping("login")
    public JsonResult userLogin(@Valid LoginVo loginVo) {
        String passwords = MD5Util.getHexPassword(loginVo.getPassword());
        System.out.println(passwords);
        JWTToken token = new JWTToken(JWTUtil.sign(loginVo.getUsername(), passwords));
        Subject subject = SecurityUtils.getSubject();
        //登录认证
        subject.login(token);

        PLOG.info("LoginController >> login · 获取Token");

        return JsonResult.success().addObject("token", token.getPrincipal());

    }

    @ApiOperation(value = "管理员登录接口", notes = "默认账号test 123 ，角色是admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", dataType = "String")
    })
    @PostMapping("adminLogin")
    public JsonResult adminLogin(String username, String password) {
        String passwords = MD5Util.getHexPassword(password);
        System.out.println(passwords);
        JWTToken token = new JWTToken(JWTUtil.sign(username, passwords));
        Subject subject = SecurityUtils.getSubject();
        //登录认证
        subject.login(token);
        User user=userService.getUserByUserName(username);
        if(!user.getRoles().get(0).getRoleName().equals("admin")){
            return JsonResult.fail();
        }
        PLOG.info("LoginController >> login · 获取Token");

        return JsonResult.success().addObject("token", token.getPrincipal());

    }

    @ApiOperation(value = "用户注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "college", value = "学院", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "gender", value = "性别", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "user_class", value = "班级", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "姓名", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "major", value = "专业", required = true, paramType = "query", dataType = "String")
    })
    @PostMapping("register")
    public JsonResult userRegister(@Valid UserVo userVo) {
        userService.add(userVo, 2L);
        return JsonResult.success();

    }

    @ApiOperation(value = "用户退出接口")
    @PostMapping("logout")
    public JsonResult logout() {
        Subject subject = SecurityUtils.getSubject();
        //登录认证
        subject.logout();
        return JsonResult.success();
    }

    @GetMapping("/error/{message}")
    public JsonResult error(@PathVariable String message) {
        return JsonResult.errorOf(200, message);
    }
}
