package com.nzxs2.domin;

import java.util.Date;

/**
 * @Author Ryan
 * @Date 2017/10/19 23:34
 * @Function
 */
public class SysUser {
    private static final long serialVersionUID = 1L;

    private String id;
    /**

     * 用户昵称

     */
    private String nickname;
    /**

     * 邮箱|登录帐号

     */
    private String email;
    /**

     * 密码

     */
    private String pswd;
    /**

     * 最后登录时间

     */
    private Date lastLoginTime;
    /**

     * 1:有效，0:禁止登录

     */
    private int status;
    /**

     * 最后修改人Id

     */
    private String lastUpdateNameId;
    /**

     * 创建人Id

     */
    private String createNameId;
    /**

     * 最后修改时间

     */
    private Date lastUpdateTime;
    /**

     * 创建时间

     */
    private Date createTime;

    public SysUser(){}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLastUpdateNameId() {
        return lastUpdateNameId;
    }

    public void setLastUpdateNameId(String lastUpdateNameId) {
        this.lastUpdateNameId = lastUpdateNameId;
    }

    public String getCreateNameId() {
        return createNameId;
    }

    public void setCreateNameId(String createNameId) {
        this.createNameId = createNameId;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

}
