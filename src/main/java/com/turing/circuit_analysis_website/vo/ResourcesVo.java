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
 */

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResourcesVo implements Serializable {
    private Long id;
    @NotNull(message = "标题不能为空")
    private String title;
    @NotNull(message = "描述不能为空")
    private String destination;
    private String type;
}
