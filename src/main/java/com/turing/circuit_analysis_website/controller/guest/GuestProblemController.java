package com.turing.circuit_analysis_website.controller.guest;

import com.turing.circuit_analysis_website.pojo.Comment;
import com.turing.circuit_analysis_website.pojo.Course;
import com.turing.circuit_analysis_website.pojo.Problem;
import com.turing.circuit_analysis_website.service.ChapterService;
import com.turing.circuit_analysis_website.service.CommentService;
import com.turing.circuit_analysis_website.service.ProblemService;
import com.turing.circuit_analysis_website.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author CHEN
 * @date 2020/10/14  18:17
 */
@Api(tags = "问题信息查询接口")
@RestController
@RequestMapping("/guest/problem/")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
public class GuestProblemController {
    @Autowired
    ProblemService problemService;
    @Autowired
    CommentService commentService;

    @ApiOperation(value = "查询所有问题信息",notes = "",httpMethod = "GET")
    @GetMapping(value = "/findAll")
    public JsonResult findAll() {
        List<Problem> list= problemService.findAll();
        return JsonResult.success().addObject("problems",list);
    }

    @ApiOperation(value = "根据id查询问题信息",notes = "",httpMethod = "GET")
    @GetMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path", dataType = "int")
    })
    public JsonResult findById(@PathVariable Long id) {
        Problem problem=problemService.findById(id);
        return JsonResult.success().addObject("problem",problem);
    }


}
