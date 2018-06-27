package fishblog.mapper;
import fishblog.pojo.AuthorExample;
import fishblog.pojo.vo.AuthorVo;


public interface AuthorMapper extends BaseMapper<AuthorVo,AuthorExample,Long>{

    AuthorVo getUserByUserName(String account) throws Exception;

}