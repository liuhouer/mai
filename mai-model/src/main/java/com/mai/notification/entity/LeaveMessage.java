package com.mai.notification.entity;

import java.io.IOException;
import java.io.Serializable;

/**
 * LeaveMessage
 * 留言
 * @author Yao Jinwei
 * @date 2015-12-17 15:31:10
 */
public class LeaveMessage implements Serializable {

    // 是否删除
    public static final Integer LEAVEMESSAGE_ISDELETE_YES = 1;
    public static final Integer LEAVEMESSAGE_ISDELETE_NO = 0;

    private static final long serialVersionUID = -7857994138916478033L;

    private String leaveMessageID;
    private String fromPersonID;
    private String fromPersonName;
    private Long createTime;
    private Integer isDelete;
    private String content;
    private String replayToPersonID;
    private String replayToPersonName;
    private String replayMessageID;
    private String notificationID;

    public String getLeaveMessageID() {
        return leaveMessageID;
    }

    public void setLeaveMessageID(String leaveMessageID) {
        this.leaveMessageID = leaveMessageID;
    }

    public String getFromPersonID() {
        return fromPersonID;
    }

    public void setFromPersonID(String fromPersonID) {
        this.fromPersonID = fromPersonID;
    }

    public String getFromPersonName() {
        return fromPersonName;
    }

    public void setFromPersonName(String fromPersonName) {
        this.fromPersonName = fromPersonName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReplayToPersonID() {
        return replayToPersonID;
    }

    public void setReplayToPersonID(String replayToPersonID) {
        this.replayToPersonID = replayToPersonID;
    }

    public String getReplayToPersonName() {
        return replayToPersonName;
    }

    public void setReplayToPersonName(String replayToPersonName) {
        this.replayToPersonName = replayToPersonName;
    }

    public String getReplayMessageID() {
        return replayMessageID;
    }

    public void setReplayMessageID(String replayMessageID) {
        this.replayMessageID = replayMessageID;
    }

    public String getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(String notificationID) {
        this.notificationID = notificationID;
    }
}
