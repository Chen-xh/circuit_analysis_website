package com.turing.circuit_analysis_website.controller.admin;

import com.turing.circuit_analysis_website.pojo.User;
import com.turing.circuit_analysis_website.service.UserService;
import com.turing.circuit_analysis_website.util.JsonResult;
import com.turing.circuit_analysis_website.vo.UserAdminVo;
import com.turing.circuit_analysis_website.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/14  18:17
 */
@Api(tags = "用户信息管理接口")
@RestController
@RequestMapping("/admin/user/")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
public class AdminUserController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "根据id查询信息",notes = "",httpMethod = "GET")
    @GetMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path", dataType = "int")
    })
    public JsonResult findById(@PathVariable Long id) {
        User user=userService.getUserById(id);
        return JsonResult.success().addObject("user",user);
    }
    @ApiOperation(value = "查询所有信息",notes = "",httpMethod = "GET")
    @GetMapping("/findAll")
    @ApiImplicitParams({
    })
    public JsonResult findAll() {
        List<User> user=userService.findAll();
        return JsonResult.success().addObject("user",user);
    }

    @ApiOperation(value = "添加", notes = "", httpMethod = "POST")
    @PostMapping(value = "/add")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名（6-12）", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码（3-16）", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "rId", value = "角色的id(1 admin,2 student 3 teacher)", required = true, paramType = "query", dataType = "int"),
    })
    public JsonResult add(@Valid UserAdminVo userAdminVo, Long rId) {

        userService.add(userAdminVo, rId);

        return JsonResult.success();
    }

    @ApiOperation(value = "更新", notes = "", httpMethod = "POST")
    @PostMapping(value = "/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "username", value = "用户名（6-12）", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码（3-16）,可以不改", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "rId", value = "角色的id,可以不改",  paramType = "query", dataType = "int"),
    })
    public JsonResult update(@Valid UserAdminVo userAdminVo, Long rId) {

        userService.update(userAdminVo,rId);
        return JsonResult.success();
    }


    @ApiOperation(value = "删除", notes = "", httpMethod = "DELETE")
    @DeleteMapping(value = "/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "query", dataType = "int"),
    })
    public JsonResult delete(Long id) {
        userService.delete(id);
        return JsonResult.success();
    }

}
