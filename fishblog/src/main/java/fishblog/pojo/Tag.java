package fishblog.pojo;

import java.io.Serializable;

public class Tag  implements Serializable {

    private static final long serialVersionUID = -1122122409142230785L;

    private Long id;

    private String name;

    private Integer state;

    private Integer tagtypeId;

    private Integer articleTotalNum;

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state=" + state +
                ", tagtypeId=" + tagtypeId +
                ", articleTotalNum=" + articleTotalNum +
                '}';
    }

    public Integer getArticleTotalNum() {
        return articleTotalNum;
    }

    public void setArticleTotalNum(Integer articleTotalNum) {
        this.articleTotalNum = articleTotalNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getTagtypeId() {
        return tagtypeId;
    }

    public void setTagtypeId(Integer tagtypeId) {
        this.tagtypeId = tagtypeId;
    }
}