package com.turing.circuit_analysis_website.controller.admin;

import com.turing.circuit_analysis_website.service.TeacherService;
import com.turing.circuit_analysis_website.util.JsonResult;
import com.turing.circuit_analysis_website.vo.TeacherVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * @author CHEN
 * @date 2020/10/14  18:17
 */
@Api(tags = "教师信息查询接口")
@RestController
@RequestMapping("/admin/teacher/")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
public class AdminTeacherController {
    @Autowired
    TeacherService teacherService;

    @ApiOperation(value = "添加",notes = "",httpMethod = "POST")
    @PostMapping(value = "/add")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teacherName", value = "名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "teacherEmail", value = "邮箱", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "teacherBorn", value = "出生地", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "teacherPosition", value = "职称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "teacherJob", value = "职业", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "teacherGraduation", value = "毕业院校", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "teacherResearch", value = "主要研究领域和方向", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "teacherScientificResearch", value = "科研工作", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "teacherAwardIntroduction", value = "获奖情况", required = true, paramType = "query", dataType = "String"),

    })
    public JsonResult add(@Valid TeacherVo teacherVo, @RequestParam(value = "file") MultipartFile file) {
        teacherService.add(teacherVo,file);
        return JsonResult.success();
    }
    @ApiOperation(value = "更新",notes = "",httpMethod = "POST")
    @PostMapping(value = "/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teacherId", value = "信息id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "teacherName", value = "名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "teacherEmail", value = "邮箱", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "teacherBorn", value = "出生地", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "teacherPosition", value = "职称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "teacherJob", value = "职业", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "teacherGraduation", value = "毕业院校", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "teacherResearch", value = "主要研究领域和方向", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "teacherScientificResearch", value = "科研工作", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "teacherAwardIntroduction", value = "获奖情况", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "file", value = "文件可以没有", paramType = "form", dataType = "__file")
    })
    public JsonResult update(@Valid TeacherVo teacherVo, MultipartFile file) {
        teacherService.update(teacherVo,file);
        return JsonResult.success();
    }


    @ApiOperation(value = "删除",notes = "",httpMethod = "DELETE")
    @DeleteMapping(value = "/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "int"),
    })
    public JsonResult delete(Long id) {
        teacherService.delete(id);
        return JsonResult.success();
    }

}
