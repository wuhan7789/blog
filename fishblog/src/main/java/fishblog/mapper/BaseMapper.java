package fishblog.mapper;
import java.io.Serializable;
import java.util.List;

public interface BaseMapper<T,E,ID extends Serializable> {

    /*复杂查询*/
    List<T> selectByExample(E e) throws Exception;
    /*根据id查询*/
    T selectByPrimaryKey(ID id) throws Exception;
    /*真删除*/
    int deleteByPrimaryKey(ID id) throws Exception;
    /*全添加*/
    int insert(T t) throws Exception;
    /*动态添加*/
    int insertSelective(T t) throws Exception;
    /*动态修改*/
    int updateByPrimaryKeySelective(T t) throws Exception;
    /*全修改*/
    int updateByPrimaryKey(T t) throws Exception;
    /*逻辑删除*/
    void updateStateByPrimaryKey(List ids) throws Exception;

}
