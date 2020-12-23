package com.turing.circuit_analysis_website.service;

import com.turing.circuit_analysis_website.pojo.Course;
import com.turing.circuit_analysis_website.pojo.Resources;
import com.turing.circuit_analysis_website.pojo.Video;
import com.turing.circuit_analysis_website.vo.ResourcesVo;
import com.turing.circuit_analysis_website.vo.VideoVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/13  20:28
 */
public interface ResourcesService {
    List<Resources> findAll();

    List<Resources> findAllByType(String type);

    Page<Resources> findAllByType(String type,int size, int page);

    Resources findById(Long id);

    void add(ResourcesVo resourcesVo, MultipartFile file);

    void update(ResourcesVo resourcesVo, MultipartFile file);

    void delete(Long id);
}
