package com.turing.circuit_analysis_website.controller.guest;

import com.turing.circuit_analysis_website.pojo.Course;
import com.turing.circuit_analysis_website.pojo.CourseWare;
import com.turing.circuit_analysis_website.service.ChapterService;
import com.turing.circuit_analysis_website.service.CourseWareService;
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
@Api(tags = "课件信息查询接口")
@RestController
@RequestMapping("/guest/courseWare/")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
public class GuestCourseWareController {
    @Autowired
    CourseWareService courseWareService;

    @ApiOperation(value = "查询所有课件信息",notes = "",httpMethod = "GET")
    @GetMapping(value = "/findAll")
    public JsonResult findAll() {
        List<CourseWare> courseWares=courseWareService.findAll();
        return JsonResult.success().addObject("courseWares",courseWares);
    }
    @ApiOperation(value = "分页查询所有课件信息",notes = "",httpMethod = "GET")
    @GetMapping(value = "/findAllByPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "一页多少条数据", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "page", value = "当前页数(0开始)", required = true, paramType = "query", dataType = "int")
    })
    public JsonResult findAll(Integer size ,Integer page) {
        Page<CourseWare> courseWares=courseWareService.findAll(size,page);
        return JsonResult.success().addObject("courseWares",courseWares);
    }
    @ApiOperation(value = "根据id查询课件信息",notes = "",httpMethod = "GET")
    @GetMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path", dataType = "int")
    })
    public JsonResult findById(@PathVariable Long id) {
        CourseWare course=courseWareService.findById(id);
        return JsonResult.success().addObject("course",course);
    }
}
