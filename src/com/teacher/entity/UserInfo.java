package com.teacher.entity;

import com.teacher.utils.DBUtil.Id;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author J.L.Zhou
 *
 */
public class UserInfo {
	@Id
    private String userName;

    private Integer roleId;

    private String userPwd;

    private String userRealName;

    private Date userBirthdate;

    private Boolean userSex;

    private String userTel;

    private String userQq;

    private String userIdcard;

    private String userAddress;

    private Boolean userLocked;


    private Date userRegisterTime;

    private String userImg;

    private String userLastIp;
    

    private Date userLastTime;

    private String userLastId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName == null ? null : userRealName.trim();
    }

    public Date getUserBirthdate() {
        return userBirthdate;
    }

    public void setUserBirthdate(Date userBirthdate) {
        this.userBirthdate = userBirthdate;
    }

    public Boolean getUserSex() {
        return userSex;
    }

    public void setUserSex(Boolean userSex) {
        this.userSex = userSex;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel == null ? null : userTel.trim();
    }

    public String getUserQq() {
        return userQq;
    }

    public void setUserQq(String userQq) {
        this.userQq = userQq == null ? null : userQq.trim();
    }

    public String getUserIdcard() {
        return userIdcard;
    }

    public void setUserIdcard(String userIdcard) {
        this.userIdcard = userIdcard == null ? null : userIdcard.trim();
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }

    public Boolean getUserLocked() {
        return userLocked;
    }

    public void setUserLocked(Boolean userLocked) {
        this.userLocked = userLocked;
    }

    public Date getUserRegisterTime() {
        return userRegisterTime;
    }

    public void setUserRegisterTime(Date userRegisterTime) {
        this.userRegisterTime = userRegisterTime;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg == null ? null : userImg.trim();
    }

    public String getUserLastIp() {
        return userLastIp;
    }

    public void setUserLastIp(String userLastIp) {
        this.userLastIp = userLastIp == null ? null : userLastIp.trim();
    }

    public Date getUserLastTime() {
        return userLastTime;
    }

    public void setUserLastTime(Date userLastTime) {
        this.userLastTime = userLastTime;
    }

    public String getUserLastId() {
        return userLastId;
    }

    public void setUserLastId(String userLastId) {
        this.userLastId = userLastId == null ? null : userLastId.trim();
    }

	@Override
	public String toString() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return "UserInfoVO [userName=" + userName + ", roleId=" + roleId + ", userPwd=" + userPwd + ", userRealName="
				+ userRealName + ", userBirthdate=" + userBirthdate + ", userSex=" + userSex + ", userTel=" + userTel
				+ ", userQq=" + userQq + ", userIdcard=" + userIdcard + ", userAddress=" + userAddress + ", userLocked="
				+ userLocked + ", userRegisterTime=" + userRegisterTime + ", userImg=" + userImg + ", userLastIp="
				+ userLastIp + ", userLastTime=" + userLastTime + ", userLastId=" + userLastId + "]";
	}
    
    
}