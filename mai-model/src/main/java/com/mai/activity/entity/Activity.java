package com.mai.activity.entity;

import com.mai.activity.dto.ActivityOperationDTO;
import com.mai.user.entity.Person;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 活动
 * Created by Administrator on 2015/9/6.
 */
public class Activity implements Serializable {


    //ready
    public static final Integer ACTIVITY_STATUS_READY = 1;
    public static final Integer ACTIVITY_STATUS_RUNNING = 2;
    public static final Integer ACTIVITY_STATUS_CLOSE = 4;
    // 默认值：审核中
    public static final Integer ACTIVITY_STATUS_NON_CHECKED = 0;
    // 状态：下架：不在任何地方显示
    public static final Integer ACTIVITY_STATUS_OFFLINE = -3;
    // 状态：审核不通过
    public static final Integer ACTIVITY_STATUS_NOTPASS = -2;
    // 状态：草稿
    public static final Integer ACTIVITY_STATUS_DRAFT = -4;
    // 状态：后台逻辑删除
    public static final Integer ACTIVITY_STATUS_DELETE = -5;
    // 类型：默认
    public static final Integer ACTIVITY_TYPE_DEFAULT = 0;
    // 类型：照片墙
    public static final Integer ACTIVITY_TYPE_PHOTO = 11;
    // 类型：明星合影
    public static final Integer ACTIVITY_TYPE_CARDPHOTO = 12;

    //加入成员已满
    public static final Integer ACTIVITY_MEMBER_FULL = 3;
    //已经关注
    public static final Integer ISFOLLOWED_YES = 1;
    // 需要审核
    public static final Boolean NEEDCHECK_YES = true;
    // 不需要审核
    public static final Boolean NEEDCHECK_NOT = false;
    // 内部活动
    public static final Boolean ISINNER_YES = true;
    // 外部活动
    public static final Boolean ISINNER_NOT = false;
    //当前成员与该活动关系：已加入、加入审核中、未加入
    //已加入
    public static final Integer PARELATION_JOINED = 1;
    //审核中
    public static final Integer PARELATION_CHECKED = 2;
    //没有关系
    public static final Integer PARELATION_NONE = -1;
    private static final long serialVersionUID = 5011891265752817523L;
    //活动ID
    private String activityID;
    //活动名称
    private String activityTitle;
    //活动状态
    private Integer activityStatus;
    //活动类型
    private Integer activityType;
    //活动人数
    private Integer maxPersonNum;
    //当前加入人数
    private Integer joinPersonNum = 0;
    //区域位置
    private String location;
    //具体地址
    private String address;
    //纬度
    private String gpsLatitude;
    //经度
    private String gpsLongitude;
    //电话号码
    private String phoneNum;
    //类型ID
    private String categoryID;
    //类型名
    private String categoryName;
    //赞助商ico
    private String supportICO;
    //赞助商一句话描述
    private String supportDescription;
    //封面图
    private String coverURL;
    //是否需要审核
    private Boolean needCheck;
    //是否为内部活动
    private Boolean isInner;
    //是否为推荐
    private Integer recommendNo;
    //社团ID
    private String societyID;
    //社团名
    private String societyName;
    //活动创建时间
    private Long createTime;
    //活动开始时间
    private Long startTime;
    //活动结束时间
    private Long endTime;
    //热度
    private Integer hotNum;
    //关注数
    private Integer followNum = 0;
    //注意事项
    private String notes;
    //管理员ID
    private String adminID;
    //管理员姓名
    private String adminName;
    //活动开始显示字符串
    private String showStartTime;
    //活动结束显示字符串
    private String showEndTime;
    //冗余学校ID
    private String schoolID;

    //冗余学校名称schoolName，显示用
    private String schoolName;

    //活动详情ID
    private String activityDetailID;
    //活动详情访问静态页
    private String activityDetail;
    //是否下入日志
    private Integer isLog = 0;
    //GPS坐标计算距离
    private String geohash;
    //分享URL
    private String shareURL;
    //分享描述
    private String shareDESC;

    ///////////////////////////////////计算所得////////////////
    //距离:计算得到的。
    private String distance;
    //当前成员与该活动关系：已加入、加入审核中、未加入:计算所得
    private Integer relation;
    //是否已经被关注:计算得到
    private Integer isFollow;

    //加入的成员
    private List<Person> joinPersonList;
    //待审核的成员
    private List<Person> checkPersonList;
    //审核未通过的成员
    private List<Person> checkFailedPersonList;
    //标签:打的标签;
    private String tagIDs;
    //标签名称，传给移动端
    private String tagNames;
    //活动详情内容
    private String activityDetailContent;

    private Integer systemfollowNum;

    private ActivityOperationDTO activityOperationDTO;
    // 附加信息【非持久化】，传递非活动类附带信息
    private Map additionalInfo;

