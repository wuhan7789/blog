package fishblog.service.Impl;

import com.github.pagehelper.PageHelper;
import fishblog.mapper.${Domain}Mapper;
import fishblog.pojo.${Domain};
import fishblog.pojo.${Domain}Example;
import fishblog.pojo.vo.${Domain}Vo;
import fishblog.service.In.${Domain}In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fishblog.pojo.${Domain}Example.Criteria;

import java.util.List;

@Service
public class ${Domain}Impl extends BaseServiceImpl<${Domain}Vo,${Domain}Example,Long> implements ${Domain}In{

    @Autowired
    ${Domain}Mapper ${domain}Mapper;

    /* where条件拼接 */
    @Override
    public ${Domain}Example addParameter(${Domain}Vo ${domain}Vo,Boolean flag) throws Exception {
        ${Domain}Example ${domain}Example = new ${Domain}Example();
        Criteria criteria = ${domain}Example.createCriteria();
        if( ${domain}Vo != null) {
            ${Domain} ${domain} = ${domain}Vo.getDomain();
            if(${domain} != null) {
                /*条件拼接*/
                if(${domain}.getName()!=null) {criteria.andNameLike("%"+${domain}.getName()+"%");}
            }
            /*是否分页*/
            if(flag) {
                PageHelper.offsetPage(${domain}Vo.getPageStart(),${domain}Vo.getPageSize());
            }
        }
        return ${domain}Example;
    }


    @Override
    public List<${Domain}Vo> selectReByExample(${Domain}Vo ${domain}Vo) throws Exception {
        ${Domain}Example ${domain}Example = new ${Domain}Example();
        Criteria criteria = ${domain}Example.createCriteria();
        criteria.andNameEqualTo(${domain}Vo.getDomain().getName());
        return ${domain}Mapper.selectByExample(${domain}Example);
    }

    @Override
    public void updateStateByPrimaryKey(List ids) throws Exception {
        ${domain}Mapper.updateStateByPrimaryKey(ids);
    }

}
