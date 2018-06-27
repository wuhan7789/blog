package fishblog.util;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class XmlUtil {

    public static Map<String, String> parser(Reader reader) throws Exception {
        //key:标签的名字；value：标签的值
        Map<String,String> map=new HashMap<>();
        // 得到一个XmlPullParser的解析器
        XmlPullParser pullParser = XmlPullParserFactory.newInstance().newPullParser();
        //设置解析输入流
        pullParser.setInput(reader);
        // pullParser.START_DOCUMENT:文档的开始
        // pullParser.START_TAG：标签的开始
        // pullParser.END_DOCUMENT:文档结束
        // pullParser.END_TAG://标签的结束
        //获取当前的事件类型：
        int eventType = pullParser.getEventType();
        //当前事件类型不是文档的结束，就一直解析
        while(eventType!=XmlPullParser.END_DOCUMENT){
            //获取当标签的名字
            String tagName = pullParser.getName();
            //当前标签是一个开始标签，且不是xml的标签，就获取这个标签下一个text的值，这个值就是标签的value值
            if(!"xml".equals(tagName)&&eventType==XmlPullParser.START_TAG){
                //获取当前的值
                String value=pullParser.nextText();
                map.put(tagName, value);
            }
            //将下一个解析节点赋值给事件类型
            eventType=pullParser.next();
        }
        return map;
    }

}
