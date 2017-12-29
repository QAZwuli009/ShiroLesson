package cn.et.food.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.et.food.dao.StudentMapper;
import cn.et.food.entity.Student;
import cn.et.food.entity.StudentExample;
import cn.et.food.service.StudentService;
import cn.et.food.utils.PageTools;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	StudentMapper sm;

	public PageTools queryStudent(String sname, Integer page, Integer rows) {
		if (sname == null) {
			sname = "";
		}
		StudentExample se = new StudentExample();
		se.createCriteria().andSnameLike("%" + sname + "%");
		// 发起sql语句查询总记录数
		int total = queryStudentCount(se);
		// limit 开始位置，总记录数
		PageTools pts = new PageTools(page, total, rows);
		RowBounds rb = new RowBounds(pts.getStartIndex() - 1, rows);
		List<Student> studentList = sm.selectByExampleWithRowbounds(se, rb);
		pts.setRows(studentList);
		return pts;

	}

	public int queryStudentCount(StudentExample se) {
		int total = (int) sm.countByExample(se);
		return total;
	}
	
	public void deleteStudent(String sid){
		String[] s=sid.split("-");
		for(int i=0;i<s.length;i++){
			Integer newSid=Integer.parseInt(s[i]);
			sm.deleteByPrimaryKey(newSid);
		}
	}

	public void updateStudent(Student student) {
		student.setBirthdate(student.getBirthdate());
		sm.updateByPrimaryKey(student);
	}

	public void saveStudent(Student student) {
		sm.insertSelective(student);
	}


}
