package com.turing.circuit_analysis_website.controller.admin;


import com.turing.circuit_analysis_website.pojo.TestInfo;
import com.turing.circuit_analysis_website.service.TestInfoService;
import com.turing.circuit_analysis_website.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/14  18:17
 */
@Api(tags = "测试记录管理接口")
@RestController
@RequestMapping("/admin/testInfo/")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
public class AdminTestInfoController {
    @Autowired
    TestInfoService testInfoService;

    @ApiOperation(value = "分页查询所有测试记录", notes = "", httpMethod = "GET")
    @GetMapping(value = "/findAll")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码，0开始", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "一页多少条", required = true, paramType = "query", dataType = "int")
    })
    public JsonResult findAll(int size, int page) {
        Page<TestInfo> testInfos = testInfoService.findAll(size,page);
        return JsonResult.success().addObject("testInfo", testInfos);
    }
    @ApiOperation(value = "根据id查询测试记录", notes = "", httpMethod = "GET")
    @GetMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path", dataType = "int")
    })
    public JsonResult findById(@PathVariable Long id) {
        TestInfo testInfo = testInfoService.findById(id);
        return JsonResult.success().addObject("testInfo", testInfo);
    }


    @ApiOperation(value = "根据章节和用户查询测试记录", notes = "多条件查询，有其中一个就行了", httpMethod = "GET")
    @GetMapping("/findByCId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "章节id",  paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "uid", value = "用户id", paramType = "query", dataType = "int")
    })
    public JsonResult findByCId(Long cid,Long uid) {
        if(cid==null&&uid==null){
            return JsonResult.fail();

        }
        List<TestInfo> testInfos = testInfoService.findByChapterOrUser(cid,uid);

        return JsonResult.success().addObject("testInfo", testInfos);
    }

    @ApiOperation(value = "删除",notes = "",httpMethod = "DELETE")
    @DeleteMapping(value = "/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "题目id", required = true, paramType = "query", dataType = "int"),
    })
    public JsonResult delete(Long id) {
        testInfoService.delete(id);
        return JsonResult.success();
    }
}
