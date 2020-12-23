package com.turing.circuit_analysis_website.dao;

import com.turing.circuit_analysis_website.pojo.Chapter;
import com.turing.circuit_analysis_website.pojo.Search;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * @author CHEN
 * @date 2020/10/12  17:27
 */
@CacheConfig(cacheNames = "search")
public interface SearchDao extends JpaRepository<Search, Long>, PagingAndSortingRepository<Search, Long> {
    @Cacheable
    Page<Search> findByTitleLike(String title, Pageable pageable);
    Search findBySeaId(Long id);


}