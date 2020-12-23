package com.turing.circuit_analysis_website.util;

/**
 * @author CHEN
 * @date 2020/10/25  14:43
 * 搜索条件接收类，用来接收前台搜索条件，和分页条件
 */
public class SearchRequest {
    private String key;// 搜索条件
    private Integer page;// 当前页
    private static final Integer DEFAULT_SIZE = 10;// 每页大小，不从页面接收，而是固定大小
    private static final Integer DEFAULT_PAGE = 1;// 默认页
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public Integer getPage() {
        if(page == null){
            return DEFAULT_PAGE;
        }
        // 获取页码时做一些校验，不能小于1
        return Math.max(DEFAULT_PAGE, page);
    }
    public void setPage(Integer page) {
        this.page = page;
    }
    public Integer getSize() {
        return DEFAULT_SIZE;
    }
}
