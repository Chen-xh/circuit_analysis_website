package com.turing.circuit_analysis_website.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/13  20:48
 * 问题
 */
@Data
@Table(name = "problem")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Problem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long proId;
    @Column(length = 2550,nullable = false)
    private String message;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid",referencedColumnName="userId")
    @JsonIgnoreProperties("roles")
    private User user;
    private String res_url;
    @OneToMany(mappedBy = "problem")
//    @JsonIgnoreProperties({"comments"})
    List<Comment> comments;
}
