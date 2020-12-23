package com.turing.circuit_analysis_website.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
public class TestCommitResponse {

    private String[] myAnswer;
    private String[] answer;
    private int[] mark;
    private int sum;

}
