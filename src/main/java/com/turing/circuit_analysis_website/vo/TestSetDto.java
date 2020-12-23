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
 * @date 2020/10/13  20:42
 * 试题集
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TestSetDto implements Serializable {


    private Long tesId;
    private Integer type;

    private String question;
    private String[] img_urls;
    private String[] options;
    private String[] answer;

}
