package com.rays.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dto.BaseDTO;

public abstract class BaseServiceImpl<T extends BaseDTO, D extends BaseDAOInt<T>> {

	@Autowired
	protected D baseDao;

	@Transactional(readOnly = false)
	public long add(T dto) throws Exception {
		long pk = baseDao.add(dto);
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(T dto) throws Exception {
		baseDao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(T dto) throws Exception  {
		baseDao.delete(dto);
	}
	
	@Transactional(readOnly = true)
	public List<T> search(T dto, int pageNo, int pageSize) {
		System.out.println("Search run in Baseservice......gopal");
		return baseDao.search(dto, pageNo, pageSize);
	}
	
	@Transactional(readOnly = true)
	public T findById(long id) {
		T dto = baseDao.findByPK(id);
		// T dto baseDao.findByPK(Class<T>, pk)
		return dto;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public T delete(long id) throws Exception {
		//log.debug("Base Service delete Start");
		T dto = findById(id);
		if (dto == null) {
			throw new Exception("Record not found");
		}
		baseDao.delete(dto);
	//	log.debug("Base Service delete End");
		return dto;
	}

	

}