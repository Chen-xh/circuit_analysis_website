package com.turing.circuit_analysis_website.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author CHEN
 * @date 2020/4/26 21:52
 * 教学录像
 */
@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "video")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Video implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long vidId;
    @Column( nullable = false)
    private String title;
    @Column(nullable = false)
    private String destination;
    @Column( nullable = false)
    @Temporal(TemporalType.DATE)
    private Date time;
    @Column( nullable = false)
    private String video_url;
}
