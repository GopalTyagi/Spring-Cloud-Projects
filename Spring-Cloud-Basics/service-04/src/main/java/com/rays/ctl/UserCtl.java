package com.rays.ctl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.UserDTO;
import com.rays.form.UserForm;
import com.rays.service.UserServiceInt;

@RestController
@RequestMapping(value = "User")
public class UserCtl extends BaseCtl<UserForm, UserDTO, UserServiceInt> {

	@Autowired
	public UserServiceInt service;

	@PostMapping("Add")
	public ORSResponse add(@RequestBody @Valid UserForm form, BindingResult bindingResult) {

		ORSResponse res = validate(bindingResult);

		if (res.isSuccess() == false) {
			return res;
		}
		try {
			UserDTO dto = (UserDTO) form.getDto();
			long add = service.add(dto);
			res.addData(add);

		} catch (Exception e) {
			e.getMessage();
		}
		return res;
	}

	/*
	 * @GetMapping("delete/{id}") public ORSResponse delete(@PathVariable long id) {
	 * ORSResponse res = new ORSResponse(true);
	 * 
	 * try { baseService.delete(id); res.addMessage("Record Deleted Successfully");
	 * } catch (Exception e) { res.setSuccess(false);
	 * res.addMessage(e.getMessage()); } return res; }
	 * 
	 * @RequestMapping(value = "/search", method = { RequestMethod.GET,
	 * RequestMethod.POST }) public ORSResponse search(@RequestBody UserForm form) {
	 * 
	 * UserDTO dto = (UserDTO) form.getDto(); ORSResponse res = new
	 * ORSResponse(true); res.addData(baseService.search(dto)); return res; }
	 */
}
