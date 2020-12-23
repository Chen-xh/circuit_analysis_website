package com.turing.circuit_analysis_website.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @author CHEN
 * @date 2020/10/13  20:42
 * 试题集
 */
@Data
@Table(name = "testSet")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
public class TestSet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tesId;
    @Column(nullable = false)
    private Integer type;
    @Column(length = 500, nullable = false)
    private String question;

    private String options;
    private String img_urls;
    @Column(nullable = false)
    private String answer;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "cid", referencedColumnName = "chaId")
    private Chapter chapter;
}
