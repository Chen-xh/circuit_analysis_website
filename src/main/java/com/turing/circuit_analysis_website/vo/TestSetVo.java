package com.turing.circuit_analysis_website.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.turing.circuit_analysis_website.pojo.Chapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author CHEN
 * @date 2020/10/13  20:42
 * 试题集
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TestSetVo implements Serializable {



    private Long tesId;
    @NotNull(message = "类型不能为空")
    private Integer type;
    @NotNull(message = "问题不能为空")
    @Size(max = 500,message = "字数太多")
    private String question;
    private String options;
    @NotNull(message = "答案不能为空")
    private String answer;

    @NotNull(message = "章节id不能为空")
    private Long CId;
}
