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
 * 评论
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentVo implements Serializable {
    private Long id;
    @NotNull(message = "评论不能为空")
    private String message;
    private Long userID;


}
