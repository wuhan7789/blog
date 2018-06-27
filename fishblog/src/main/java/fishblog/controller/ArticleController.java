package fishblog.controller;

import com.fasterxml.jackson.databind.JavaType;
import fishblog.pojo.vo.ArticleVo;
import fishblog.service.In.ArticleIn;
import fishblog.aop.GetCache;
import fishblog.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController implements BaseController<ArticleVo,Long>{

    @Autowired
    ArticleIn articleIn;

    @Override
    @GetCache(name = "articles",outTime = 1800)
    @RequestMapping(value = "/pageList", method = {RequestMethod.GET})
    public @ResponseBody PageList<ArticleVo> selectByExample(ArticleVo articleVo) throws Exception {
        return articleIn.selectByExample(articleVo,true);
    }

    @Override
    @RequestMapping(value = "/selectRe", method = {RequestMethod.GET})
    public @ResponseBody List<ArticleVo> selectReByExample(ArticleVo articleVo) throws Exception {
        return articleIn.selectReByExample(articleVo);
    }

    @Override
    @RequestMapping(value = "/selectOnebyId", method = {RequestMethod.GET})
    public @ResponseBody ArticleVo selectByPrimaryKey(Long id) throws Exception {
        return articleIn.selectByPrimaryKey(id);
    }

    @Override
    @RequestMapping(value = "/insertOne", method = {RequestMethod.POST})
    public @ResponseBody int insertSelective(ArticleVo articleVo) throws Exception{
        return articleIn.insertSelective(articleVo);
    }

    @Override
    @RequestMapping(value = "/updateOne", method = {RequestMethod.POST})
    public @ResponseBody int updateByPrimaryKeySelective(ArticleVo articleVo) throws Exception {
        return articleIn.updateByPrimaryKeySelective(articleVo);
    }

    @Override
    @RequestMapping(value = "/updateStateManyOrOne", method = {RequestMethod.POST})
    public @ResponseBody void updateStateByPrimaryKey(String ids) throws Exception {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, Long.class);
        List<Long> list = MAPPER.readValue(ids, javaType);
        articleIn.updateStateByPrimaryKey(list);
    }

    @Override
    public int deleteByPrimaryKey(Long aLong) throws Exception {
        return 0;
    }


}
