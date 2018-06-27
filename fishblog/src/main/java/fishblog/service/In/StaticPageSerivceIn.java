package fishblog.service.In;


public interface StaticPageSerivceIn {

    //创建静态化的文章页面
    void createStaticArticlePage(Long id) throws Exception;

    //删除静态化的文章页面
    void deleteStaticArticlePage(Long id);

}
