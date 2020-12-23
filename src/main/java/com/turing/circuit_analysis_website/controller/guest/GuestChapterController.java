package com.turing.circuit_analysis_website.controller.guest;

import com.turing.circuit_analysis_website.service.ChapterService;
import com.turing.circuit_analysis_website.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CHEN
 * @date 2020/10/14  18:17
 */
@Api(tags = "章节信息查询接口")
@RestController
@RequestMapping("/guest/chapter/")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
public class GuestChapterController {
    @Autowired
    ChapterService chapterService;

    @ApiOperation(value = "查询所有章节信息",notes = "",httpMethod = "GET")
    @GetMapping(value = "/findAll")
    public JsonResult findAll() {
        return chapterService.findAll();
    }

}
