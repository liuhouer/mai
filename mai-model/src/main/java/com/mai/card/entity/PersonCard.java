package com.mai.card.entity;

import java.io.Serializable;

/**
 * PersonCard
 * 个人贺卡
 * @author Yao Jinwei
 * @date 2015/12/18
 */
public class PersonCard implements Serializable {

    private static final long serialVersionUID = 8105483242107970327L;
    // app内分享  微信朋友
    public static final String SHARE_TYPE_TO_WEIXIN="TOWEIXINPENGYOU";
    // app内分享  微信朋友圈
    public static final String SHARE_TYPE_TO_CIRCLE="TOWEIXINPENGYOUQUAN";
    // app内分享  QQ
    public static final String SHARE_TYPE_TO_QQ="TOQQ";
    // app内分享  新浪微博
    public static final String SHARE_TYPE_TO_SINA="TOSINA";
    // 微信内分享去向 微信朋友
    public static final String SHARE_TYPE_IN_WEIXIN="WEIXINPENGYOU";
    // 微信内分享去向 微信朋友圈
    public static final String SHARE_TYPE_IN_CIRCLE="WEIXINPENGYOUQUAN";

    public static final Integer PERSONCARD_ISDELETE_YES = 1;
    public static final Integer PERSONCARD_ISDELETE_NO = 0;
    private String personCardID;
    // 明星编号
    private String cardStarPhotoID;
    // 边框编号
    private String cardFrameID;
    // 原始照片URL
    private String originalPhotoURL;
    // 原始照片宽
    private Integer originalWidth;
    // 原始照片高
    private Integer originalHeight;
    // 合成照片URL
    private String finalPhotoURL;
    // 合成照片宽
    private Integer finalWidth;
    // 合成照片高
    private Integer finalHeight;
    // 原始音频URL
    private String originalAudioURL;
    // 合成音频URL
    private String finalAudioURL;
    // 制作人编号
    private String personID;
    // 制作人姓名
    private String personName;
    // 明星姓名
    private String cardStarName;
    // 制作人手机号
    private String phoneNum;

    private Long createTime;
    // 浏览数
    private Integer viewNum;
    // app分享至微信数
    private Integer wxShareNum;
    // app分享至朋友圈数
    private Integer circleShareNum;
    // app分享至QQ数
    private Integer qqShareNum;
    // app分享至微博数
    private Integer sinaShareNum;
    // 微信内分享计数
    private Integer shareNum;
    private Integer isDelete;
    // 微信内分享计数
    private Integer shareInWxNum;
    // 微信朋友圈分享计数
    private Integer shareInCircleNum;

    // 已抽奖次数
    private Integer lotteryNum;
    // 分享URL
    private String shareURL;
    // 分享title
    private String shareTitle;
    // 分享摘要
    private String shareDesc;
    // 分享小图
    private String sharePicURL;

    //用户分享总数，非数据库字段，游戏分享数用
    private Integer shareTotal;

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareDesc() {
        return shareDesc;
    }

    public void setShareDesc(String shareDesc) {
        this.shareDesc = shareDesc;
    }

    public String getSharePicURL() {
        return sharePicURL;
    }

    public void setSharePicURL(String sharePicURL) {
        this.sharePicURL = sharePicURL;
    }

    public Integer getLotteryNum() {
        return lotteryNum;
    }

    public void setLotteryNum(Integer lotteryNum) {
        this.lotteryNum = lotteryNum;
    }

    public String getShareURL() {
        return shareURL;
    }

    public void setShareURL(String shareURL) {
        this.shareURL = shareURL;
    }

    public String getPersonCardID() {
        return personCardID;
    }

    public void setPersonCardID(String personCardID) {
        this.personCardID = personCardID;
    }

    public String getCardStarPhotoID() {
        return cardStarPhotoID;
    }

    public void setCardStarPhotoID(String cardStarPhotoID) {
        this.cardStarPhotoID = cardStarPhotoID;
    }

    public String getCardFrameID() {
        return cardFrameID;
    }

    public void setCardFrameID(String cardFrameID) {
        this.cardFrameID = cardFrameID;
    }

    public String getOriginalPhotoURL() {
        return originalPhotoURL;
    }

    public void setOriginalPhotoURL(String originalPhotoURL) {
        this.originalPhotoURL = originalPhotoURL;
    }

    public Integer getOriginalWidth() {
        return originalWidth;
    }

    public void setOriginalWidth(Integer originalWidth) {
        this.originalWidth = originalWidth;
    }

    public Integer getOriginalHeight() {
        return originalHeight;
    }

    public void setOriginalHeight(Integer originalHeight) {
        this.originalHeight = originalHeight;
    }

    public String getFinalPhotoURL() {
        return finalPhotoURL;
    }

    public void setFinalPhotoURL(String finalPhotoURL) {
        this.finalPhotoURL = finalPhotoURL;
    }

    public Integer getFinalWidth() {
        return finalWidth;
    }

    public void setFinalWidth(Integer finalWidth) {
        this.finalWidth = finalWidth;
    }

    public Integer getFinalHeight() {
        return finalHeight;
    }

    public void setFinalHeight(Integer finalHeight) {
        this.finalHeight = finalHeight;
    }

    public String getOriginalAudioURL() {
        return originalAudioURL;
    }

    public void setOriginalAudioURL(String originalAudioURL) {
        this.originalAudioURL = originalAudioURL;
    }

    public String getFinalAudioURL() {
        return finalAudioURL;
    }

    public void setFinalAudioURL(String finalAudioURL) {
        this.finalAudioURL = finalAudioURL;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getCardStarName() {
        return cardStarName;
    }

    public void setCardStarName(String cardStarName) {
        this.cardStarName = cardStarName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    public Integer getWxShareNum() {
        return wxShareNum;
    }

    public void setWxShareNum(Integer wxShareNum) {
        this.wxShareNum = wxShareNum;
    }

    public Integer getCircleShareNum() {
        return circleShareNum;
    }

    public void setCircleShareNum(Integer circleShareNum) {
        this.circleShareNum = circleShareNum;
    }

    public Integer getQqShareNum() {
        return qqShareNum;
    }

    public void setQqShareNum(Integer qqShareNum) {
        this.qqShareNum = qqShareNum;
    }

    public Integer getSinaShareNum() {
        return sinaShareNum;
    }

    public void setSinaShareNum(Integer sinaShareNum) {
        this.sinaShareNum = sinaShareNum;
    }

    public Integer getShareNum() {
        return shareNum;
    }

    public void setShareNum(Integer shareNum) {
        this.shareNum = shareNum;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getShareInWxNum() {
        return shareInWxNum;
    }

    public void setShareInWxNum(Integer shareInWxNum) {
        this.shareInWxNum = shareInWxNum;
    }

    public Integer getShareInCircleNum() {
        return shareInCircleNum;
    }

    public void setShareInCircleNum(Integer shareInCircleNum) {
        this.shareInCircleNum = shareInCircleNum;
    }

    public Integer getShareTotal() {
        return shareTotal;
    }

    public void setShareTotal(Integer shareTotal) {
        this.shareTotal = shareTotal;
    }

}
