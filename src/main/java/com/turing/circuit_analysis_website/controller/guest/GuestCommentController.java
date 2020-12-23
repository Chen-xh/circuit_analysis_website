package com.turing.circuit_analysis_website.controller.guest;

import com.turing.circuit_analysis_website.pojo.Comment;
import com.turing.circuit_analysis_website.pojo.Problem;
import com.turing.circuit_analysis_website.service.ChapterService;
import com.turing.circuit_analysis_website.service.CommentService;
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
@Api(tags = "评论信息查询接口")
@RestController
@RequestMapping("/guest/commend/")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
public class GuestCommentController {
    @Autowired
    CommentService commentService;

    @ApiOperation(value = "查询所有评论信息",notes = "",httpMethod = "GET")
    @GetMapping(value = "/findAll")
    public JsonResult findAll() {
        List<Comment> list=commentService.findAll();
        return JsonResult.success().addObject("comments",list);
    }

    @ApiOperation(value = "根据id查询评论信息",notes = "",httpMethod = "GET")
    @GetMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path", dataType = "int")
    })
    public JsonResult findById(@PathVariable Long id) {
        Comment comment=commentService.findById(id);
        return JsonResult.success().addObject("comment",comment);
    }
    @ApiOperation(value = "点赞",notes = "",httpMethod = "GET")
    @GetMapping("/good/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "评论id", required = true, paramType = "path", dataType = "int")
    })
    public JsonResult goodComment(@PathVariable Long id) {
       commentService.goodComment(id);
        return JsonResult.success();
    }
}
