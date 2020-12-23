package com.turing.circuit_analysis_website.controller.admin;

import com.turing.circuit_analysis_website.service.CourseWareService;
import com.turing.circuit_analysis_website.service.VideoService;
import com.turing.circuit_analysis_website.util.JsonResult;
import com.turing.circuit_analysis_website.vo.CourseWareVo;
import com.turing.circuit_analysis_website.vo.VideoVo;
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
@Api(tags = "录像信息管理接口")
@RestController
@RequestMapping("/admin/video/")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
public class AdminVideoController {
    @Autowired
    VideoService videoService;

    @ApiOperation(value = "添加",notes = "",httpMethod = "POST")
    @PostMapping(value = "/add")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "标题", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "destination", value = "描述", required = true, paramType = "query", dataType = "String")
    })
    public JsonResult add(@Valid VideoVo videoVo, @RequestParam(value = "file") MultipartFile file) {
        videoService.add(videoVo,file);
        return JsonResult.success();
    }
    @ApiOperation(value = "更新",notes = "",httpMethod = "POST")
    @PostMapping(value = "/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "录像id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "title", value = "标题", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "destination", value = "描述", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "file", value = "文件可以没有", paramType = "form", dataType = "__file")
    })
    public JsonResult update(@Valid VideoVo videoVo, MultipartFile file) {
        videoService.update(videoVo,file);
        return JsonResult.success();
    }


    @ApiOperation(value = "删除",notes = "",httpMethod = "DELETE")
    @DeleteMapping(value = "/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "录像id", required = true, paramType = "query", dataType = "int"),
    })
    public JsonResult delete(Long id) {
        videoService.delete(id);
        return JsonResult.success();
    }

}
