package fishblog.service.Impl;

import com.github.pagehelper.PageHelper;
import fishblog.mapper.ArticleMapper;
import fishblog.pojo.Article;
import fishblog.pojo.ArticleExample;
import fishblog.pojo.vo.ArticleVo;
import fishblog.service.In.ArticleIn;
import fishblog.service.In.StaticPageSerivceIn;
import fishblog.util.CreateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fishblog.pojo.ArticleExample.Criteria;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ArticleImpl extends BaseServiceImpl<ArticleVo,ArticleExample,Long> implements ArticleIn{

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    StaticPageSerivceIn staticPageSerivceIn;

    @Override
    @Transactional
    public int insertSelective(ArticleVo articleVo) throws Exception {
        articleVo.getDomain().setInputtime(new Date());
        int i = articleMapper.insertSelective(articleVo);
        staticPageSerivceIn.createStaticArticlePage(articleVo.getDomain().getId());
        return i;
    }

    /* where条件拼接 */
    @Override
    public ArticleExample addParameter(ArticleVo articleVo,Boolean flag) throws Exception {
        ArticleExample articleExample = new ArticleExample();
        Criteria criteria = articleExample.createCriteria();
        if( articleVo != null) {
            Article article = articleVo.getDomain();
            if(article != null) {
                /*条件拼接*/
            }
            /*是否分页*/
            if(flag) {
                PageHelper.offsetPage(articleVo.getPageStart(),articleVo.getPageSize());
            }
        }
        return articleExample;
    }


    @Override
    @Transactional
    public List<ArticleVo> selectReByExample(ArticleVo articleVo) throws Exception {
        ArticleExample articleExample = new ArticleExample();
        Criteria criteria = articleExample.createCriteria();
        return articleMapper.selectByExample(articleExample);
    }

    @Override
    @Transactional
    public void updateStateByPrimaryKey(List ids) throws Exception {
        articleMapper.updateStateByPrimaryKey(ids);
    }

    @Override
    @Transactional
    public int updateByPrimaryKeySelective(ArticleVo articleVo) throws Exception {
        int i = articleMapper.updateByPrimaryKeySelective(articleVo);
        staticPageSerivceIn.createStaticArticlePage(articleVo.getDomain().getId());
        return i;
    }

    @Override
    @Transactional
    public ArticleVo selectByExampleWithBLOBs(ArticleVo articleVo) throws Exception {
        ArticleExample articleExample = new ArticleExample();
        Criteria criteria = articleExample.createCriteria();
        criteria.andIdEqualTo(articleVo.getDomain().getId());
        return articleMapper.selectByExampleWithBLOBs(articleExample);
    }


}
