package com.mai.notification.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 通知实体
 * Created by fengdzh on 2015/9/14.
 */
public class Notification implements Serializable {
	
//	public static final String SYSTEM_LOGO = "http://7xne27.com2.z0.glb.qiniucdn.com/system_default_maitongxue.png";
    public static final Integer NOTIFICATION_IS_READ_YES = 1;
    public static final Integer NOTIFICATION_IS_READ_NO = 0;

    //活动类型：系统消息
    public static final Integer NOTIFICATION_TYPE_SYSTEM = 1;
    //活动类型：申请加入
    public static final Integer NOTIFICATION_TYPE_ACTIVITY_APPLAY_ENTER = 101;
    //活动类型：申请加入.成功
    public static final Integer NOTIFICATION_TYPE_ACTIVITY_APPLAY_ENTER_PASS = 102;
    //活动类型：申请加入.失败
    public static final Integer NOTIFICATION_TYPE_ACTIVITY_APPLAY_ENTER_FAILED = 103;
    //活动动态：审核活动变化，给社长发通知
    public static final Integer NOTIFICATION_TYPE_ACTIVITY_CHANGED = 151;
    

    //活动类型：关注活动
    public static final Integer NOTIFICATION_TYPE_APPLAY_FOLLOW_ACTIVITY = 3;
    
   //活动动态：给成员发通知
    public static final Integer NOTIFICATIONTYPE_TYPE_ACTIVITY_NEWS = 121;

    //加入社团.审核:给管理员发通知
    public static final Integer NOTIFICATIONTYPE_TYPE_JOIN_SOCIETY_APPLAY = 201;
    //加入社团.审核通过:给成员发通知
    public static final Integer NOTIFICATIONTYPE_TYPE_JOIN_SOCIETY_APPLAY_SUCCESS = 202;
    //加入社团.审核被拒绝:给成员发通知
    public static final Integer NOTIFICATIONTYPE_TYPE_JOIN_SOCIETY_APPLAY_FAILED = 203;

    //关注社团申请:给管理员发通知
    public static final Integer NOTIFICATIONTYPE_TYPE_FOLLOW_SOCIETY_APPLAY = 211;

    //社团动态：给成员发通知
    public static final Integer NOTIFICATIONTYPE_TYPE_SOCIETY_NEWS = 221;

    //社团动态：审核社团，给社长发通知
    public static final Integer NOTIFICATIONTYPE_TYPE_SOCIETY_CHECKED = 251;
    
    //社团动态：给社员发通知
    public static final Integer NOTIFICATIONTYPE_TYPE_SOCIETY_PERSON_NOTICE = 261;

    //留言
    public static final Integer NOTIFICATIONTYPE_TYPE_LEAVE_MESSAGE = 10001;

    //是否删除
    public static final Integer NOTIFICATION_ISDELETE_YES = 1;
    public static final Integer NOTIFICATION_ISDELETE_NO = 0;

    //是否处理：已经处理
    public static final Integer ISDEAL_YES = 1;
    //是否处理：未处理
    public static final Integer ISDEAL_NO = 0;

    //是否处理：未处理，提示处理（仅用于传值，非持久化属性）
    public static final Integer ISDEAL_CONFIRM = -1;

    //是否警告提示，是
    public static final Integer ISALERT_YES = 1;
    //是否警告提示，否
    public static final Integer ISALERT_NO = 0;

    //处理结果：通过
    public static final Integer DEALRESULT_CODE_PASS = 1;
    //处理结果：拒绝
    public static final Integer DEALRESULT_CODE_REFUSE = 0;

    public static final String NOTIFICATION_CONTENT_ACTIVITY_AUDIT = "桑心，您报名的{0}活动审核未通过，社长说{1}";

    public static final String NOTIFICATION_CONTENT_ACTIVITY_APPLY_TIP = "童鞋，你报名的{0}活动快开始了，别记错时间地点哦！";

    public static final String NOTIFICATION_CONTENT_ACTIVITY_APPLY_FOLLOW = "童鞋，你关注的{0}活动快开始了，赶紧去报名吧！";


    public static final String NOTIFICATION_CONTENT_ACTIVITY_RUNNING = "活动已开始";

