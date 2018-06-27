package fishblog.controller;

import fishblog.util.UuidRandom;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

@Controller
public class ShiroController {

    //登录
    @RequestMapping("loginPage")
    private String loginPage() throws Exception {
        return "login";
    }

    //生成验证码
    @RequestMapping("getRandom")
    private void getRandom(HttpServletRequest req, HttpServletResponse res) throws Exception {
        BufferedImage bufferedImage = UuidRandom.getUuidRandom().getUuidImage(req);
        //使用图片流出入到页面
        ImageIO.write(bufferedImage, "jpg", res.getOutputStream());
    }

    //注销
    @RequestMapping(value = "logout")
    private String logout() throws Exception {
        return "login";
    }

    //没有权限页面
    @RequestMapping(value = "nopermission")
    private String nopermission() throws Exception {
        return "nopermission";
    }

    @RequestMapping(value = "/login",method = {RequestMethod.POST},produces = "text/html;charset=UTF-8")
    public @ResponseBody String login(String random,String username, String password, HttpSession session) throws Exception {
        UsernamePasswordToken token = null;
        // 获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        // 测试当前的用户是否已经被认证，即是否已经登陆
        /*if(!session.getAttribute("suijima").equals(random.trim())){
            return "验证码错误";
        }*/
        // 调用Subject的isAuthenticated
        if (!currentUser.isAuthenticated()) {
            // 把用户名和密码封装为UsernamePasswordToken 对象
            token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            try {
                // 执行登陆
                currentUser.login(token);
            } catch (Exception e) {
                e.printStackTrace();
                return "用户名或密码错误";
            }
        }
        session.setAttribute("USER",currentUser.getPrincipal());
        return "success";
    }

}