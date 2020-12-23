package com.turing.circuit_analysis_website.controller.guest;

import com.turing.circuit_analysis_website.pojo.Problem;
import com.turing.circuit_analysis_website.pojo.Video;
import com.turing.circuit_analysis_website.service.ChapterService;
import com.turing.circuit_analysis_website.service.VideoService;
import com.turing.circuit_analysis_website.util.JsonResult;
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
@Api(tags = "录像信息查询接口")
@RestController
@RequestMapping("/guest/video/")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
public class GuestVideoController {
    @Autowired
    VideoService videoService;

    @ApiOperation(value = "查询所有录像信息", notes = "", httpMethod = "GET")
    @GetMapping(value = "/findAll")
    public JsonResult findAll() {
        List<Video> videos = videoService.findAll();
        return JsonResult.success().addObject("video", videos);
    }

    @ApiOperation(value = "根据id查询录像信息", notes = "", httpMethod = "GET")
    @GetMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path", dataType = "int")
    })
    public JsonResult findById(@PathVariable Long id) {
        Video video = videoService.findById(id);
        return JsonResult.success().addObject("video", video);
    }
}
