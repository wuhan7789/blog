package fishblog.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import fishblog.util.SecurityUtil;
import fishblog.util.XmlUtil;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

//这是同一接口，微信接入验证，响应微信消息 都走这一个接口，会根据POST/GET判断。
@RequestMapping("test")
@Controller
public class WeixinController {

    /*
    *   接入微信认证接口。
    * */
    @RequestMapping(method = {RequestMethod.GET})
    public void test(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //下面三个加密后就是（微信加密签名），自己加密一遍.equals(微信加密签名)
        // 认证成功是微信服务器发过来的就返回echostr表示接入成功
        String token = "weixin";//微信端自定义的token
        String timestamp = request.getParameter("timestamp");//时间戳
        String nonce = request.getParameter("nonce");//随机数
        //（微信加密签名），signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String signature = request.getParameter("signature");
        //随机字符串，原样返回代表验证成功
        String echostr = request.getParameter("echostr");
        /*加密:
          1.字母顺序排序并转成一个字符串*/
        String[] arr = {token,timestamp,nonce};
        Arrays.sort(arr);
        StringBuffer oneStr = new StringBuffer();
        for(String str : arr){
            oneStr.append(str);
        }
        //2.这个字符串进行sha1加密
        String shaStr = SecurityUtil.sha1(oneStr.toString());
        //3.比对
        if(shaStr.equals(signature)) {
            System.out.println("微信接入验证成功");
            System.out.println(echostr);
            response.getWriter().println(echostr);
        }else{
            System.out.println("微信接入验证失败");
        }
    }


    /*
    *   消息接口
    * */
    @RequestMapping(method = {RequestMethod.POST})
    public ModelAndView message(HttpServletRequest request,HttpServletResponse response) throws Exception{
        //获取请求的字节流
        ServletInputStream inputStream = request.getInputStream();
        //字节流转成字符流
        InputStreamReader reader = new InputStreamReader(inputStream,"utf-8");
        //包一层缓存流
        BufferedReader br = new BufferedReader(reader);

        /*解析Xml工具类*/
        Map<String,String> map = XmlUtil.parser(br);
        /*关注事件*/
        if(map.get("MsgType").equals("event")) {
            if(map.get("Event").equals("subscribe")) {
                map.put("MsgType","text");
                map.put("Content","欢迎关注咸鱼博客~");
            }
        }
        /*返回视图模型*/
        ModelAndView mv = new ModelAndView("weixin","map",map);

        return mv;
    }

}
