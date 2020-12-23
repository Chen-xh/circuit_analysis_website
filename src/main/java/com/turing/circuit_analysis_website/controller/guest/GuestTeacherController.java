package com.turing.circuit_analysis_website.controller.guest;

import com.turing.circuit_analysis_website.pojo.Teacher;
import com.turing.circuit_analysis_website.service.TeacherService;
import com.turing.circuit_analysis_website.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/14  18:17
 */
@Api(tags = "教师信息查询接口")
@RestController
@RequestMapping("/guest/teacher/")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
public class GuestTeacherController {
    @Autowired
    TeacherService teacherService;

    @ApiOperation(value = "查询所有信息",notes = "",httpMethod = "GET")
    @GetMapping(value = "/findAll")
    public JsonResult findAll() {
        List<Teacher> list=teacherService.findAll();
        return JsonResult.success().addObject("teacher",list);
    }
    @ApiOperation(value = "根据id查询信息",notes = "",httpMethod = "GET")
    @GetMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path", dataType = "int")
    })
    public JsonResult findById(@PathVariable Long id) {
        Teacher Teacher=teacherService.getTeacherById(id);
        return JsonResult.success().addObject("teacher",Teacher);
    }


}
