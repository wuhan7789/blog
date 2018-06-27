package fishblog.service.Impl;

import com.github.pagehelper.PageHelper;
import fishblog.mapper.TagMapper;
import fishblog.pojo.Tag;
import fishblog.pojo.TagExample;
import fishblog.pojo.vo.TagVo;
import fishblog.service.In.TagIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fishblog.pojo.TagExample.Criteria;

import java.util.List;

@Service
public class TagImpl extends BaseServiceImpl<TagVo,TagExample,Long> implements TagIn{

    @Autowired
    TagMapper TagMapper;

    /* where条件拼接 */
    @Override
    public TagExample addParameter(TagVo TagVo,Boolean flag) throws Exception {
        TagExample TagExample = new TagExample();
        Criteria criteria = TagExample.createCriteria();
        if( TagVo != null) {
            Tag Tag = TagVo.getDomain();
            if(Tag != null) {
                /*条件拼接*/
                if(Tag.getName()!=null) {criteria.andNameLike("%"+Tag.getName()+"%");}
            }
            /*是否分页*/
            if(flag) {
                PageHelper.offsetPage(TagVo.getPageStart(),TagVo.getPageSize());
            }
        }
        return TagExample;
    }


    @Override
    public List<TagVo> selectReByExample(TagVo TagVo) throws Exception {
        TagExample TagExample = new TagExample();
        Criteria criteria = TagExample.createCriteria();
        criteria.andNameEqualTo(TagVo.getDomain().getName());
        return TagMapper.selectByExample(TagExample);
    }

    @Override
    public void updateStateByPrimaryKey(List ids) throws Exception {
        TagMapper.updateStateByPrimaryKey(ids);
    }

}
