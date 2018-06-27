package fishblog.util;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

public class PageList<T>  implements Serializable{

    private static final long serialVersionUID = -2670097853861306727L;

    //单页记录数
    List<T> rows;

    //分页所有数据，easyui分页组件下只需要total
    PageInfo pageInfo;


    @Override
    public String toString() {
        return "PageList{" +
                "rows=" + rows +
                ", pageInfo=" + pageInfo +
                '}';
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
