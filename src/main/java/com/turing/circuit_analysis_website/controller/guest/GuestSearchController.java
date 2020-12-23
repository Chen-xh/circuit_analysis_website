package com.turing.circuit_analysis_website.controller.guest;


import com.turing.circuit_analysis_website.service.SearchService;
import com.turing.circuit_analysis_website.util.JsonResult;
import com.turing.circuit_analysis_website.util.SearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author CHEN
 * @date 2020/10/14  18:17
 */
@Api(tags = "全站搜索信息接口")
@RestController
@RequestMapping("/guest/search")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
public class GuestSearchController {
    @Autowired
    SearchService  searchService;


    @ApiOperation(value = "全站搜索信息", notes = "", httpMethod = "GET")
    @GetMapping("")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "搜索关键字", required = true, paramType = "query", dataType = "String")
    })
    public JsonResult findById(SearchRequest request) {

        return searchService.search(request);
    }
}
