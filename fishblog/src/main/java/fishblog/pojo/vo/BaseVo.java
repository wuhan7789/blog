package fishblog.pojo.vo;

import java.io.Serializable;

public class BaseVo<T> implements Serializable {

    private static final long serialVersionUID = 7211128142851073986L;

    //当前第几页
    private Integer indexPage;

    //开始下标
    private Integer pageStart;

    //每页条数
    private Integer pageSize = 10;

    //包装类 主类
    private T domain;

    @Override
    public String toString() {
        return "BaseVo{" +
                "indexPage=" + indexPage +
                ", pageStart=" + pageStart +
                ", pageSize=" + pageSize +
                ", domain=" + domain +
                '}';
    }

    public T getDomain() {
        return domain;
    }

    public void setDomain(T domain) {
        this.domain = domain;
    }

    public void setIndexPage(Integer indexPage) {
        this.indexPage = indexPage;
    }

    public void setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getIndexPage() {
        return indexPage;
    }

    public Integer getPageStart() {
        if(indexPage == null || indexPage <=1 ) {
            indexPage = 1;
            pageStart = 0;
        } else {
            pageStart = (indexPage - 1)*pageSize;
        }
        return pageStart;
    }

    public Integer getPageSize() {
        return pageSize;
    }


}
