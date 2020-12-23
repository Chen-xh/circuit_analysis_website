package com.turing.circuit_analysis_website.pojo;

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
 * @date 2020/10/13  20:42
 * 课程
 */
@Data
@Table(name = "course")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couId;
    private String title;
    private String res_url;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    @ManyToOne
    @JoinColumn(name = "cid",referencedColumnName="chaId")
    private Chapter chapter;

}
