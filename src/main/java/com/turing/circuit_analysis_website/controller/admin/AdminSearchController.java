package com.turing.circuit_analysis_website.controller.admin;

import com.turing.circuit_analysis_website.service.SearchService;
import com.turing.circuit_analysis_website.util.JsonResult;
import com.turing.circuit_analysis_website.vo.SearchVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author CHEN
 * @date 2020/10/14  18:17
 */
@Api(tags = "全站搜索信息管理接口")
@RestController
@RequestMapping("/admin/search/")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
public class AdminSearchController {
    @Autowired
    SearchService searchService;

    @ApiOperation(value = "添加",notes = "",httpMethod = "POST")
    @PostMapping(value = "/add")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "url", value = "路径", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "title", value = "标题", required = true, paramType = "query", dataType = "String")
    })
    public JsonResult add(@Valid SearchVo searchVo) {
            searchService.add(searchVo);
        return JsonResult.success();
    }
    @ApiOperation(value = "更新",notes = "",httpMethod = "POST")
    @PostMapping(value = "/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "url", value = "路径", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "title", value = "标题", required = true, paramType = "query", dataType = "String")
    })
    public JsonResult update(@Valid SearchVo searchVo) {
        searchService.update(searchVo);
        return JsonResult.success();
    }


    @ApiOperation(value = "删除",notes = "",httpMethod = "DELETE")
    @DeleteMapping(value = "/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "int"),
    })
    public JsonResult delete(Long id) {
        searchService.delete(id);
        return JsonResult.success();
    }

}
