package edu.xawl.material.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import edu.xawl.common.entity.BaseBean;
import edu.xawl.material.enums.BorrowFlowStatus;
import edu.xawl.us.entity.UserBean;
/**
 * borrow flow table
 * who borrow 、 material 、 materialDetail 、who review 、  material status 、 borrow content 、review content 、current people、 flow status
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
@Entity
@Table(name="LECMS_BORROW_FLOW")
public class BorrowFlow extends BaseBean{
	
	private UserBean borrower;
	
	private UserBean reviewer;
	
	private String borrowContent;
	
	private String reviewContent;
	
	private UserBean currentPeople;
	
	private BorrowFlowStatus borrowStatus;

	private MaterialBean material;
	
	private Integer num;
	
	private boolean overTime;
	
	/**
	 * 表示应该归还时间
	 */
	private Date goBackTime;
	
	private List<MaterialDetailFlowBean> details = new ArrayList<MaterialDetailFlowBean>();
	
	@ManyToOne(targetEntity=UserBean.class)
	@JoinColumn(name="BORROWER")
	public UserBean getBorrower() {
		return borrower;
	}

	public void setBorrower(UserBean borrower) {
		this.borrower = borrower;
	}

	@ManyToOne(targetEntity=UserBean.class)
	@JoinColumn(name="REVIEWER")
	public UserBean getReviewer() {
		return reviewer;
	}

	public void setReviewer(UserBean reviewer) {
		this.reviewer = reviewer;
	}

	@Column(name="BORROW_CONTENT")
	public String getBorrowContent() {
		return borrowContent;
	}

	public void setBorrowContent(String borrowContent) {
		this.borrowContent = borrowContent;
	}

	@Column(name="REVIEW_CONTENT")
	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	@ManyToOne(targetEntity=UserBean.class)
	@JoinColumn(name="CURRENT_PEOPLE")
	public UserBean getCurrentPeople() {
		return currentPeople;
	}

	public void setCurrentPeople(UserBean currentPeople) {
		this.currentPeople = currentPeople;
	}

	@Enumerated
	@Column(name="BORROW_STATUS")
	public BorrowFlowStatus getBorrowStatus() {
		return borrowStatus;
	}

	public void setBorrowStatus(BorrowFlowStatus borrowStatus) {
		this.borrowStatus = borrowStatus;
	}

	@ManyToOne(targetEntity=MaterialBean.class)
	@JoinColumn(name="MATERIAL")
	public MaterialBean getMaterial() {
		return material;
	}

	public void setMaterial(MaterialBean material) {
		this.material = material;
	}

	@Column(name="NUM")
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Column(name="OVER_TIME")
	public boolean getOverTime() {
		return overTime;
	}

	public void setOverTime(boolean overTime) {
		this.overTime = overTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="GO_BACK_TIME")
	public Date getGoBackTime() {
		return goBackTime;
	}

	public void setGoBackTime(Date goBackTime) {
		this.goBackTime = goBackTime;
	}

	@Transient
	public List<MaterialDetailFlowBean> getDetails() {
		return details;
	}

	public void setDetails(List<MaterialDetailFlowBean> details) {
		this.details = details;
	}
}
