package edu.xawl.material.enums;
/**
 * 此枚举类代表了具体实验室器材的状态
 * @author Administrator
 *
 */
public enum MaterialStatus {
	/**
	 * 正常，表示存在实验室
	 */
	NOMAL,
	/**
	 * 正在被使用，表示已经借出，未归还
	 */
	USED,
	/**
	 * 损坏状态，表示器材损坏
	 */
	BAD,  
	/**
	 * 删除状态，页面不可见
	 */
	DELETED,
	/**
	 * 已经申请正在审核状态
	 */
	REVIEW
}
