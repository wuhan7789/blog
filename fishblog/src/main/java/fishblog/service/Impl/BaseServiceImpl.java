package fishblog.service.Impl;

import com.github.pagehelper.PageInfo;
import fishblog.mapper.BaseMapper;
import fishblog.service.In.BaseServiceIn;
import fishblog.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
public abstract class BaseServiceImpl<T,E,ID extends Serializable> implements BaseServiceIn<T,E,ID> {

    @Autowired
    BaseMapper<T,E,ID> baseMapper;

    @Override
    @Transactional
    public PageList<T> selectByExample(T t,Boolean flag) throws Exception {
        List<T> rows = baseMapper.selectByExample(addParameter(t,flag));
        PageList<T> pageList = new PageList();
        pageList.setRows(rows);
        pageList.setPageInfo(new PageInfo<>(rows));
        return pageList;
    }

    public abstract E addParameter(T t,Boolean flag) throws Exception;


    @Override
    @Transactional
    public int deleteByPrimaryKey(ID id) throws Exception{
        return baseMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int insert(T t) throws Exception{
        return baseMapper.insert(t);
    }

    @Override
    @Transactional
    public int insertSelective(T t) throws Exception {
        return baseMapper.insertSelective(t);
    }

    @Override
    @Transactional
    public T selectByPrimaryKey(ID id) throws Exception{
        return baseMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int updateByPrimaryKeySelective(T t) throws Exception {
        return baseMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    @Transactional
    public int updateByPrimaryKey(T t) throws Exception {
        return baseMapper.updateByPrimaryKey(t);
    }

}
