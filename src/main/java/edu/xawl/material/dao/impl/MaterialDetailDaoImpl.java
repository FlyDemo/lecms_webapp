package edu.xawl.material.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import edu.xawl.common.dao.BaseDao;
import edu.xawl.material.dao.MaterialDetailDao;
@Repository
public class MaterialDetailDaoImpl implements MaterialDetailDao {

	@Resource
	private BaseDao baseDao;
}
