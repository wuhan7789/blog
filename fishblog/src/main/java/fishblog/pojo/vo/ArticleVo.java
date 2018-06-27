package fishblog.pojo.vo;

import fishblog.pojo.Article;

public class ArticleVo extends BaseVo<Article>{

    private String tag;

    private String authorName;

    @Override
    public String toString() {
        return "ArticleVo{" +
                "tag='" + tag + '\'' +
                ", authorName='" + authorName + '\'' +
                '}' + super.toString();
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
