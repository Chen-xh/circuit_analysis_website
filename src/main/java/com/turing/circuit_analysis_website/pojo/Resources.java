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
 * @date 2020/10/26  17:26
 * 其他文件资源文件
 */
@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "resources")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Resources implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long resId;
    @Column( nullable = false)
    private String title;
    @Column(nullable = false)
    private String destination;
    @Column( nullable = false)
    @Temporal(TemporalType.DATE)
    private Date time;
    @Column( nullable = false)
    private String res_url;
    @JsonIgnore
    private String type;
}