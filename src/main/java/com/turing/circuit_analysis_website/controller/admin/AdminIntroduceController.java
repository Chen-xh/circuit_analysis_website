package com.turing.circuit_analysis_website.controller.admin;

import com.turing.circuit_analysis_website.service.CommentService;
import com.turing.circuit_analysis_website.service.IntroduceService;
import com.turing.circuit_analysis_website.util.JsonResult;
import com.turing.circuit_analysis_website.vo.CommentVo;
import com.turing.circuit_analysis_website.vo.IntroduceVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author CHEN
 * @date 2020/10/14  18:17
 */
@Api(tags = "简介信息管理接口")
@RestController
@RequestMapping("/admin/introduce/")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
public class AdminIntroduceController {
    @Autowired
    IntroduceService introduceService;

    @ApiOperation(value = "添加",notes = "",httpMethod = "POST")
    @PostMapping(value = "/add")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "message", value = "简介内容", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "title", value = "简介内容", required = true, paramType = "query", dataType = "String")
    })
    public JsonResult add(@Valid IntroduceVo introduceVo) {
            introduceService.add(introduceVo);
        return JsonResult.success();
    }
    @ApiOperation(value = "更新",notes = "",httpMethod = "POST")
    @PostMapping(value = "/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "简介id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "message", value = "简介内容", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "title", value = "简介内容", required = true, paramType = "query", dataType = "String")
    })
    public JsonResult update(@Valid IntroduceVo introduceVo) {
        introduceService.update(introduceVo);
        return JsonResult.success();
    }
    @ApiOperation(value = "添加公告",notes = "",httpMethod = "POST")
    @PostMapping(value = "/addNotice")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "message", value = "公告内容", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "title", value = "公告内容", required = true, paramType = "query", dataType = "String")
    })
    public JsonResult addNotice(@Valid IntroduceVo introduceVo) {
        introduceVo.setType("list");
        introduceService.add(introduceVo);
        return JsonResult.success();
    }
    @ApiOperation(value = "更新公告",notes = "",httpMethod = "POST")
    @PostMapping(value = "/updateNotice")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "公告id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "message", value = "公告内容", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "title", value = "公告内容", required = true, paramType = "query", dataType = "String")
    })
    public JsonResult updateNotice(@Valid IntroduceVo introduceVo) {
        introduceVo.setType("list");
        introduceService.update(introduceVo);
        return JsonResult.success();
    }

    @ApiOperation(value = "删除",notes = "",httpMethod = "DELETE")
    @DeleteMapping(value = "/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "简介id", required = true, paramType = "query", dataType = "int"),
    })
    public JsonResult delete(Long id) {
        introduceService.delete(id);
        return JsonResult.success();
    }

}
