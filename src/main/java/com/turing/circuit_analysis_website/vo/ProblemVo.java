package com.turing.circuit_analysis_website.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author CHEN
 * @date 2020/10/13  20:48
 * 问题
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProblemVo implements Serializable {
    private Long id;
    @NotNull(message = "问题不能为空")
    private String message;
    private Long userID;


}
