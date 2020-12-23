package com.turing.circuit_analysis_website.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author CHEN
 * @date 2020/10/13  19:13
 * 简介
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IntroduceVo implements Serializable {
    private Long id;
    @NotNull(message = "标题不能为空")
    private String title;
    @NotNull(message = "内容不能为空")
    @Size(max=2550,message = "内容过长")
    private String message;
    private String type;

}