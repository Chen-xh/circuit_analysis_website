package com.turing.circuit_analysis_website.shiro;


import com.turing.circuit_analysis_website.dao.UserDao;
import com.turing.circuit_analysis_website.enums.MyCustomizeErrorCode;
import com.turing.circuit_analysis_website.exception.CustomizeAuthenticationException;
import com.turing.circuit_analysis_website.exception.CustomizeAuthenticationException;
import com.turing.circuit_analysis_website.pojo.Role;
import com.turing.circuit_analysis_website.pojo.User;
import com.turing.circuit_analysis_website.util.JWTToken;
import com.turing.circuit_analysis_website.util.JWTUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author CHEN
 * @date 2020/2/29 17:50
 * 创建shiro的自定义的Realm
 */
@Component
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    UserDao userDao;


    private static final Logger PLOG = LoggerFactory.getLogger(CustomRealm.class);

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {

        PLOG.info("Shiro >> 身份认证");
        String tokens = (String) token.getCredentials();
        if (tokens == null) {
            throw new CustomizeAuthenticationException(MyCustomizeErrorCode.NOT_LOGIN);
        }
        String username = JWTUtil.getUsername(tokens);

        if (username == null) {
            throw new CustomizeAuthenticationException(MyCustomizeErrorCode.NOT_LOGIN);
        }


//         从数据库获取对应用户名密码的用户
        User user = userDao.findUserByUsername(username);
        if (null == user) {
            throw new CustomizeAuthenticationException(MyCustomizeErrorCode.NOT_FOND_USER);
        }
        String password = user.getPassword();
        if (!JWTUtil.verify(tokens, username, password)) {

            throw new CustomizeAuthenticationException(MyCustomizeErrorCode.PASS_NOT_CORRECT);
        }
        return new SimpleAuthenticationInfo(username, tokens, getName());
    }

    /**
     * 获取授权信息
     * @param
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {
        PLOG.info("Shiro >> 权限认证");
        //获取用户的输入的账号.
        String username = (String) token.getPrimaryPrincipal();
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User user = userDao.findUserByUsername(username);
//        System.out.println("----->>userInfo=" + user);
        //user没找到
        if (user == null) {
            throw new CustomizeAuthenticationException(MyCustomizeErrorCode.NOT_FOND_USER);
        }
        //授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for(Role role:user.getRoles()){
            info.addRole(role.getRoleName());
        }
        return info;
    }

}

