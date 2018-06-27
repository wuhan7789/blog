package fishblog.service.Impl;

import com.github.pagehelper.PageHelper;
import fishblog.mapper.AuthorMapper;
import fishblog.pojo.Author;
import fishblog.pojo.AuthorExample;
import fishblog.pojo.vo.AuthorVo;
import fishblog.service.In.AuthorIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fishblog.pojo.AuthorExample.Criteria;

import java.util.List;

@Service
public class AuthorImpl extends BaseServiceImpl<AuthorVo,AuthorExample,Long> implements AuthorIn{

    @Autowired
    AuthorMapper authorMapper;

    /* where条件拼接 */
    @Override
    public AuthorExample addParameter(AuthorVo authorVo,Boolean flag) throws Exception {
        AuthorExample authorExample = new AuthorExample();
        Criteria criteria = authorExample.createCriteria();
        if( authorVo != null) {
            Author author = authorVo.getDomain();
            if(author != null) {
                /*条件拼接*/
                if(author.getName()!=null) {criteria.andNameLike("%"+author.getName()+"%");}
            }
            /*是否分页*/
            if(flag) {
                PageHelper.offsetPage(authorVo.getPageStart(),authorVo.getPageSize());
            }
        }
        return authorExample;
    }


    @Override
    public List<AuthorVo> selectReByExample(AuthorVo authorVo) throws Exception {
        AuthorExample authorExample = new AuthorExample();
        Criteria criteria = authorExample.createCriteria();
        criteria.andNameEqualTo(authorVo.getDomain().getName());
        return authorMapper.selectByExample(authorExample);
    }

    @Override
    public void updateStateByPrimaryKey(List ids) throws Exception {
        authorMapper.updateStateByPrimaryKey(ids);
    }

    @Override
    public AuthorVo getUserByUserName(String account) throws Exception{
        return authorMapper.getUserByUserName(account);
    }

}
