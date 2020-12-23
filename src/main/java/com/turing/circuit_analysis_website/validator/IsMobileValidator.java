package com.turing.circuit_analysis_website.validator;

import com.alibaba.druid.util.StringUtils;
import com.turing.circuit_analysis_website.util.ValidatorUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author CHEN
 * @date 2020/9/20  15:11
 * 自定义学号格式校验注解
 */
public class IsMobileValidator implements ConstraintValidator<IsStudentNo, String> {

    private boolean required = false;

    //初始化
    @Override
    public void initialize(IsStudentNo isMobile) {
        required = isMobile.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (required) {
            return ValidatorUtil.isStudentNo(value);
        } else {
            if (StringUtils.isEmpty(value)) {
                return true;
            } else {
                return ValidatorUtil.isStudentNo(value);
            }
        }
    }
}

