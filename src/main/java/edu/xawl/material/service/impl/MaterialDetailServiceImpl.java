package edu.xawl.material.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.xawl.common.service.CommonService;
import edu.xawl.material.dao.MaterialDetailDao;
import edu.xawl.material.service.MaterialDetailService;
@Service
public class MaterialDetailServiceImpl implements MaterialDetailService{

	@Resource
	private CommonService commonService;
	
	@Resource
	private MaterialDetailDao materialDetailDao;
}
