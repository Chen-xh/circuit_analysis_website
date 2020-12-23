package com.turing.circuit_analysis_website.controller.admin;

import com.turing.circuit_analysis_website.service.CourseService;
import com.turing.circuit_analysis_website.util.JsonResult;
import com.turing.circuit_analysis_website.vo.CourseVo;
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
@Api(tags = "课程信息管理接口")
@RestController
@RequestMapping("/admin/course/")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
public class AdminCourseController {
    @Autowired
    CourseService courseService;

    @ApiOperation(value = "添加",notes = "",httpMethod = "POST")
    @PostMapping(value = "/add")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "标题", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "CId", value = "课程章节id", required = true, paramType = "query", dataType = "int")
    })
    public JsonResult add(@Valid CourseVo courseVo,  @RequestParam(value = "file") MultipartFile file) {
        courseService.add(courseVo,file);
        return JsonResult.success();
    }
    @ApiOperation(value = "更新",notes = "",httpMethod = "POST")
    @PostMapping(value = "/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "课程id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "title", value = "标题", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "CId", value = "课程章节id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "file", value = "文件可以没有", paramType = "form", dataType = "__file")
    })
    public JsonResult update(@Valid CourseVo courseVo,  MultipartFile file) {
        courseService.update(courseVo,file);
        return JsonResult.success();
    }


    @ApiOperation(value = "删除",notes = "",httpMethod = "DELETE")
    @DeleteMapping(value = "/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "课程id", required = true, paramType = "query", dataType = "int"),
    })
    public JsonResult delete(Long id) {
        courseService.delete(id);
        return JsonResult.success();
    }
}
