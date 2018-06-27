package fishblog.controller;

import com.fasterxml.jackson.databind.JavaType;
import fishblog.pojo.vo.AuthorVo;
import fishblog.service.In.AuthorIn;
import fishblog.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/author")
public class AuthorController implements BaseController<AuthorVo,Long>{

    @Autowired
    AuthorIn authorIn;

    @Override
    @RequestMapping(value = "/pageList", method = {RequestMethod.GET})
    public @ResponseBody PageList<AuthorVo> selectByExample(AuthorVo authorVo) throws Exception {
        return authorIn.selectByExample(authorVo,true);
    }

    @Override
    @RequestMapping(value = "/selectRe", method = {RequestMethod.GET})
    public @ResponseBody List<AuthorVo> selectReByExample(AuthorVo authorVo) throws Exception {
        return authorIn.selectReByExample(authorVo);
    }

    @Override
    @RequestMapping(value = "/selectOnebyId", method = {RequestMethod.GET})
    public @ResponseBody AuthorVo selectByPrimaryKey(Long id) throws Exception {
        return authorIn.selectByPrimaryKey(id);
    }

    @Override
    @RequestMapping(value = "/insertOne", method = {RequestMethod.POST})
    public @ResponseBody int insertSelective(AuthorVo authorVo) throws Exception {
        return authorIn.insertSelective(authorVo);
    }

    @Override
    @RequestMapping(value = "/updateOne", method = {RequestMethod.POST})
    public @ResponseBody int updateByPrimaryKeySelective(AuthorVo authorVo) throws Exception {
        return authorIn.updateByPrimaryKeySelective(authorVo);
    }

    @Override
    @RequestMapping(value = "/updateStateManyOrOne", method = {RequestMethod.POST})
    public @ResponseBody void updateStateByPrimaryKey(String ids) throws Exception {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, Long.class);
        List<Long> list = MAPPER.readValue(ids, javaType);
        authorIn.updateStateByPrimaryKey(list);
    }

    @Override
    public int deleteByPrimaryKey(Long aLong) throws Exception {
        return 0;
    }


}
