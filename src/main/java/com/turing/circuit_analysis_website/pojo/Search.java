package com.turing.circuit_analysis_website.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author CHEN
 * @date 2020/10/29  14:51
 * 搜索引擎
 */
@Data
@Table(name = "search")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
public class Search {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seaId;
    private String title;
    private String url;
    private String message;
}
