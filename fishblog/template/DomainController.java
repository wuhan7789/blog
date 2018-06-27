package fishblog.controller;

import com.fasterxml.jackson.databind.JavaType;
import fishblog.pojo.vo.${Domain}Vo;
import fishblog.service.In.${Domain}In;
import fishblog.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/${domain}")
public class ${Domain}Controller implements BaseController<${Domain}Vo,Long>{

    @Autowired
    ${Domain}In ${domain}In;

    @Override
    @RequestMapping(value = "/pageList", method = {RequestMethod.GET})
    public @ResponseBody PageList<${Domain}Vo> selectByExample(${Domain}Vo ${domain}Vo) throws Exception {
        return ${domain}In.selectByExample(${domain}Vo,true);
    }

    @Override
    @RequestMapping(value = "/selectRe", method = {RequestMethod.GET})
    public @ResponseBody List<${Domain}Vo> selectReByExample(${Domain}Vo ${domain}Vo) throws Exception {
        return ${domain}In.selectReByExample(${domain}Vo);
    }

    @Override
    @RequestMapping(value = "/selectOnebyId", method = {RequestMethod.GET})
    public @ResponseBody ${Domain}Vo selectByPrimaryKey(Long id) throws Exception {
        return ${domain}In.selectByPrimaryKey(id);
    }

    @Override
    @RequestMapping(value = "/insertOne", method = {RequestMethod.POST})
    public @ResponseBody int insertSelective(${Domain}Vo ${domain}Vo) throws Exception {
        return ${domain}In.insertSelective(${domain}Vo);
    }

    @Override
    @RequestMapping(value = "/updateOne", method = {RequestMethod.POST})
    public @ResponseBody int updateByPrimaryKeySelective(${Domain}Vo ${domain}Vo) throws Exception {
        return ${domain}In.updateByPrimaryKeySelective(${domain}Vo);
    }

    @Override
    @RequestMapping(value = "/updateStateManyOrOne", method = {RequestMethod.POST})
    public @ResponseBody void updateStateByPrimaryKey(String ids) throws Exception {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, Long.class);
        List<Long> list = MAPPER.readValue(ids, javaType);
        ${domain}In.updateStateByPrimaryKey(list);
    }

    @Override
    public int deleteByPrimaryKey(Long aLong) throws Exception {
        return 0;
    }


}
