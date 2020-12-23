package com.turing.circuit_analysis_website.service;

import com.turing.circuit_analysis_website.pojo.Search;
import com.turing.circuit_analysis_website.util.JsonResult;
import com.turing.circuit_analysis_website.util.SearchRequest;
import com.turing.circuit_analysis_website.vo.SearchVo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;

/**
 * @author CHEN
 * @date 2020/10/29  14:57
 */

public interface SearchService {
    void  add (SearchVo searchVo);
    void  update (SearchVo searchVo);
    JsonResult search(SearchRequest searchRequest);

    void delete(Long id);
}
