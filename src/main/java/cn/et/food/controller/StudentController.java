package cn.et.food.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.et.food.entity.Result;
import cn.et.food.entity.Student;
import cn.et.food.service.StudentService;
import cn.et.food.utils.PageTools;

@Controller
public class StudentController {

	@Autowired
	StudentService ss;
	
	/**
	 * 直接返回对象springMVC自动转换成json
	 * 
	 * @param sname
	 * @return
	 * @throws Exception
	 */
	
	@ResponseBody
	@RequestMapping(value = "/queryStudent", method = RequestMethod.GET)
	public PageTools queryStudent(String sname, Integer page, Integer rows)
			throws Exception {
		PageTools queryStudent = ss.queryStudent(sname, page, rows);
		return queryStudent;
	}

	@ResponseBody
	@RequestMapping(value = "/student/{sid}", method = RequestMethod.DELETE)
	public Result deleteStudent(@PathVariable String sid) throws Exception {
		Result rs = new Result();
		rs.setCode(1);
		try {
			ss.deleteStudent(sid);
		} catch (Exception e) {
			rs.setCode(0);
			rs.setMessage(e);
		}
		return rs;
	}

	@ResponseBody
	@RequestMapping(value = "/student/{sid}", method = RequestMethod.POST)
	public Result updateStudent(@PathVariable Integer sid, Student student,MultipartFile myImage)
			throws Exception {
		
		String fileName=myImage.getOriginalFilename();
		File destFile=new File("F:\\images\\"+fileName);
		myImage.transferTo(destFile);
		
		student.setSid(sid);
		Result rs = new Result();
		rs.setCode(1);
		try {
			student.setImage(fileName);
			ss.updateStudent(student);
		} catch (Exception e) {
			rs.setCode(0);
			rs.setMessage(e);
		}
		return rs;
	}

	@ResponseBody
	@RequestMapping(value = "/student", method = RequestMethod.POST)
	public Result saveStudent(Student student,MultipartFile myImage) throws Exception {
		Result rs = new Result();
		rs.setCode(1);
		String fileName=myImage.getOriginalFilename();
		File destFile=new File("F:\\images\\"+fileName);
		myImage.transferTo(destFile);
		try {
			student.setImage(fileName);
			ss.saveStudent(student);
		} catch (Exception e) {
			rs.setCode(0);
			rs.setMessage(e);
		}
		return rs;
	}
}
