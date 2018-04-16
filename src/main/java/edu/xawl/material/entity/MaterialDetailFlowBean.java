package edu.xawl.material.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import edu.xawl.common.entity.BaseBean;
import edu.xawl.material.enums.MaterialDetailOp;
import edu.xawl.us.entity.UserBean;
/**
 * @author Administrator
 *
 */
@Entity
@Table(name="LECMS_MATERIAL_DETAIL_FLOW")
public class MaterialDetailFlowBean extends BaseBean{
	
	private UserBean oper;
	
	private MaterialDetailBean opMaterial;
	
	private MaterialDetailOp op;
	
	private BorrowFlow borrowFlow;

	@ManyToOne(targetEntity=UserBean.class)
	@JoinColumn(name="OPER")
	public UserBean getOper() {
		return oper;
	}

	public void setOper(UserBean oper) {
		this.oper = oper;
	}

	@ManyToOne(targetEntity=MaterialDetailBean.class)
	@JoinColumn(name="OP_MATERIAL_DETAIL")
	public MaterialDetailBean getOpMaterial() {
		return opMaterial;
	}

	public void setOpMaterial(MaterialDetailBean opMaterial) {
		this.opMaterial = opMaterial;
	}

	@Enumerated
	@Column(name="OP")
	public MaterialDetailOp getOp() {
		return op;
	}

	public void setOp(MaterialDetailOp op) {
		this.op = op;
	}

	@ManyToOne(targetEntity=BorrowFlow.class)
	@JoinColumn(name="BORROW_FLOW")
	public BorrowFlow getBorrowFlow() {
		return borrowFlow;
	}

	public void setBorrowFlow(BorrowFlow borrowFlow) {
		this.borrowFlow = borrowFlow;
	}
	
	
}