    public static final String NOTIFICATION_CONTENT_ACTIVITY_OFFLINE = "活动已下架";
    //是否需要处理：需要
    public static final Integer NEEDDEAL_NEED = 1;
    //是否需要处理：不需要
    public static final Integer NEEDDEAL_NOT = 0;

    private static final long serialVersionUID = -8455726730365010633L;
    //通知ID
    private String notificationID;
    //通知类型
    private Integer notificationType;
    //通知来源F
    private String fromPersonID;
    //来源人姓名
    private String fromPersonName;
    //通知对象
    private String toPersonID;
    //是否需要处理
    private Integer needDeal;
    //是否已读
    private Integer isRead;
    //是否已经处理
    private Integer isDeal;
    //处理结果
    private String dealResult;
    //处理结果dealResultCode
    private Integer dealResultCode;
    //处理备注
    private String dealNote;
    //处理时间
    private Long dealTime;
    //处理人ID
    private String dealPersonID;
    //处理人姓名
    private String dealPersonName;
    //   通知创建时间
    private Long createTime;
    //通知头像
    private String headerURL;
    //通知标题
    private String title;
    //通知内容
    private String notificationContent;
    //通知对应主体ID
    private String objID;
    //结构化通知内容；
    private String notificationJson;

    //是否警示
    private Integer isAlert;
    //警示信息
    private String alertMessage;
    // 返回数据值
    private Integer returnNum;
    // 留言
    private List<LeaveMessage> leaveMessageList;

    public List<LeaveMessage> getLeaveMessageList() {
        return leaveMessageList;
    }

    public void setLeaveMessageList(List<LeaveMessage> leaveMessageList) {
        this.leaveMessageList = leaveMessageList;
    }

    public Integer getReturnNum() {
        return returnNum;
    }

    public void setReturnNum(Integer returnNum) {
        this.returnNum = returnNum;
    }
    public Integer getIsAlert() {
        return isAlert;
    }

    public void setIsAlert(Integer isAlert) {
        this.isAlert = isAlert;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

    public Integer getDealResultCode() {
        return dealResultCode;
    }

    public void setDealResultCode(Integer dealResultCode) {
        this.dealResultCode = dealResultCode;
    }

    public String getDealNote() {
        return dealNote;
    }

    public void setDealNote(String dealNote) {
        this.dealNote = dealNote;
    }

    public Integer getNeedDeal() {
        return needDeal;
    }

    public void setNeedDeal(Integer needDeal) {
        this.needDeal = needDeal;
    }

    public String getDealPersonID() {
        return dealPersonID;
    }

    public void setDealPersonID(String dealPersonID) {
        this.dealPersonID = dealPersonID;
    }

    public String getDealPersonName() {
        return dealPersonName;
    }

    public void setDealPersonName(String dealPersonName) {
        this.dealPersonName = dealPersonName;
    }

    public String getDealResult() {
        return dealResult;
    }

    public void setDealResult(String dealResult) {
        this.dealResult = dealResult;
    }

    public Long getDealTime() {
        return dealTime;
    }

    public void setDealTime(Long dealTime) {
        this.dealTime = dealTime;
    }

    public String getHeaderURL() {
        return headerURL;
    }

    public void setHeaderURL(String headerURL) {
        this.headerURL = headerURL;
    }

    public Integer getIsDeal() {
        return isDeal;
    }

    public void setIsDeal(Integer isDeal) {
        this.isDeal = isDeal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getFromPersonID() {
        return fromPersonID;
    }

    public void setFromPersonID(String fromPersonID) {
        this.fromPersonID = fromPersonID;
    }


    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }

    public String getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(String notificationID) {
        this.notificationID = notificationID;
    }

    public String getNotificationJson() {
        return notificationJson;
    }

    public void setNotificationJson(String notificationJson) {
        this.notificationJson = notificationJson;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public Integer getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(Integer notificationType) {
        this.notificationType = notificationType;
    }

    public String getObjID() {
        return objID;
    }

    public void setObjID(String objID) {
        this.objID = objID;
    }

    public String getToPersonID() {
        return toPersonID;
    }

    public void setToPersonID(String toPersonID) {
        this.toPersonID = toPersonID;
    }
}
