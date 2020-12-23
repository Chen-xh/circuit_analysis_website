package com.turing.circuit_analysis_website.service;

import com.turing.circuit_analysis_website.util.TestCommitResponse;
import com.turing.circuit_analysis_website.vo.TestSetDto;
import com.turing.circuit_analysis_website.vo.TestSetVo;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/13  20:28
 */
public interface TestSetService {
    List<TestSetDto> findAll();

    TestSetDto findById(Long id);

    List<TestSetDto> findByChapter(Long id,int size);

    List<TestSetDto> findByType(int type);
    TestCommitResponse commitTest(String[] answer,int score);

    List<TestSetDto> getFinalExam(int type,int size);
    void add(TestSetVo testSet);

    void update(TestSetVo testSet);

    void delete(Long id);

}
