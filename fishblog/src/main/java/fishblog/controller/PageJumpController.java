package fishblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageJumpController {

    /*咸 鱼 主 页*/
    @RequestMapping(value = "")
    public String fishblog() throws Exception {
        return "fishblog";
    }

    /*后台主页*/
    @RequestMapping(value = "system")
    public String system() throws Exception {
        return "system";
    }
    /*控制台 页面*/
    @RequestMapping(value = "console")
    public String console() throws Exception {
        return "console";
    }
    /*标签页 页面*/
    @RequestMapping(value = "tags")
    public String tags() throws Exception {
        return "tags";
    }
    /*文章管理 页面*/
    @RequestMapping(value = "article")
    public String article() throws Exception {
        return "article";
    }
    /*文章插入 页面*/
    @RequestMapping(value = "articleInsert")
    public String articleInsert() throws Exception {
        return "article_insert";
    }
    /*文章修改 页面*/
    @RequestMapping(value = "articleUpdate")
    public String articleUpdate() throws Exception {
        return "article_update";
    }
}
