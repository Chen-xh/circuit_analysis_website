package com.turing.circuit_analysis_website.service;

import com.turing.circuit_analysis_website.pojo.Comment;
import com.turing.circuit_analysis_website.pojo.Problem;
import com.turing.circuit_analysis_website.vo.CommentVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author CHEN
 * @date 2020/10/13  20:28
 */
public interface CommentService {
    Comment findById(Long id);

    void addToP(CommentVo comment ,Long proID, MultipartFile file);
    void addToC(CommentVo comment ,Long comID, MultipartFile file);
    void goodComment(Long id);
    List<Comment> findAll();


    void update(CommentVo comment, MultipartFile file);

    void delete(Long id);
}
