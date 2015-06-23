package com.teddy.lc4e.core.database.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Document
public class UserBlocked {
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private ObjectId user;

    private Set<ObjectId> blockedUsers;

    private Date createTime;

    private Date updateTime;

    public UserBlocked() {
    }

    @PersistenceConstructor

    public UserBlocked(ObjectId id, ObjectId user, Set<ObjectId> blockedUsers, Date createTime, Date updateTime) {
        this.id = id;
        this.user = user;
        this.blockedUsers = blockedUsers;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getUser() {
        return user;
    }

    public void setUser(ObjectId user) {
        this.user = user;
    }

    public Set<ObjectId> getBlockedUsers() {
        return blockedUsers;
    }

    public void setBlockedUsers(Set<ObjectId> blockedUsers) {
        this.blockedUsers = blockedUsers;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}