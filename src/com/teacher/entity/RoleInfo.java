package com.teacher.entity;

import com.teacher.utils.DBUtil.*;

public class RoleInfo {
	@Id
    private Integer roleId;

	@Name("ROLE_NAME")
    private String roleName;

    
    
    
    public RoleInfo() {
		super();
	}

    
	public RoleInfo(Integer roleId) {
		super();
		this.roleId = roleId;
	}


	public RoleInfo(Integer roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

	@Override
	public String toString() {
		return "RoleInfoVO [roleId=" + roleId + ", roleName=" + roleName + "]";
	}
    
    
}