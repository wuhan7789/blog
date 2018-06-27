package fishblog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fishblog.util.PageList;
import java.io.Serializable;
import java.util.List;

public interface BaseController<T,ID extends Serializable> {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    /*复杂查询 分页(true)/不分页(false)*/
    PageList<T> selectByExample(T t) throws Exception;

    /*查询重复*/
    List<T> selectReByExample(T t) throws Exception;

    /*根据id查询*/
    T selectByPrimaryKey(Long id) throws Exception;

    /*动态添加*/
    int insertSelective(T t) throws Exception;

    /*动态修改*/
    int updateByPrimaryKeySelective(T t) throws Exception;

    /*逻辑删除*/
    void updateStateByPrimaryKey(String ids) throws Exception;

    /*真删除*/
    int deleteByPrimaryKey(ID id) throws Exception;

}
