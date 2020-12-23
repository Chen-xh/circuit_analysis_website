package com.turing.circuit_analysis_website.controller.admin;

import com.turing.circuit_analysis_website.service.CommentService;
import com.turing.circuit_analysis_website.util.JsonResult;
import com.turing.circuit_analysis_website.vo.CommentVo;
import com.turing.circuit_analysis_website.vo.ProblemVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author CHEN
 * @date 2020/10/14  18:17
 */
@Api(tags = "评论信息管理接口")
@RestController
@RequestMapping("/admin/comment/")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
public class AdminCommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    private HttpServletRequest request;

    @ApiOperation(value = "添加", notes = "", httpMethod = "POST")
    @PostMapping(value = "/add")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "message", value = "评论内容", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "parId", value = "隶属于哪个问题或者评论的id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "type", value = "填1或0,1隶属于问题，0隶属于评论", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "file", value = "文件可以没有", paramType = "form", dataType = "__file")
    })
    public JsonResult add(@Valid CommentVo commentVo, MultipartFile file, Long parId, int type) {
        String id = request.getAttribute("studentNo").toString();
//        String id="201811701104";
        commentVo.setUserID(Long.valueOf(id));
        if (type == 0) {
            commentService.addToC(commentVo, parId,file);
        } else {
            commentService.addToP(commentVo, parId,file);
        }
        return JsonResult.success();
    }

    @ApiOperation(value = "更新", notes = "", httpMethod = "POST")
    @PostMapping(value = "/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "评论id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "message", value = "问题内容", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "file", value = "文件可以没有", paramType = "form", dataType = "__file")
    })
    public JsonResult update(@Valid CommentVo commentVo,MultipartFile file) {
        commentService.update(commentVo,file);
        return JsonResult.success();
    }


    @ApiOperation(value = "删除", notes = "", httpMethod = "DELETE")
    @DeleteMapping(value = "/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "评论id", required = true, paramType = "query", dataType = "int"),
    })
    public JsonResult delete(Long id) {
        commentService.delete(id);
        return JsonResult.success();
    }

}
