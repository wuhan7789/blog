package fishblog.util;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import fishblog.pojo.Button;
import fishblog.pojo.CommonMenuButton;
import fishblog.pojo.ComplexMenuButton;
import fishblog.pojo.Menu;

import java.util.Map;

public class WeixinMenus {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    public void GetMenus()throws Exception {
        String url = fishblog.util.HttpUtil.GET_ACCESS_TOKEN_URL;
        url = url.replace("APPID", fishblog.util.HttpUtil.APPID);
        url = url.replace("APPSECRET", fishblog.util.HttpUtil.APPSECRET);
        /*获取AccessToken*/
        System.out.println(fishblog.util.HttpUtil.getAccessToken(url));

        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(Map.class,String.class,String.class);
        Map<String,String> map = MAPPER.readValue(fishblog.util.HttpUtil.getAccessToken(url), javaType);

        /*拼接菜单*/
        CommonMenuButton btn11 = new CommonMenuButton();
        btn11.setName("博客主页");
        btn11.setType("view");
        btn11.setUrl("http://02d73f21.ngrok.io/wx_coges/navi.jsp");
        CommonMenuButton btn21 = new CommonMenuButton();
        btn21.setName("友情链接");
        btn21.setType("view");
        btn21.setKey("21");
        btn21.setUrl("https://www.baidu.com/");
        CommonMenuButton btn31 = new CommonMenuButton();
        btn31.setName("jq之家");
        btn31.setType("click");
        btn31.setKey("31");
        CommonMenuButton btn32 = new CommonMenuButton();
        btn32.setName("前端");
        btn32.setType("click");
        btn32.setKey("32");
        /**
         * 微信： mainBtn2,mainBtn3底部的三个一级菜单
         */
        ComplexMenuButton mainBtn3 = new ComplexMenuButton();
        mainBtn3.setName("开源推荐");
        mainBtn3.setSub_button(new CommonMenuButton[] { btn31, btn32});
        /**
         * 封装整个菜单
         */
        Menu menu = new Menu();
        menu.setButton(new Button[] { btn11, btn21, mainBtn3 });

        String jsonMenu = MAPPER.writeValueAsString(menu);
        String menusUrl = fishblog.util.HttpUtil.GET_MENUS_URL;
        menusUrl = menusUrl.replace("ACCESS_TOKEN",map.get("access_token"));
        HttpUtil.getMenus(menusUrl,jsonMenu);
    }


}
