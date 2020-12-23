package com.turing.circuit_analysis_website.dao;

import com.turing.circuit_analysis_website.pojo.Chapter;
import com.turing.circuit_analysis_website.pojo.TestInfo;
import com.turing.circuit_analysis_website.pojo.TestSet;
import com.turing.circuit_analysis_website.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/12  17:27
 */
public interface TestInfoDao extends JpaRepository<TestInfo, Long>, PagingAndSortingRepository<TestInfo, Long> {
    TestInfo findByInfId(Long id);
    List<TestInfo> findByChapter(Chapter chapter);
    List<TestInfo> findByChapterAndUser(Chapter chapter, User user);
    List<TestInfo> findByUser(User user);
}