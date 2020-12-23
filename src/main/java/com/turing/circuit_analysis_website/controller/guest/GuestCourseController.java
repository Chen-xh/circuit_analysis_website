package com.turing.circuit_analysis_website.controller.guest;

import com.turing.circuit_analysis_website.pojo.Comment;
import com.turing.circuit_analysis_website.pojo.Course;
import com.turing.circuit_analysis_website.service.CourseService;
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
@Api(tags = "课程信息查询接口")
@RestController
@RequestMapping("/guest/course/")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
public class GuestCourseController {
    @Autowired
    CourseService courseService;

    @ApiOperation(value = "查询所有课程信息",notes = "",httpMethod = "GET")
    @GetMapping(value = "/findAll")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "每页多少条", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "page", value = "页数（0开始）", required = true, paramType = "query", dataType = "int")
    })
    public JsonResult findAll(int size, int page) {
        Page<Course> list=courseService.findAll(size,page);
        return JsonResult.success().addObject("course",list);
    }
    @ApiOperation(value = "根据id查询课程信息",notes = "",httpMethod = "GET")
    @GetMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path", dataType = "int")
    })
    public JsonResult findById(@PathVariable Long id) {
        Course course=courseService.findById(id);
        return JsonResult.success().addObject("course",course);
    }


    @ApiOperation(value = "根据章节查询课程信息",notes = "",httpMethod = "GET")
    @GetMapping("/findByCId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "int")
    })
    public JsonResult findByCId(Long id) {
        List<Course> list=courseService.findByCid(id);
        return JsonResult.success().addObject("course",list);
    }
}
