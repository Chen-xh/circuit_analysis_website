package com.turing.circuit_analysis_website.util;

import com.alibaba.druid.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author CHEN
 * @date 2020/9/20  15:06
 * 学号校验工具类
 */
public class ValidatorUtil {


    private static final Pattern mobile_pattern = Pattern.compile("^\\d{4}[111-129][01-61]\\d{3}.+");

    public static boolean isStudentNo(String src){
        if(StringUtils.isEmpty(src)){
            return false;
        }
        Matcher m = mobile_pattern.matcher(src);
        return m.matches();
    }
}
