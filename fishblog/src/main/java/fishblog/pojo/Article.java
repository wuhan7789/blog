package fishblog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {

    private static final long serialVersionUID = -6654344387511844595L;

    private Long id;

    private String title;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date inputtime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date updatetime;

    private Long clicknum;

    private Long authorId;

    private Integer articleTag;

    private Integer state;

    private String content;

    private String introduction;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", inputtime=" + inputtime +
                ", updatetime=" + updatetime +
                ", clicknum=" + clicknum +
                ", authorId=" + authorId +
                ", articleTag=" + articleTag +
                ", state=" + state +
                ", content='" + content + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getInputtime() {
        return inputtime;
    }

    public void setInputtime(Date inputtime) {
        this.inputtime = inputtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Long getClicknum() {
        return clicknum;
    }

    public void setClicknum(Long clicknum) {
        this.clicknum = clicknum;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Integer getArticleTag() {
        return articleTag;
    }

    public void setArticleTag(Integer articleTag) {
        this.articleTag = articleTag;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}