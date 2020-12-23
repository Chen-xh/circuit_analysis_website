package com.turing.circuit_analysis_website.vo;

import lombok.*;

import javax.persistence.*;

/**
 * @author CHEN
 * @date 2020/2/29 17:50
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TeacherVo {


    private Long teacherId;
    private String teacherName;
    private String teacherEmail;
    private String teacherBorn;
    private String teacherPosition;
    private String teacherJob;
    private String teacherGraduation;
    private String teacherResearch;
    private String teacherScientificResearch;
    private String teacherAwardIntroduction;

}
