package com.turing.circuit_analysis_website.vo;

import com.turing.circuit_analysis_website.validator.IsStudentNo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author CHEN
 * @date 2020/10/12  17:34
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserVo  implements Serializable {
    private Long id;
    @IsStudentNo
    @NotEmpty(message = "学号不能为空")
    @Size(max = 12, min = 12, message = "请输入12位正确的用户名")
    private String username;
    @NotEmpty(message = "密码不能为空")
    @Size(max = 16, min = 3, message = "请输入3-16位正确的密码")
    private String password;
    //性别
    @NotEmpty(message = "学院不能为空")
    private String gender;
    //学院
    @NotEmpty(message = "学院不能为空")
    private String college;
    //专业
    @NotEmpty(message = "专业不能为空")
    private String major;
    //班级
    @NotEmpty(message = "班级不能为空")
    private String user_class;
    //姓名
    @NotEmpty(message = "姓名不能为空")
    private String name;

}
