package fishblog.service.In;

import fishblog.pojo.ArticleExample;
import fishblog.pojo.vo.ArticleVo;

public interface ArticleIn extends BaseServiceIn<ArticleVo,ArticleExample,Long>{

    ArticleVo selectByExampleWithBLOBs(ArticleVo articleVo) throws Exception;

}
