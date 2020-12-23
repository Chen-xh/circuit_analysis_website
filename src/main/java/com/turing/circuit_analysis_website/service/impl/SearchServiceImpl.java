package com.turing.circuit_analysis_website.service.impl;

import com.turing.circuit_analysis_website.dao.SearchDao;
import com.turing.circuit_analysis_website.enums.MyCustomizeErrorCode;
import com.turing.circuit_analysis_website.exception.CustomizeRuntimeException;
import com.turing.circuit_analysis_website.pojo.Search;
import com.turing.circuit_analysis_website.service.SearchService;
import com.turing.circuit_analysis_website.util.JsonResult;
import com.turing.circuit_analysis_website.util.SearchRequest;
import com.turing.circuit_analysis_website.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


/**
 * @author CHEN
 * @date 2020/10/29  14:59
 */
@Service
@Transactional
public class SearchServiceImpl implements SearchService {
    @Autowired
    SearchDao searchDao;
    @CachePut(value = "search")
    @Override
    public void add(SearchVo searchVo) {
        Search s=new Search();
        s.setMessage(searchVo.getMessage());
        s.setTitle(searchVo.getTitle());
        s.setUrl(searchVo.getUrl());
        searchDao.save(s);
    }

    @CachePut(value = "search")
    @Override
    public void update(SearchVo searchVo) {
        Search tem=searchDao.findBySeaId(searchVo.getSeaId());
        if(tem==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_INTRODUCE);
        }
        tem.setMessage(searchVo.getMessage());
        tem.setTitle(searchVo.getTitle());
        searchDao.saveAndFlush(tem);
    }

    @CacheEvict(value = "search")
    @CachePut(value = "search")
    @Override
    public JsonResult search(SearchRequest request) {
        int page = request.getPage() - 1;
        int size = request.getSize();

        Pageable pageable= PageRequest.of(page, size);
        Page<Search> page1=searchDao.findByTitleLike("%"+request.getKey()+"%",pageable);
        if(page1.getContent()==null||page1.getContent().size()<=0){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_INTRODUCE);
        }
        //分页结果解析
        long total =page1.getTotalElements();
        Long totalPages = total % size == 0 ? total / size : total / size + 1;
        //解析聚合结果
        return JsonResult.success()
                .addObject("total",total)
                .addObject("totalPages",totalPages)
                .addObject("items",page1.getContent());
    }
    @CacheEvict(value = "search")
    @Override
    public void delete(Long id) {
        Search search=searchDao.findBySeaId(id);
        if(search==null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_INTRODUCE);
        }
        searchDao.delete(search);
    }
}
