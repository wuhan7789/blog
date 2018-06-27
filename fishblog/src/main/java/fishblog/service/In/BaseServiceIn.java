package fishblog.service.In;

import fishblog.util.PageList;

import java.io.Serializable;
import java.util.List;

public interface BaseServiceIn<T,E,ID extends Serializable> {

    /*复杂查询 分页(true)/不分页(false)*/
    PageList<T> selectByExample(T t,Boolean flag) throws Exception;

    /*表单差重复*/
    List<T> selectReByExample(T t) throws Exception;

    /*根据id查询*/
    T selectByPrimaryKey(ID id) throws Exception;

    /*真删除*/
    int deleteByPrimaryKey(ID id) throws Exception;

    /*全添加 不建议使用*/
    int insert(T t) throws Exception;

    /*动态添加*/
    int insertSelective(T t) throws Exception;

    /*动态修改*/
    int updateByPrimaryKeySelective(T t) throws Exception;

    /*全修改 不建议使用*/
    int updateByPrimaryKey(T t) throws Exception;

    /*逻辑删除*/
    void updateStateByPrimaryKey(List ids) throws Exception;

}
