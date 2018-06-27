package fishblog.service.Impl;

import fishblog.pojo.vo.TagTypeVo;
import fishblog.pojo.TagTypeExample;
import fishblog.service.In.TagTypeServiceIn;

import java.util.List;

public class TagTypeServiceImpl extends BaseServiceImpl<TagTypeVo,TagTypeExample,Long> implements TagTypeServiceIn {

    @Override
    public TagTypeExample addParameter(TagTypeVo tagTypeVo, Boolean flag) throws Exception {
        return null;
    }

    @Override
    public List<TagTypeVo> selectReByExample(TagTypeVo tagTypeVo) throws Exception {
        return null;
    }

    @Override
    public void updateStateByPrimaryKey(List ids) throws Exception {

    }

}
