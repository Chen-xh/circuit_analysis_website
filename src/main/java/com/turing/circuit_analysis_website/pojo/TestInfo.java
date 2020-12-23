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
 * @date 2020/10/12  19:07
 * 用户测试记录
 */
@Data
@Table(name = "testInfo")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class TestInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long infId;

    //分数
    @Column(nullable = false)
    private Integer score;
    //题数
    @Column(nullable = false)
    private Integer number ;

    @ManyToOne
    @JoinColumn(name = "cid", referencedColumnName = "chaId")
    private Chapter chapter;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid",referencedColumnName="userId")
    @JsonIgnoreProperties("roles")
    private User user;
}
