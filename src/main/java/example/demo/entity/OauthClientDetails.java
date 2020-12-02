package example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("oauth_client_details")
//@Data
public class OauthClientDetails implements Serializable {

    @TableId
    private String id;
    /**
     * 应用名称
     */
    private String appName;
    /**
     * 应用key
     */
    private String appKey;
    /**
     * 应用密钥
     */
    private String appSecret;
    /**
     * 资源id集合
     */
    private String resourceIds;
    /**
     * oauth权限范围
     */
    private String scope;
    /**
     * 支持的授权类型
     */
    private String authorizedGrantTypes;
    /**
     * 重定向uri
     */
    private String redirectUri;
    /**
     * security权限值
     */
    private String authorities;
    /**
     * access_token有效时间
     */
    private Integer accessTokenValidity;
    /**
     * refresh_token有效时间
     */
    private Integer refreshTokenValidity;
    /**
     * 客户端的其他信息，必须为json格式
     */
    private String additionalInformation;
    /**
     * 是否已存档
     */
    private Integer archived;
    /**
     * 是否是信任的,0:不受信任，1：信任的
     */
    private Integer trusted;
    /**
     * 是否自动跳过approve操作，默认为false，取值范围：true,false,read,write
     */
    private String autoapprove;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;
    /**
     * 更新人
     */
    private String updateBy;

    @Override
    public String toString() {
        return "OauthClientDetails{" +
                "id='" + id + '\'' +
                ", appName='" + appName + '\'' +
                ", appKey='" + appKey + '\'' +
                ", appSecret='" + appSecret + '\'' +
                ", resourceIds='" + resourceIds + '\'' +
                ", scope='" + scope + '\'' +
                ", authorizedGrantTypes='" + authorizedGrantTypes + '\'' +
                ", redirectUri='" + redirectUri + '\'' +
                ", authorities='" + authorities + '\'' +
                ", accessTokenValidity=" + accessTokenValidity +
                ", refreshTokenValidity=" + refreshTokenValidity +
                ", additionalInformation='" + additionalInformation + '\'' +
                ", archived=" + archived +
                ", trusted=" + trusted +
                ", autoapprove='" + autoapprove + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }

    /**
     * 更新时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updateTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public Integer getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    public Integer getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public Integer getArchived() {
        return archived;
    }

    public void setArchived(Integer archived) {
        this.archived = archived;
    }

    public Integer getTrusted() {
        return trusted;
    }

    public void setTrusted(Integer trusted) {
        this.trusted = trusted;
    }

    public String getAutoapprove() {
        return autoapprove;
    }

    public void setAutoapprove(String autoapprove) {
        this.autoapprove = autoapprove;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
