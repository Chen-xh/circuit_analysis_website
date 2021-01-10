package com.turing.circuit_analysis_website.service;

import com.turing.circuit_analysis_website.util.TestCommitResponse;
import com.turing.circuit_analysis_website.vo.TestSetDto;
import com.turing.circuit_analysis_website.vo.TestSetVo;
import org.springframework.web.multipart.MultipartFile;

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
    void add(TestSetVo testSet, MultipartFile file);

    void update(TestSetVo testSet, MultipartFile file);

    void delete(Long id);

}
