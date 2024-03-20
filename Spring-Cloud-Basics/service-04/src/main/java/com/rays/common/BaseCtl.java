package com.rays.common;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rays.dto.BaseDTO;

public abstract class BaseCtl<F extends BaseForm, T extends BaseDTO, S extends BaseServiceInt<T>> {

	@Autowired
	protected S baseService;

	@Value("${page.size}")
	private int pageSize = 0;

	public ORSResponse validate(BindingResult bindingResult) {
		ORSResponse res = new ORSResponse(true);

		if (bindingResult.hasErrors()) {
			res.setSuccess(false);

			Map<String, String> errors = new HashMap<String, String>();

			List<FieldError> list = bindingResult.getFieldErrors();
			list.forEach(e -> {
				errors.put(e.getField(), e.getDefaultMessage());
				System.out.println("Field :: " + e.getField() + "  Message :: " + e.getDefaultMessage());
			});
			res.addInputError(errors);
		}
		return res;

	}

	@GetMapping
	public ORSResponse get() {
		System.out.println("BaseCtl Get() method run");
		ORSResponse res = new ORSResponse(true);
		res.addData("I am okay " + this.getClass() + " --" + new Date());
		return res;
	}

	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable long id) {

		System.out.println("BaseCtl Get() method run.......Gopal");

		ORSResponse res = new ORSResponse(true);
		T dto = baseService.findById(id);

		if (dto != null) {
			res.addData(dto);
		} else {

			res.setSuccess(false);
			// res.addMessage("Record not found");
		}
		System.out.println("Edit response :" + res);
		return res;
	}

	@GetMapping("delete/{id}")
	public ORSResponse delete(@PathVariable long id) {
		System.out.println("BaseCtl Delete() method run........vipin");
		ORSResponse res = new ORSResponse(true);
		try {
			T dto = baseService.delete(id);
			res.addData(dto);
			System.out.println("Record Deleted Successfully");
		} catch (Exception e) {
			res.setSuccess(false);
			res.addMessage(e.getMessage());
		}
		return res;
	}

	@PostMapping("deleteMany/{ids}")
	public ORSResponse deleteMany(@PathVariable String[] ids, @RequestParam("pageNo") String pageNo,
			@RequestBody F form) {
		System.out.println("BaseCtl DeleteMany() method....Gopal... run");
		ORSResponse res = new ORSResponse(true);
		try {

			// System.out.println("deleteMany Page No is ******---" + pageNo);

			for (String id : ids) {
				System.out.println("Records To be Deleted :: " + id);
				baseService.delete(Long.parseLong(id));

			}
			T dto = (T) form.getDto();

			List<T> list = baseService.search(dto, Integer.parseInt(pageNo), pageSize);

			res.addData(baseService.search(dto, Integer.parseInt(pageNo), pageSize));
			res.setSuccess(true);
			res.addMessage("Records Deleted Successfully");
			System.out.println("Records Deleted Successfully by Gopal");

		} catch (Exception e) {
			res.setSuccess(false);
			res.addMessage(e.getMessage());
		}
		return res;
	}

	@RequestMapping(value = "/search/{pageNo}", method = { RequestMethod.GET, RequestMethod.POST })
	public ORSResponse search(@RequestBody F form, @PathVariable int pageNo) {

		System.out.println("BaseCtl Search method with pageNo :: " + pageNo + "   Page size is :: " + pageSize);

		pageNo = (pageNo < 0) ? 0 : pageNo;

		System.out.println("Operation :: " + form.getOperation());

		T dto = (T) form.getDto();

		ORSResponse res = new ORSResponse(true);

		res.addData(baseService.search(dto, pageNo, pageSize));

		List nextList = baseService.search(dto, pageNo + 1, pageSize);
		res.addResult("nextList", nextList.size());
		return res;
	}

}