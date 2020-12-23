package com.turing.circuit_analysis_website.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author CHEN
 * @date 2020/4/26 21:52
 * 课程
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CourseVo implements Serializable {
    private Long id;
    @NotNull(message = "标题不能为空")
    private String title;
    @NotNull(message = "章节id不能为空")
    private Long cId;

}
