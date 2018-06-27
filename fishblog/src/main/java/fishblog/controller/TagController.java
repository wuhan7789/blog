package fishblog.controller;

import com.fasterxml.jackson.databind.JavaType;
import fishblog.aop.GetCache;
import fishblog.pojo.vo.TagVo;
import fishblog.service.In.TagIn;
import fishblog.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/tags")
public class TagController implements BaseController<TagVo,Long>{

    @Autowired
    TagIn tagIn;

    @Override
    @GetCache(name = "tag",outTime = 1800)
    @RequestMapping(value = "/pageList", method = {RequestMethod.GET})
    public @ResponseBody PageList<TagVo> selectByExample(TagVo TagVo) throws Exception {
        return tagIn.selectByExample(TagVo,true);
    }

    @RequestMapping(value = "/tagList", method = {RequestMethod.GET})
    public @ResponseBody PageList<TagVo> tagList(TagVo TagVo) throws Exception {
        return tagIn.selectByExample(TagVo,false);
    }

    @Override
    @RequestMapping(value = "/selectRe", method = {RequestMethod.GET})
    public @ResponseBody List<TagVo> selectReByExample(TagVo TagVo) throws Exception {
        return tagIn.selectReByExample(TagVo);
    }

    @Override
    @RequestMapping(value = "/selectOnebyId", method = {RequestMethod.GET})
    public @ResponseBody TagVo selectByPrimaryKey(Long id) throws Exception {
        return tagIn.selectByPrimaryKey(id);
    }

    @Override
    @RequestMapping(value = "/insertOne", method = {RequestMethod.POST})
    public @ResponseBody int insertSelective(TagVo articleVo) throws Exception {
        return tagIn.insertSelective(articleVo);
    }

    @Override
    @RequestMapping(value = "/updateOne", method = {RequestMethod.POST})
    public @ResponseBody int updateByPrimaryKeySelective(TagVo articleVo) throws Exception {
        return tagIn.updateByPrimaryKeySelective(articleVo);
    }

    @Override
    @RequestMapping(value = "/updateStateManyOrOne", method = {RequestMethod.POST})
    public @ResponseBody void updateStateByPrimaryKey(String ids) throws Exception {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, Long.class);
        List<Long> list = MAPPER.readValue(ids, javaType);
        tagIn.updateStateByPrimaryKey(list);
    }

    @Override
    public int deleteByPrimaryKey(Long aLong) throws Exception {
        return 0;
    }


}
