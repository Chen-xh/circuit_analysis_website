package com.turing.circuit_analysis_website.service;

import com.turing.circuit_analysis_website.pojo.Introduce;
import com.turing.circuit_analysis_website.pojo.Resources;
import com.turing.circuit_analysis_website.util.JsonResult;
import com.turing.circuit_analysis_website.vo.IntroduceVo;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/13  20:28
 */
public interface IntroduceService {
    List<Introduce> findAll();
    List<Introduce> findAllByType(String type);
    Introduce findById(Long id);

    Introduce getTeacherIntroduce();

    Introduce getCourseIntroduce();

    void add(IntroduceVo introduce );

    void update(IntroduceVo introduce);

    void delete(Long id);

}
