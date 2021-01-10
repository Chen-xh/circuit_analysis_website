package com.turing.circuit_analysis_website.service;

import com.turing.circuit_analysis_website.pojo.TestInfo;
import com.turing.circuit_analysis_website.util.TestCommitResponse;
import com.turing.circuit_analysis_website.vo.TestSetVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/13  20:28
 */
public interface TestInfoService {
    Page<TestInfo> findAll(int size, int page);

    TestInfo findById(Long id);

    List<TestInfo> findByChapterOrUser(Long cid,Long uid);


    void add(TestCommitResponse testCommitResponse ,String studentNo,Long cid);


    void delete(Long id);

}
