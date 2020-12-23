package com.turing.circuit_analysis_website.controller.admin;

import com.turing.circuit_analysis_website.service.ProblemService;
import com.turing.circuit_analysis_website.util.JsonResult;
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
@Api(tags = "问题信息管理接口")
@RestController
@RequestMapping("/admin/problem/")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
public class AdminProblemController {
    @Autowired
    ProblemService problemService;
    @Autowired
    private HttpServletRequest request;

    @ApiOperation(value = "添加", notes = "", httpMethod = "POST")
    @PostMapping(value = "/add")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "message", value = "问题内容", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "file", value = "文件可以没有", paramType = "form", dataType = "__file")
    })
    public JsonResult add(@Valid ProblemVo problemVo ,MultipartFile file) {
        String id = request.getAttribute("studentNo").toString();
//        String id="201811701104";
        problemVo.setUserID(Long.valueOf(id));
        problemService.add(problemVo,file);
        return JsonResult.success();
    }

    @ApiOperation(value = "更新", notes = "", httpMethod = "POST")
    @PostMapping(value = "/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "问题id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "message", value = "问题内容", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "file", value = "文件可以没有", paramType = "form", dataType = "__file")
    })
    public JsonResult update(@Valid ProblemVo problemVo,MultipartFile file) {
        String id = request.getAttribute("studentNo").toString();
//        String id="201811701104";
        problemVo.setUserID(Long.valueOf(id));
        problemService.update(problemVo,file);
        return JsonResult.success();
    }


    @ApiOperation(value = "删除", notes = "", httpMethod = "DELETE")
    @DeleteMapping(value = "/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "问题id", required = true, paramType = "query", dataType = "int"),
    })
    public JsonResult delete(Long id) {
        problemService.delete(id);
        return JsonResult.success();
    }


}
