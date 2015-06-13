package com.teddy.lc4e.core.database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class SysTopicStatus {
    @Id
    private Integer intid;

    private Integer inttopicid;

    private Integer intstatusid;

    private Date datecreatetime;

    @PersistenceConstructor
    public SysTopicStatus(Integer intid, Integer inttopicid, Integer intstatusid, Date datecreatetime) {
        this.intid = intid;
        this.inttopicid = inttopicid;
        this.intstatusid = intstatusid;
        this.datecreatetime = datecreatetime;
    }

    public Integer getIntid() {
        return intid;
    }

    public void setIntid(Integer intid) {
        this.intid = intid;
    }

    public Integer getInttopicid() {
        return inttopicid;
    }

    public void setInttopicid(Integer inttopicid) {
        this.inttopicid = inttopicid;
    }

    public Integer getIntstatusid() {
        return intstatusid;
    }

    public void setIntstatusid(Integer intstatusid) {
        this.intstatusid = intstatusid;
    }

    public Date getDatecreatetime() {
        return datecreatetime;
    }

    public void setDatecreatetime(Date datecreatetime) {
        this.datecreatetime = datecreatetime;
    }
}