    public Map getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(Map additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public ActivityOperationDTO getActivityOperationDTO() {
        return activityOperationDTO;
    }

    public void setActivityOperationDTO(ActivityOperationDTO activityOperationDTO) {
        this.activityOperationDTO = activityOperationDTO;
    }

    public Integer getActivityType() {
        return activityType;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    public String getTagNames() {
        return tagNames;
    }

    public void setTagNames(String tagNames) {
        this.tagNames = tagNames;
    }

    public Integer getRelation() {
        return relation;
    }

    public void setRelation(Integer relation) {
        this.relation = relation;
    }

    public String getTagIDs() {
        return tagIDs;
    }

    public void setTagIDs(String tagIDs) {
        this.tagIDs = tagIDs;
    }

    ///////////////////////////////////计算所得////////////////


    public String getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(String schoolID) {
        this.schoolID = schoolID;
    }


    public String getActivityDetailID() {
        return activityDetailID;
    }

    public void setActivityDetailID(String activityDetailID) {
        this.activityDetailID = activityDetailID;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(Integer isFollow) {
        this.isFollow = isFollow;
    }

    public Integer getFollowNum() {
        return followNum;
    }

    public void setFollowNum(Integer followNum) {
        this.followNum = followNum;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getSupportDescription() {
        return supportDescription;
    }

    public void setSupportDescription(String supportDescription) {
        this.supportDescription = supportDescription;
    }

    public String getSupportICO() {
        return supportICO;
    }

    public void setSupportICO(String supportICO) {
        this.supportICO = supportICO;
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    public Integer getHotNum() {
        return hotNum;
    }

    public void setHotNum(Integer hotNum) {
        this.hotNum = hotNum;
    }

    public String getActivityDetail() {
        return activityDetail;
    }

    public void setActivityDetail(String activityDetail) {
        this.activityDetail = activityDetail;
    }

    public String getActivityID() {
        return activityID;
    }

    public void setActivityID(String activityID) {
        this.activityID = activityID;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Person> getCheckFailedPersonList() {
        return checkFailedPersonList;
    }

    public void setCheckFailedPersonList(List<Person> checkFailedPersonList) {
        this.checkFailedPersonList = checkFailedPersonList;
    }

    public List<Person> getCheckPersonList() {
        return checkPersonList;
    }

    public void setCheckPersonList(List<Person> checkPersonList) {
        this.checkPersonList = checkPersonList;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getGpsLatitude() {
        return gpsLatitude;
    }

    public void setGpsLatitude(String gpsLatitude) {
        this.gpsLatitude = gpsLatitude;
    }


    public Boolean getIsInner() {
        return isInner;
    }

    public void setIsInner(Boolean isInner) {
        this.isInner = isInner;
    }

    public List<Person> getJoinPersonList() {
        return joinPersonList;
    }

    public void setJoinPersonList(List<Person> joinPersonList) {
        this.joinPersonList = joinPersonList;
    }

    public Integer getJoinPersonNum() {
        return joinPersonNum;
    }

    public void setJoinPersonNum(Integer joinPersonNum) {
        this.joinPersonNum = joinPersonNum;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getMaxPersonNum() {
        return maxPersonNum;
    }

    public void setMaxPersonNum(Integer maxPersonNum) {
        this.maxPersonNum = maxPersonNum;
    }

    public Boolean getNeedCheck() {
        return needCheck;
    }

    public void setNeedCheck(Boolean needCheck) {
        this.needCheck = needCheck;
    }

    public String getGpsLongitude() {
        return gpsLongitude;
    }

    public void setGpsLongitude(String gpsLongitude) {
        this.gpsLongitude = gpsLongitude;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getRecommendNo() {
        return recommendNo;
    }

    public void setRecommendNo(Integer recommendNo) {
        this.recommendNo = recommendNo;
    }

    public String getSocietyID() {
        return societyID;
    }

    public void setSocietyID(String societyID) {
        this.societyID = societyID;
    }

    public String getSocietyName() {
        return societyName;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public String getShowStartTime() {
        return showStartTime;
    }

    public void setShowStartTime(String showStartTime) {
        this.showStartTime = showStartTime;
    }

    public String getShowEndTime() {
        return showEndTime;
    }

    public void setShowEndTime(String showEndTime) {
        this.showEndTime = showEndTime;
    }

    public String getActivityDetailContent() {
        return activityDetailContent;
    }

    public void setActivityDetailContent(String activityDetailContent) {
        this.activityDetailContent = activityDetailContent;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Integer getIsLog() {
        return isLog;
    }

    public void setIsLog(Integer isLog) {
        this.isLog = isLog;
    }

    public String getGeohash() {
        return geohash;
    }

    public void setGeohash(String geohash) {
        this.geohash = geohash;
    }

    public Integer getSystemfollowNum() {
        return systemfollowNum;
    }

    public void setSystemfollowNum(Integer systemfollowNum) {
        this.systemfollowNum = systemfollowNum;
    }

    public String getShareDESC() {
        return shareDESC;
    }

    public void setShareDESC(String shareDESC) {
        this.shareDESC = shareDESC;
    }

    public String getShareURL() {
        return shareURL;
    }

    public void setShareURL(String shareURL) {
        this.shareURL = shareURL;
    }
}
