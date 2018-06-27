package fishblog.pojo;

import java.io.Serializable;
import java.util.Date;

public class Author  implements Serializable {

    private static final long serialVersionUID = 4511448773501134826L;
    private Integer id;

    private String name;

    private String account;

    private String password;

    private String email;

    private String remark;

    private String headimg;

    private Long articletotalnum;

    private Date inputtime;

    private Date recenttime;

    private Integer roleId;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", remark='" + remark + '\'' +
                ", headimg='" + headimg + '\'' +
                ", articletotalnum=" + articletotalnum +
                ", inputtime=" + inputtime +
                ", recenttime=" + recenttime +
                ", roleId=" + roleId +
                ", state=" + state +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg == null ? null : headimg.trim();
    }

    public Long getArticletotalnum() {
        return articletotalnum;
    }

    public void setArticletotalnum(Long articletotalnum) {
        this.articletotalnum = articletotalnum;
    }

    public Date getInputtime() {
        return inputtime;
    }

    public void setInputtime(Date inputtime) {
        this.inputtime = inputtime;
    }

    public Date getRecenttime() {
        return recenttime;
    }

    public void setRecenttime(Date recenttime) {
        this.recenttime = recenttime;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}