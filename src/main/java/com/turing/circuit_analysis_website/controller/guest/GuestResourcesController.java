package com.turing.circuit_analysis_website.controller.guest;

import com.turing.circuit_analysis_website.pojo.Course;
import com.turing.circuit_analysis_website.pojo.Introduce;
import com.turing.circuit_analysis_website.pojo.Resources;
import com.turing.circuit_analysis_website.service.ResourcesService;
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
@Api(tags = "其他资源信息查询接口")
@RestController
@RequestMapping("/guest/resources/")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
public class GuestResourcesController {
    @Autowired
    ResourcesService resourcesService;

    @ApiOperation(value = "查询所有资源信息", notes = "", httpMethod = "GET")
    @GetMapping(value = "/findAll")
    public JsonResult findAll() {
        List<Resources> resources = resourcesService.findAll();
        return JsonResult.success().addObject("resources", resources);
    }
    @ApiOperation(value = "查询比赛获奖图片的list", notes = "", httpMethod = "GET")
    @GetMapping(value = "/findImgList")
    public JsonResult findImgList() {
        List<Resources> list = resourcesService.findAllByType("list");
        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "查询实验讲义",notes = "",httpMethod = "GET")
    @GetMapping(value = "/findExperiment")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "每页多少条", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "page", value = "页数（0开始）", required = true, paramType = "query", dataType = "int")
    })
    public JsonResult findExperiment(int size, int page) {
        Page<Resources> list=resourcesService.findAllByType("exp",size,page);
        return JsonResult.success().addObject("exp", list);
    }
    @ApiOperation(value = "根据id查询资源信息", notes ="", httpMethod = "GET")
    @GetMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path", dataType = "int")
    })
    public JsonResult findById(@PathVariable Long id) {
        Resources resources = resourcesService.findById(id);
        return JsonResult.success().addObject("resources", resources);
    }

    @ApiOperation(value = "根据type查询信息", notes = "type参数\n" +
            "1.web:在线平台\n" +
            "2.test:试题\n" +
            "3.team:师资队伍\n" +
            "4.supporting:教学成果-支撑材料\n" +
            "5.outline:教学大纲\n" +
            "6.list:首页图片流水\n" +
            "7.figure:形象照\n" +
            "8.exp:实验指导书\n" +
            "9.construction:建设成果\n" +
            "10.achievements:教学成果\n" +
            "11.null:未知分类" , httpMethod = "GET")
    @GetMapping(value = "/findByType")
    public JsonResult findByType(String type) {
        List<Resources> list = resourcesService.findAllByType(type);
        return JsonResult.success().addObject("list", list);
    }
}
