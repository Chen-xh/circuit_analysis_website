package com.turing.circuit_analysis_website.enums;

/**
 * @author CHEN
 * @date 2020/10/13  12:07
 */
public enum MyCustomizeErrorCode implements CustomizeErrorCode {
    /**
     * 2001:没有找到用户!
     */
    NOT_FOND_USER(2001,"没有找到用户!"),
    /**
     * 2002:没有找到章节信息!
     */
    NOT_FOND_CHAPTER(2002,"没有找到章节信息!"),
    /**
     * 2003:没有找到课程信息!
     */
    NOT_FOND_COURSEWARE(2003,"没有找到课程信息!"),
    /**
     * 2004:没该用户已存在!
     */
    USER_HAVE_EXISTENCE(2004,"没该用户已存在!"),
    /**
     * 2005:没有找到评论!
     */
    NOT_FOND_COMMENT(2005,"没有找到评论!"),
    /**
     * 2006:没有找到问题!
     */
    NOT_FOND_PROLEM(2006,"没有找到该问题!"),
    /**
     * 2007:没有找到试题!
     */
    NOT_FOND_TEST(2007,"没有找到试题!"),
    /**
     * 2008:没有找到记录!
     */
    NOT_FOND_INTRODUCE(2008,"没有找到记录!"),
    /**
     * 2009:没有找到课件!
     */
    NOT_FOND_COURSE_WARE(2009,"没有找到课件!"),
    /**
     * 2010:没有找到录像!
     */
    NOT_FOND_VIDEO(2010,"没有找到录像!"),
    /**
     * 2011:上传文件不能为空!
     */
    FILE_IS_NULL(2011,"上传文件不能为空!"),
    /**
     * 2012:没有找到资源!
     */
    NOT_FOUND_RESOURCES(2012,"没有找到资源!"),
    /**
     * 2013:没有该角色!
     */
    NOT_FOND_ROLE(2008,"没有该角色!"),
    /**
     * 2014:没有测试记录!
     */
    NOT_FOND_TEST_INFO(2014,"没有测试记录!"),
    /**
     * 2015:没有找到教师信息!
     */
    NOT_FOND_TEACHER(2015,"没有找到教师信息!"),
    /**
     * 3001:尚未登录!
     */
    NOT_LOGIN(3001,"尚未登录!"),
    /**
     * 3002:没有权限!
     */
    NOT_ALLOWED(3002,"没有权限!"),
    /**
     * 3003:密码错误!
     */
    PASS_NOT_CORRECT(3003,"密码错误!"),
    /**
     * 3005:超级管理者无法操作!
     */
    ROOT_ADMIN_CANT_NOT_CHANGE(3005,"超级管理者无法删除!"),
    /**
     * 3006:单选题只有一个答案!
     */
    ONLY_HAVE_ANSWER(3006,"单选题只有一个答案!"),
    /**
     * 3004:账号或密码错误!
     */
    USERNAME_PASS_NOT_CORRECT(3004,"账号或密码错误!"),

    /**
     * 3007:文件大小超出限制！
     */
    FILE_MAX_SIZE_EXCEPTION(3007, "文件大小超出限制！"),
    /**
     * 500:服务端异常
     */
    INTERNAL_SERVER_ERROR(500, "服务器冒烟了...要不等它降降温后再来访问?");

    private Integer code;
    private String message;

    MyCustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
