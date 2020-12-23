package com.turing.circuit_analysis_website.service;

import com.turing.circuit_analysis_website.pojo.Problem;
import com.turing.circuit_analysis_website.vo.ProblemVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/13  20:28
 */
public interface ProblemService {

    List<Problem> findAll();

    Problem findById(Long id);

    void add(ProblemVo problem, MultipartFile file);

    void update(ProblemVo problem, MultipartFile file);

    void delete(Long id);

}
