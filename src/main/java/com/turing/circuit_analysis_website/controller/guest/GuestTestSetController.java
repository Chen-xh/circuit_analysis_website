package com.turing.circuit_analysis_website.controller.guest;

import com.turing.circuit_analysis_website.service.TestSetService;
import com.turing.circuit_analysis_website.util.JsonResult;
import com.turing.circuit_analysis_website.vo.TestSetDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/14  18:17
 */
@Api(tags = "试题信息查询接口")
@RestController
@RequestMapping("/guest/testSet/")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
public class GuestTestSetController {
    @Autowired
    TestSetService testSetService;


    @ApiOperation(value = "查询所有试题信息", notes = "", httpMethod = "GET")
    @GetMapping(value = "/findAll")
    public JsonResult findAll() {
        List<TestSetDto> testSets = testSetService.findAll();
        return JsonResult.success().addObject("testSet", testSets);
    }

    @ApiOperation(value = "根据id查询试题信息", notes = "", httpMethod = "GET")
    @GetMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path", dataType = "int")
    })
    public JsonResult findById(@PathVariable Long id) {
        TestSetDto testSet = testSetService.findById(id);
        return JsonResult.success().addObject("testSet", testSet);
    }


    @ApiOperation(value = "根据章节查询试题信息", notes = "随机10道题", httpMethod = "GET")
    @GetMapping("/findByCId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "int")
    })
    public JsonResult findByCId(Long id) {
        List<TestSetDto> testSets = testSetService.findByChapter(id,10);

//        List<TestSetDto> tem1=new LinkedList<>();
//        List<TestSetDto> tem2=new LinkedList<>();
//        for(TestSetDto testSetDto:testSets){
//            switch (testSetDto.getType()){
//                case 1:
//                    tem1.add(testSetDto);
//                    break;
//                case 2:
//                    tem2.add(testSetDto);
//                    break;
//            }
//        }
        return JsonResult.success()
                .addObject("tem1", testSets);
    }

    @ApiOperation(value = "根据题目类型查询题目信息", notes = "", httpMethod = "GET")
    @GetMapping("/findByType")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "int")
    })
    public JsonResult findByType(int id) {
        List<TestSetDto> testSets = testSetService.findByType(id);
        return JsonResult.success().addObject("testSet", testSets);
    }


}
