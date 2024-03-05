/**
 * 功能：
 * 作者：yixiangxi
 * 日期：2024/2/25 9:13
 **/
package com.example.entity;

public class Comment {
    /**
     * ID
     */
    private Integer id;
    /**
     * 内容
     */
    private String content;
    /**
     * 评论人ID
     */
    private Integer userId;


    /**
     * 评论人名称
     */
    private String userName;
    /**
     * 父级ID
     */
    private Integer pid;
    /**
     * 根节点ID
     */
    private Integer rootId;
    /**
     * 评论时间
     */
    private String time;


    /**
     * 博客ID
     */
    private Integer fid;

    private String module;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getRootId() {
        return rootId;
    }

    public void setRootId(Integer rootId) {
        this.rootId = rootId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
