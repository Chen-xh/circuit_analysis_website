package com.turing.circuit_analysis_website.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @date 2020/4/26 21:52
 * 课件
 */
@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "courseWare")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class CourseWare implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long courId;
    @Column( nullable = false)
    private String title;
    @Column(nullable = false)
    private String destination;
    @Column( nullable = false)
    @Temporal(TemporalType.DATE)
    private Date time;
    @Column( nullable = false)
    private String ware_url;

}
