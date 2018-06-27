package fishblog;

import fishblog.pojo.Author;
import fishblog.pojo.vo.AuthorVo;
import fishblog.service.In.AuthorIn;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MyShiroReaml extends AuthorizingRealm {

    @Autowired
    AuthorIn authorIn;

    //用于认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 1、把AuthenticationToken转换为UsernamePasswordToken
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

        // 2、从UsernamePasswordToken中获取username
        String username = usernamePasswordToken.getUsername();

        // 3、调用数据库的方法，从数据库中查询username对应的用户记录
        AuthorVo authorVo = null;
        try {
            authorVo = authorIn.getUserByUserName(username);
        } catch (Exception e) {
            throw new UnknownAccountException("error");
        }
        Author author = authorVo.getDomain();
        // 4、若用户不存在，则抛出UnknownAccountException异常
        if (author == null) {
            throw new UnknownAccountException("error");
        }
        // 6、根据用户的情况，来构建AuthenticationInfo对象返回，通常使用的实现类为：SimpleAuthenticationInfo
        // 以下参数是从数据库中获取的
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(authorVo, author.getPassword(), this.getName());
        return info;
    }


    //授权认证
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {

/**
         *
         * 流程
         * 1.根据用户user->2.获取角色id->3.根据角色id获取权限permission
         //方法一：获得user对象
         User user=(User)pc.getPrimaryPrincipal();
         SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
         //获取permission
         if(user!=null) {
         List<Permission> permissionsByUser = shiroService.getPermissionsByUser(user);
         if (permissionsByUser.size()!=0) {
         for (Permission p: permissionsByUser) {

         info.addStringPermission(p.getUrl());
         }
         return info;
         }
         }

         //方法二： 从subject管理器里获取user
         //      Subject subject = SecurityUtils.getSubject();
         //      User _user = (User) subject.getPrincipal();
         //      System.out.println("subject"+_user.getUsername());*//*
*/
        return null;
    }

}

