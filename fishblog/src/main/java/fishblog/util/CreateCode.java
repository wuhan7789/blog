package fishblog.util;
import fishblog.pojo.vo.ArticleVo;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

/** FreeMarker版代码生成器
*             pojo 和 pojo 和 javabean是一个概念
*             注意替换思路 和 细节
* */

public class CreateCode {

    //提出公共路径
    private static final String ONE = "src/main/java/fishblog/";
    private static final String TWO = "src/main/webapp/";
    //路径  注意顺序
    private static final String[] PATHS = {
        ONE + "controller/" , ONE + "service/In/",
        ONE + "service/Impl/" , ONE + "pojo/vo/",
        TWO + "js/blog/",TWO + "WEB-INF/views-html/"
    };
    //模板的名称 ，为什么不拼到上面的路径一起？因为需要单独使用来找到template下面的模板
    private static final String[] TEMPLNAMES = {
       "DomainController.java","DomainIn.java",
       "DomainImpl.java","DomainVo.java","domain.js","domain.html"
    };
    //可能生成的不只一个类，所有这里写数组，可以生成多个表的crud
    private String[] domain = {"Author"};

    //是否允许覆盖
    static boolean flag = false;
    public static void main(String[] args) throws Exception {
        if(flag == false) {
            System.out.println("部分类存在，无法覆盖");
            return;
        }else{
            new CreateCode().createCode();
        }
    }

    public void createCode() throws Exception {
        //初始化FreeMarker配置
        Configuration configuration;
        //创建一个Configuration实例
        configuration = new Configuration();
        //设置FreeMarker的模版文件夹位置
        configuration.setDirectoryForTemplateLoading(new File("D:\\fishblog\\template"));
        //构造填充数据的Map
        Map map = new HashMap();
        for(int i = 0 ; i < domain.length ; i++) {
            //          拿到大写domain名
            String domainBig = domain[i];
            //          获取小写domain名
            String domainSmall = domainBig.substring(0,1).toLowerCase() + domainBig.substring(1);
            map.put("Domain", domainBig);
            map.put("domain", domainSmall);

            /* 开始遍历生成各层 ：
          因为这俩个String数组的数据顺序都一样，所以随便遍历哪一个都可以*/
            for(int j = 0 ; j < PATHS.length ; j ++) {
                String path = PATHS[j];
                String domainName = TEMPLNAMES[j];
//              找到单个层模板的路径并且设置编码规范
                System.out.println("template/" + domainName);
                //创建模版对象
                Template template = configuration.getTemplate(domainName,"UTF-8");
//              生成的文件位置 就是路径（注意这个时候domainName还没有替换）
                String filepath = path + domainName;
//              开始替换文件名字
                filepath = filepath.replaceAll("domain",domainSmall).replaceAll("Domain", domainBig);
                File file = new File(filepath);
//              准备文件流
                FileWriter fw = new FileWriter(file);
//              数据模型  +  模板 = 文件
                template.process(map,fw);
//              关闭流
                fw.close();
            }
        }
    }

}
