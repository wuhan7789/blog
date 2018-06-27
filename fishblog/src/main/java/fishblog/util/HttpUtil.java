package fishblog.util;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpUtil {

    public static final String APPID = "wx586e0601acf9aa31";
    public static final String APPSECRET = "66481fef0be7877a0e7c1e452ee49cce";
    //获取访问令牌的地址
    public static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    //请求菜单地址
    public static final String GET_MENUS_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";


    //获取接口凭证
    public static String getAccessToken(String url) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        /*创建一个get请求对象*/
        HttpGet httpGet = new HttpGet(url);
        /*发送请求*/
        HttpResponse httpResponse = httpClient.execute(httpGet);
        /*获取响应内容*/
        HttpEntity entity = httpResponse.getEntity();
        /*响应内容转换字符串*/
        String text = EntityUtils.toString(entity);
        return text;
    }

    //请求创建菜单地址
    public static String getMenus(String url,String data) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        /*创建一个Post请求对象*/
        HttpPost httpPost = new HttpPost(url);
        /*向请求对象中添加请求数据*/
        httpPost.setEntity(new StringEntity(data,"utf-8"));
        /*发送请求*/
        HttpResponse httpResponse = httpClient.execute(httpPost);
        /*获取响应内容*/
        HttpEntity responseEntity = httpResponse.getEntity();
         /*响应内容转换字符串*/
        String responseText = EntityUtils.toString(responseEntity);
        return responseText;
    }

}
