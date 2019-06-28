package com.dayon.b2b2c.web.manage.view;

import java.util.List;

import com.dayon.b2b2c.api.auth.entity.AuthPower;
import com.dayon.b2b2c.api.auth.entity.AuthRole;
import com.dayon.b2b2c.api.user.entity.UserAdmin;

public class LoginView {
	private UserAdmin userAdmin;
	private List<AuthRole> authRoles;
	private List<AuthPower> authPowers;

	public UserAdmin getUserAdmin() {
		return userAdmin;
	}

	public void setUserAdmin(UserAdmin userAdmin) {
		this.userAdmin = userAdmin;
	}

	public List<AuthRole> getAuthRoles() {
		return authRoles;
	}

	public void setAuthRoles(List<AuthRole> authRoles) {
		this.authRoles = authRoles;
	}

	public List<AuthPower> getAuthPowers() {
		return authPowers;
	}

	public void setAuthPowers(List<AuthPower> authPowers) {
		this.authPowers = authPowers;
	}

}
