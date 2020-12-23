package com.turing.circuit_analysis_website.vo;

import com.turing.circuit_analysis_website.validator.IsStudentNo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
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
public class LoginVo implements Serializable {

    @IsStudentNo
    @NotEmpty(message = "学号·不能为空")
    @Size(max = 12, min = 12, message = "请输入12位正确的用户名")
    private String username;
    @NotEmpty(message = "密码不能为空")
    @Size(max = 16, min = 3, message = "请输入3-16位正确的密码")
    private String password;


}
