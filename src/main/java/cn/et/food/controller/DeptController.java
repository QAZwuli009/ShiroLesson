package cn.et.food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.et.food.entity.Emp;
import cn.et.food.entity.Result;
import cn.et.food.entity.TreeNode;
import cn.et.food.service.DeptService;
import cn.et.food.utils.PageTools;

@Controller
public class DeptController {

	@Autowired
	DeptService ds;

	/**
	 * 直接返回对象springMVC自动转换成json
	 * 
	 * @param sname
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/queryDept", method = RequestMethod.POST)
	public List<TreeNode> queryDept(Integer id) throws Exception {
		if (id == null) {
			id = 0;
		}
		List<TreeNode> queryTrreNode = ds.queryTrreNode(id);
		return queryTrreNode;
	}
	
	@ResponseBody
	@RequestMapping(value = "/queryEmp", method = RequestMethod.GET)
	public PageTools queryEmp(Integer id,String ename, Integer page, Integer rows) throws Exception {
		 PageTools queryEmp = ds.queryEmp(id,ename, page, rows);
		return queryEmp;
	}

	@ResponseBody
	@RequestMapping(value = "/Emp/{id}", method = RequestMethod.DELETE)
	public Result deleteStudent(@PathVariable String id) throws Exception {
		Result rs = new Result();
		rs.setCode(1);
		try {
			ds.deleteEmp(id);
		} catch (Exception e) {
			rs.setCode(0);
			rs.setMessage(e);
		}
		return rs;
	}

	@ResponseBody
	@RequestMapping(value = "/Emp/{id}", method = RequestMethod.PUT)
	public Result updateStudent(@PathVariable Integer id, Emp emp)
			throws Exception {
		emp.setId(id);
		Result rs = new Result();
		rs.setCode(1);
		try {
			ds.updateEmp(emp);
		} catch (Exception e) {
			rs.setCode(0);
			rs.setMessage(e);
		}
		return rs;
	}

	@ResponseBody
	@RequestMapping(value = "/Emp", method = RequestMethod.POST)
	public Result saveStudent(Emp emp) throws Exception {
		Result rs = new Result();
		rs.setCode(1);
		try {
			ds.saveEmp(emp);
		} catch (Exception e) {
			rs.setCode(0);
			rs.setMessage(e);
		}
		return rs;
	}
}
