package fishblog.service.Impl;
import fishblog.mapper.ArticleMapper;
import fishblog.pojo.vo.ArticleVo;
import fishblog.service.In.StaticPageSerivceIn;
import fishblog.util.TimeFormat;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class StaticPageSerivceImpl implements StaticPageSerivceIn{

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public void createStaticArticlePage(Long id) throws Exception {
        Writer writer = null;
        ArticleVo data = articleMapper.selectByPrimaryKey(id);
        try{
            //初始化FreeMarker配置
            Configuration configuration;
            //创建一个Configuration实例
            configuration = new Configuration();
            //设置FreeMarker的模版文件夹位置
            configuration.setDirectoryForTemplateLoading(new File("/usr/local/tomcat8/articles-html/"));
            Map m = new HashMap();
            m.put("title",data.getDomain().getTitle());
            m.put("tag",data.getTag());
            m.put("author",data.getAuthorName());
            m.put("inputTime",TimeFormat.DateToSimpleTime(data.getDomain().getInputtime()));
            m.put("content",data.getDomain().getContent());
            m.put("introduction",data.getDomain().getIntroduction());
            //创建模版对象
            Template template = configuration.getTemplate("content.html","UTF-8");
            //生成路径
            String path = "/usr/local/tomcat8/articles-html/article0" +data.getDomain().getId()+ ".html";
            File file = new File(path);
            //准备文件流,注意编码
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            //数据模型  +  模板 = 文件
            template.process(m,writer);
            //关闭流
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            writer.close();
            deleteStaticArticlePage(data.getDomain().getId());
        }
    }

    @Override
    public void deleteStaticArticlePage(Long id) {
        String path = "/usr/local/tomcat8/articles-html/article0" +id+ ".html";
        File file = new File(path);
        //删除生成的页面
        if (file.exists() && file.isFile()) {
            if(file.delete()) {
                System.out.println("删除成功！");
            } else { System.out.println("删除文件失败！");}
        } else { System.out.println("删除文件失败，文件不存在！");}
        //查了下spring的事务机制，发现spring默认的事务只在发生未被捕获的RuntimeException时才会回滚，
        //而我自定义的EpidemicException是直接继承Exception的，把这个改为继承自RuntimeException就好了。
        throw new RuntimeException("error");
    }

}
