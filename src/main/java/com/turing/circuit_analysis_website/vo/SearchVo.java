package com.turing.circuit_analysis_website.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author CHEN
 * @date 2020/10/29  14:51
 * 搜索引擎
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SearchVo {

    private Long seaId;
    @NotNull(message = "标题不能为空")
    private String title;
    @NotNull(message = "路径不能为空")
    private String url;

    private String message;
}
