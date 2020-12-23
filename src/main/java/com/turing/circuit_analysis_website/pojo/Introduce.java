package com.turing.circuit_analysis_website.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author CHEN
 * @date 2020/10/13  19:13
 * 简介
 */
@Data
@Table(name = "introduce")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Introduce implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long intId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false,length = 1024)
    private String message;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    @JsonIgnore
    private String type;
}