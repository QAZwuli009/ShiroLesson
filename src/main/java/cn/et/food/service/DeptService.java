package cn.et.food.service;

import java.util.List;

import cn.et.food.entity.Emp;
import cn.et.food.entity.TreeNode;
import cn.et.food.utils.PageTools;

public interface DeptService {

	public abstract List<TreeNode> queryTrreNode(Integer pid);

	public PageTools queryEmp(Integer id,String ename,Integer page,Integer rows);

	public void deleteEmp(String id);

	public void updateEmp(Emp emp);

	public void saveEmp(Emp emp);

}