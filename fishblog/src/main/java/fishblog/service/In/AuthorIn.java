package fishblog.service.In;

import fishblog.pojo.AuthorExample;
import fishblog.pojo.vo.AuthorVo;

public interface AuthorIn extends BaseServiceIn<AuthorVo,AuthorExample,Long>{

    AuthorVo getUserByUserName(String account)throws Exception;

}
