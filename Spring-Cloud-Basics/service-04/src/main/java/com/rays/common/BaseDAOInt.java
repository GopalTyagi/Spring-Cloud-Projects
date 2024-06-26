package com.rays.common;

import java.util.List;

import com.rays.dto.BaseDTO;

public interface BaseDAOInt<T extends BaseDTO> {

	public long add(T dto);

	public void update(T dto);

	public void delete(T dto);

	public T findByPK(long pk);

	public List search(T dto, int pageNo, int pageSize);

//	public List search(T dto);

}
