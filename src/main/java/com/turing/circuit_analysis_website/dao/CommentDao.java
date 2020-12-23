package com.turing.circuit_analysis_website.dao;

import com.turing.circuit_analysis_website.pojo.Comment;
import com.turing.circuit_analysis_website.pojo.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/12  17:27
 */
public interface CommentDao extends JpaRepository<Comment, Long>, PagingAndSortingRepository<Comment, Long> {
    Comment findByComId(Long id);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE comment SET good=good+1 WHERE com_id=?1",nativeQuery = true)
    void goodComment(Long id);
    List<Comment> findByComment(Comment comment);
    List<Comment> findByProblem(Problem problem);
    List<Comment> findByRoot(Long root);
}