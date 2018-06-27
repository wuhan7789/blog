package fishblog.mapper;

import fishblog.pojo.ArticleExample;
import fishblog.pojo.vo.ArticleVo;


public interface ArticleMapper extends BaseMapper<ArticleVo,ArticleExample,Long>{

    ArticleVo selectByExampleWithBLOBs(ArticleExample example) throws Exception;

    int updateByPrimaryKeyWithBLOBs(ArticleVo record);



}