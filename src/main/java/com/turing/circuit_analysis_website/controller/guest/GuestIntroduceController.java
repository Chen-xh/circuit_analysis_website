package com.turing.circuit_analysis_website.controller.guest;

import com.turing.circuit_analysis_website.pojo.Introduce;
import com.turing.circuit_analysis_website.service.IntroduceService;
import com.turing.circuit_analysis_website.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/14  18:17
 */
@Api(tags = "简介信息查询接口")
@RestController
@RequestMapping("/guest/introduce/")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
public class GuestIntroduceController {
    @Autowired
    IntroduceService introduceService;

    @ApiOperation(value = "查询所有简介信息", notes = "", httpMethod = "GET")
    @GetMapping(value = "/findAll")
    public JsonResult findAll() {
        List<Introduce> list = introduceService.findAll();
        return JsonResult.success().addObject("introduces", list);
    }
    @ApiOperation(value = "查询公告信息", notes = "", httpMethod = "GET")
    @GetMapping(value = "/findNotice")
    public JsonResult findAllByType() {
        List<Introduce> list = introduceService.findAllByType("list");
        return JsonResult.success().addObject("notice", list);
    }
    @ApiOperation(value = "根据id查询简介信息", notes = "", httpMethod = "GET")
    @GetMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path", dataType = "int")
    })
    public JsonResult findById(@PathVariable Long id) {
        Introduce introduce = introduceService.findById(id);
        return JsonResult.success().addObject("introduce", introduce);
    }
    @ApiOperation(value = "查询课程简介信息", notes = "", httpMethod = "GET")
    @GetMapping(value = "/getCourseIntroduce")
    public JsonResult getCourseIntroduce() {
        Introduce introduce = introduceService.getCourseIntroduce();
        return JsonResult.success().addObject("introduce", introduce);
    }
    @ApiOperation(value = "根据type查询信息", notes = "type参数\n" +
            "1.class:课程简介\n" +
            "2.teacher:老师简介\n" +
            "3.knowledge:先修知识\n" +
            "4.list:公告\n" +
            "5.socialEvaluation:社会评价\n" +
            "6.studentAssess:学生评价\n"+
            "7.history:历史历程", httpMethod = "GET")
    @GetMapping(value = "/findByType")
    public JsonResult findByType(String type) {
        List<Introduce> list = introduceService.findAllByType(type);
        return JsonResult.success().addObject("list", list);
    }
}
