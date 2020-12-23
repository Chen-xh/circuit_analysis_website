package com.turing.circuit_analysis_website.service;

import com.turing.circuit_analysis_website.pojo.Comment;
import com.turing.circuit_analysis_website.pojo.Problem;
import com.turing.circuit_analysis_website.pojo.Video;
import com.turing.circuit_analysis_website.util.JsonResult;
import com.turing.circuit_analysis_website.vo.VideoVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/13  20:28
 */
public interface VideoService {
    List<Video> findAll();
    Video findById(Long id);

    void add(VideoVo video, MultipartFile file);

    void update(VideoVo video ,MultipartFile file);

    void delete(Long id);
}
