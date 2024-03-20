package com.rays.common;

import java.util.List;

import com.rays.dto.BaseDTO;

public interface BaseServiceInt<T extends BaseDTO> {

	public long add(T dto);

	public void update(T dto);

	public T delete(long id);

	public T findById(long id);

	public List search(T dto, int parseInt, int pageSize);
}
