package com.mai.society.entity;

import com.mai.society.dto.JoinSocietyOperationDTO;
import com.mai.user.entity.Person;

import java.io.Serializable;
import java.util.List;

/**
 * 社团信息
 * Created by fengdzh on 2015/9/16.
 */
public class Society implements Serializable {

    public static final Integer RECOMMENDNO_CORRECTED_VALUE = 100000000;
	/**
     * 社团状态：审核中=待审核
     */
    public static final Integer STATUS_CHECKED = 0;
    /**
     * 社团状态：关闭==审核不通过
     */
    public static final Integer STATUS_CLOSE = -1;
    /**
     * 社团状态：已发布
     */
    public static final Integer STATUS_RELEASED = 1;
    /**
     * 社团状态：正常==审核通过
     */
    public static final Integer STATUS_NORMAL = 2;
    /**
     * 社团状态：下架
     */
    public static final Integer STATUS_OFFLINE = 3;

    //已经关注
    public static final Integer IS_FOLLOWED_YES = 1;
    //没有关注
    public static final Integer IS_FOLLOWED_NO = 0;

    public static final String ORDERRULE_DEFAULT = "0";
    public static final String ORDERRULE_HOT = "1";

    private static final long serialVersionUID = -7818632557287584316L;
    private String societyID;
    private String societyName;
    private String schoolID;
    private String schoolName;
    private String societyCategoryID;
    private String societyCategoryName;
    private Integer memberNum = 0;
    private String buildDate;
    private Long createTime;
    private Integer praiseNum;
    private String slogan;
    //纬度
    private String gpsLatitude;
    //经度
    private String gpsLongitude;
    private String locationDetail;
    private String phoneNum;
    private String coverURL;
    private String adminID;
    private String adminName;
    private Integer status;
    private String societyNote;
    private String applyNote;
    //当前加入人数
    private Integer joinPersonNum = 0;
    //所在地区
    private String locationID;
    //社团等级
    private Integer level;
    //LOGO
    private String societyLOGO;
    //是否下入日志
    private Integer isLog = 0;
    //是否为推荐,0为不推荐，推荐填写整数作为推荐权重
    private Integer recommendNo = 0;

    private Integer followNum;

    //GPS坐标计算距离
    private String geohash;

    //分享URL
    private String shareURL;
    //分享描述
    private String shareDESC;

    /////////////////// 计算所得/////////////////////////
    //排名显示：状元、榜眼、昙花，数据库不需要保存
    private String topName;
    //计算出来的地理位置:数据库不保存
    private String distance;
    //加入成员的信息：临时使用。
    private List<Person> memberList;
    
    //显示社团的申请时间
    private String showTime;
    //是否已经被关注:计算得到
    private Integer isFollow;

    private JoinSocietyOperationDTO joinSocietyOperationDTO;

    private Integer systemfollowNum;

    private Integer systempraiseNum;

    //标签:打的标签;
    private String tagIDs;

    public JoinSocietyOperationDTO getJoinSocietyOperationDTO() {
        return joinSocietyOperationDTO;
    }

    public void setJoinSocietyOperationDTO(JoinSocietyOperationDTO joinSocietyOperationDTO) {
        this.joinSocietyOperationDTO = joinSocietyOperationDTO;
    }

    public Integer getFollowNum() {
        return followNum;
    }

    public void setFollowNum(Integer followNum) {
        this.followNum = followNum;
    }

    public Integer getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(Integer isFollow) {
        this.isFollow = isFollow;
    }

    public List<Person> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Person> memberList) {
        this.memberList = memberList;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getTopName() {
        return topName;
    }

    public void setTopName(String topName) {
        this.topName = topName;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    public String getApplyNote() {
        return applyNote;
    }

    public void setApplyNote(String applyNote) {
        this.applyNote = applyNote;
    }

    public String getSocietyNote() {
        return societyNote;
    }

    public void setSocietyNote(String societyNote) {
        this.societyNote = societyNote;
    }

    public String getSocietyCategoryID() {
        return societyCategoryID;
    }

    public void setSocietyCategoryID(String societyCategoryID) {
        this.societyCategoryID = societyCategoryID;
    }

    public String getSocietyCategoryName() {
        return societyCategoryName;
    }

    public void setSocietyCategoryName(String societyCategoryName) {
        this.societyCategoryName = societyCategoryName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(String buildDate) {
        this.buildDate = buildDate;
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

    public String getGpsLongitude() {
        return gpsLongitude;
    }

    public void setGpsLongitude(String gpsLongitude) {
        this.gpsLongitude = gpsLongitude;
    }

    public String getLocationDetail() {
        return locationDetail;
    }

    public void setLocationDetail(String locationDetail) {
        this.locationDetail = locationDetail;
    }

    public Integer getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(Integer memberNum) {
        this.memberNum = memberNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
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

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(String schoolID) {
        this.schoolID = schoolID;
    }

    public Integer getJoinPersonNum() {
        return joinPersonNum;
    }

    public void setJoinPersonNum(Integer joinPersonNum) {
        this.joinPersonNum = joinPersonNum;
    }

    public String getSocietyLOGO() {
        return societyLOGO;
    }

    public void setSocietyLOGO(String societyLOGO) {
        this.societyLOGO = societyLOGO;
    }

    public Integer getIsLog() {
        return isLog;
    }

    public void setIsLog(Integer isLog) {
        this.isLog = isLog;
    }

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

    public Integer getRecommendNo() {
        return recommendNo;
    }

    public void setRecommendNo(Integer recommendNo) {
        this.recommendNo = recommendNo;
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

    public Integer getSystempraiseNum() {
        return systempraiseNum;
    }

    public void setSystempraiseNum(Integer systempraiseNum) {
        this.systempraiseNum = systempraiseNum;
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

    public String getTagIDs() {
        return tagIDs;
    }

    public void setTagIDs(String tagIDs) {
        this.tagIDs = tagIDs;
    }

}
