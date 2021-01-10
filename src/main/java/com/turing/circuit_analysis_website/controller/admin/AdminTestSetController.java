package com.turing.circuit_analysis_website.controller.admin;


import com.turing.circuit_analysis_website.service.TestInfoService;
import com.turing.circuit_analysis_website.service.TestSetService;
import com.turing.circuit_analysis_website.util.JsonResult;
import com.turing.circuit_analysis_website.util.TestCommitResponse;
import com.turing.circuit_analysis_website.vo.TestSetDto;
import com.turing.circuit_analysis_website.vo.TestSetVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/14  18:17
 */
@Api(tags = "试题信息管理接口")
@RestController
@RequestMapping("/admin/testSet/")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
public class AdminTestSetController {
    @Autowired
    TestSetService testSetService;
    @Autowired
    TestInfoService testInfoService;
    @Autowired
    private HttpServletRequest request;

    @ApiOperation(value = "添加",notes = "题目类型（1 单选，2 判断）\n" +
            "选项(类型2没有选项，选项间以&分割，例如 111&444)\n" +
            "图片路径(以&分割 )\n" +
            "答案(单选就A  ，判断就T或F)",httpMethod = "POST")
    @PostMapping(value = "/add")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "question", value = "题目内容", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "题目类型", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "options", value = "选项", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "answer", value = "答案", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "CId", value = "课程章节id", required = true, paramType = "query", dataType = "int"),
    })
    public JsonResult add(@Valid TestSetVo testSetVo, MultipartFile file) {
        testSetService.add(testSetVo,file);
        return JsonResult.success();
    }

    @ApiOperation(value = "更新",notes = "详情见添加",httpMethod = "POST")
    @PostMapping(value = "/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tesId", value = "题目id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "question", value = "题目内容", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "题目类型", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "options", value = "选项", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "answer", value = "答案", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "CId", value = "课程章节id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "file", value = "图片可以没有", paramType = "form", dataType = "__file")
    })
    public JsonResult update(@Valid TestSetVo testSetVo, MultipartFile file) {
        testSetService.update(testSetVo,file);
        return JsonResult.success();
    }


    @ApiOperation(value = "删除",notes = "",httpMethod = "DELETE")
    @DeleteMapping(value = "/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "题目id", required = true, paramType = "query", dataType = "int"),
    })
    public JsonResult delete(Long id) {
        testSetService.delete(id);
        return JsonResult.success();
    }
    @ApiOperation(value = "提交测试，返回结果", notes = "id#answer的数组,答案间&分割", httpMethod = "POST")
    @PostMapping("/commitTest")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "answer", value = "answer", required = true, allowMultiple = true,paramType = "query", dataType = "String"),

    })
    public JsonResult commitTest(String[] answer) {
        String id=request.getAttribute("studentNo").toString();
        TestCommitResponse testCommitResponse = testSetService.commitTest(answer,10);
        String []tem=answer[0].split("#");
        testInfoService.add(testCommitResponse,id,Long.valueOf(tem[0]));
        return JsonResult.success().addObject("testSet", testCommitResponse);
    }
    @ApiOperation(value = "获取期末考试的题", notes = "一共50题选择20，判断30，一题10分",httpMethod = "GET")
    @GetMapping("/getFinalExam")
    @ApiImplicitParams({
    })
    public JsonResult getFinalExam() {
        List<TestSetDto> tem1=testSetService.getFinalExam(1,20);
        List<TestSetDto> tem2=testSetService.getFinalExam(2,30);
        List<TestSetDto> tem=new LinkedList<>();
        tem.addAll(tem1);
        tem.addAll(tem2);
        return JsonResult.success()
                .addObject("tem", tem)
                .addObject("size", 50);
    }
    @ApiOperation(value = "提交期末测试测试", notes = "id#answer的数组,答案间&分割", httpMethod = "POST")
    @PostMapping("/commitFinalExam")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "answer", value = "answer", required = true, allowMultiple = true,paramType = "query", dataType = "String"),

    })
    public JsonResult commitTest2(String[] answer) {
        String id=request.getAttribute("studentNo").toString();
        TestCommitResponse testCommitResponse = testSetService.commitTest(answer,2);
        String []tem=answer[0].split("#");
        testInfoService.add(testCommitResponse,id,Long.valueOf(tem[0]));
        return JsonResult.success().addObject("testSet", testCommitResponse);
    }
}
