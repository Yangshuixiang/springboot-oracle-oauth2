package example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * oauth统一认证用户
 */
//@Data
@TableName("oauth_user")
public class OauthUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId
    private Long id;
    /**
     * 账号
     */
    private String account;
    /**
     * 昵称
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机
     */
    private String phone;
    /**
     * 性别，1：男，2：女
     */
    private Integer gender;
    /**
     * 状态，0：正常，1：冻结
     */
    private Integer status;
    /**
     * 删除标识，0：正常，1：已删除
     */
    private Integer delFlag;
    private Long createBy;
    private LocalDateTime createTime;
    private Long updateBy;
    private LocalDateTime updateTime;
    /**
     * 用户拥有的角色列表
     */
    @TableField(exist = false)
    private List<OauthRole> roleList;

    @Override
    public String toString() {
        return "OauthUser{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", gender=" + gender +
                ", status=" + status +
                ", delFlag=" + delFlag +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                ", updateBy=" + updateBy +
                ", updateTime=" + updateTime +
                ", roleList=" + roleList +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public List<OauthRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<OauthRole> roleList) {
        this.roleList = roleList;
    }
}
