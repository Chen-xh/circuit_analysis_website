package com.turing.circuit_analysis_website.dao;

import com.turing.circuit_analysis_website.pojo.Chapter;
import com.turing.circuit_analysis_website.pojo.TestSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/12  17:27
 */
public interface TestSetDao extends JpaRepository<TestSet, Long>, PagingAndSortingRepository<TestSet, Long> {
    TestSet findByTesId(Long id);

    List<TestSet> findByChapter(Chapter chapter);
    List<TestSet> findByType(int type);

    @Query(value = "SELECT * FROM test_set WHERE type=? ORDER BY RAND() LIMIT ?",nativeQuery = true)
    List<TestSet> select(int type ,int size);

    @Query(value = "SELECT * FROM test_set WHERE cid=? ORDER BY RAND() LIMIT ?",nativeQuery = true)
    List<TestSet> selectByChapter(Long cid,int size);
}

