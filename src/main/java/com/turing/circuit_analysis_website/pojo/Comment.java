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
import java.util.List;

/**
 * @author CHEN
 * @date 2020/10/13  20:48
 * 评论
 */
@Data
@Table(name = "comment")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comId;
    @Column(length = 2550,nullable = false)
    private String message;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid",referencedColumnName="userId")
    @JsonIgnoreProperties("roles")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cid",referencedColumnName="comId")
    @JsonIgnoreProperties({"comment","problem"})
    private Comment comment;

    private String res_url;

    private Long root;
    private int good;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pId",referencedColumnName="proId")
    @JsonIgnore
    @JsonIgnoreProperties({"comment","problem","user"})
    private Problem problem;

//    @JsonIgnore
    @JsonIgnoreProperties({"comment","problem","comments"})
    @OneToMany(mappedBy = "comment")
    List<Comment> comments;
}
