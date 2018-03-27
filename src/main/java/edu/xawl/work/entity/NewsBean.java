package edu.xawl.work.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import edu.xawl.common.entity.BaseBean;
import edu.xawl.us.entity.UserBean;

/**
 * 最新消息
 * @author Administrator
 *
 */
@Entity
@Table(name="LECMS_NEWS")
public class NewsBean extends BaseBean{
	
	/**
	 * 最新消息标题
	 */
	private String newsTitle;
	
	/**
	 * 消息内容
	 */
	private String newsContent;
	
	/**
	 * 消息时间
	 */
	private Date newsTime;
	
	/**
	 * 是否删除
	 */
	private Boolean deleted;
	
	/**
	 * 是否置顶显示
	 */
	private Boolean topShow;
	
	/**
	 * 创建人
	 */
	private UserBean  creator = new UserBean();
 
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	@Column(name="NEWS_TITLE")
	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	@Column(name="NEWS_CONTENT")
	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	@Column(name="DELETED")
	public Boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="NEWS_TIME")
	public Date getNewsTime() {
		return newsTime;
	}

	public void setNewsTime(Date newsTime) {
		this.newsTime = newsTime;
	}

	
	@Column(name="TOP_SHOW")
	public Boolean getTopShow() {
		return topShow;
	}

	public void setTopShow(boolean topShow) {
		this.topShow = topShow;
	}

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=UserBean.class)
	@JoinColumn(name="CREATOR_ID")
	public UserBean getCreator() {
		return creator;
	}

	public void setCreator(UserBean creator) {
		this.creator = creator;
	}
}
