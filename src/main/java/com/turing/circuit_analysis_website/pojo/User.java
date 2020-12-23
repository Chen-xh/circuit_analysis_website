package com.turing.circuit_analysis_website.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * @author CHEN
 * @date 2020/10/12  17:15
 */
@Data
@Table(name = "user")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    //采用学号登录作为username
    private String username;
    @JsonIgnore
    private String password;

    //性别
    private String gender;
    //学院
    private String college;
    //专业
    private String major;
    //班级
    private String user_class;
    //姓名
    private String name;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "userId",referencedColumnName="userId")},
            inverseJoinColumns = {@JoinColumn(name = "rid",referencedColumnName="rid")})
    private List<Role> roles;
}
