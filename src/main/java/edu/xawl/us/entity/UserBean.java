package edu.xawl.us.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import edu.xawl.common.entity.BaseBean;
import edu.xawl.us.enums.UserLeval;
@Entity
@Table(name="LECMS_USER_BASE")
public class UserBean extends BaseBean{

	private static final long serialVersionUID = 6043736454119081692L;
	
	private String name;
	private String loginName;
	private String passWord;
	private String mail;
	private UserLeval leval;
	private boolean deleted;

	@Column(name="NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="LOGIN_NAME")
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	@Column(name="PASSWORD")
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	@Column(name="MAIL")
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	@Enumerated
	@Column(name="LEVAL")
	public UserLeval getLeval() {
		return leval;
	}
	public void setLeval(UserLeval leval) {
		this.leval = leval;
	}
	
	@Column(name="DELETED")
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
	
}
