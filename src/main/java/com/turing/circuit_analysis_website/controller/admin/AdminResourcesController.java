package com.turing.circuit_analysis_website.controller.admin;

import com.turing.circuit_analysis_website.service.ResourcesService;
import com.turing.circuit_analysis_website.util.JsonResult;
import com.turing.circuit_analysis_website.vo.ResourcesVo;
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
@Api(tags = "其他资源信息管理接口")
@RestController
@RequestMapping("/admin/resources/")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
public class AdminResourcesController {
    @Autowired
    ResourcesService resourcesService;

    @ApiOperation(value = "添加",notes = "",httpMethod = "POST")
    @PostMapping(value = "/add")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "标题", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "destination", value = "描述", required = true, paramType = "query", dataType = "String")
    })
    public JsonResult add(@Valid ResourcesVo ResourcesVo, @RequestParam(value = "file") MultipartFile file) {
        resourcesService.add(ResourcesVo,file);
        return JsonResult.success();
    }
    @ApiOperation(value = "更新",notes = "",httpMethod = "POST")
    @PostMapping(value = "/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "资源id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "title", value = "标题", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "destination", value = "描述", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "file", value = "文件可以没有", paramType = "form", dataType = "__file")
    })
    public JsonResult update(@Valid ResourcesVo ResourcesVo, MultipartFile file) {
        resourcesService.update(ResourcesVo,file);
        return JsonResult.success();
    }
    @ApiOperation(value = "添加比赛获奖图片的list",notes = "",httpMethod = "POST")
    @PostMapping(value = "/addImgList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "标题", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "destination", value = "描述", required = true, paramType = "query", dataType = "String")
    })
    public JsonResult addImgList(@Valid ResourcesVo ResourcesVo, @RequestParam(value = "file") MultipartFile file) {
        ResourcesVo.setType("list");
        resourcesService.add(ResourcesVo,file);
        return JsonResult.success();
    }
    @ApiOperation(value = "更新比赛获奖图片的list",notes = "",httpMethod = "POST")
    @PostMapping(value = "/updateImgList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "资源id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "title", value = "标题", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "destination", value = "描述", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "file", value = "文件可以没有", paramType = "form", dataType = "__file")
    })
    public JsonResult updateImgList(@Valid ResourcesVo ResourcesVo, MultipartFile file) {
        ResourcesVo.setType("list");
        resourcesService.update(ResourcesVo,file);
        return JsonResult.success();
    }
    @ApiOperation(value = "添加实验讲义",notes = "",httpMethod = "POST")
    @PostMapping(value = "/addExperiment")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "标题", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "destination", value = "描述", required = true, paramType = "query", dataType = "String")
    })
    public JsonResult addExperiment(@Valid ResourcesVo ResourcesVo, @RequestParam(value = "file") MultipartFile file) {
        ResourcesVo.setType("exp");
        resourcesService.add(ResourcesVo,file);
        return JsonResult.success();
    }
    @ApiOperation(value = "更新实验讲义",notes = "",httpMethod = "POST")
    @PostMapping(value = "/updateExperiment")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "资源id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "title", value = "标题", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "destination", value = "描述", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "file", value = "文件可以没有", paramType = "form", dataType = "__file")
    })
    public JsonResult updateExperiment(@Valid ResourcesVo ResourcesVo, MultipartFile file) {
        ResourcesVo.setType("exp");
        resourcesService.update(ResourcesVo,file);
        return JsonResult.success();
    }
    @ApiOperation(value = "删除",notes = "",httpMethod = "DELETE")
    @DeleteMapping(value = "/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "资源id", required = true, paramType = "query", dataType = "int"),
    })
    public JsonResult delete(Long id) {
        resourcesService.delete(id);
        return JsonResult.success();
    }

}
