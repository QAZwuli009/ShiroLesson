package cn.et.shiro.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Select;

import cn.et.shiro.entity.Menu;
import cn.et.shiro.entity.UserInfo;

public interface UserMapper {

	@Select("select user_name as userName,pass_word as password from user_info where user_name=#{0}")
	public UserInfo queryUser(String userName);

	@Select("select r.role_name from user_info u "
			+ "inner join user_role_relation urr on u.user_id=urr.user_id "
			+ "inner join role r on urr.role_id=r.role_id "
			+ "where user_name=#{0}")
	public Set<String> queryRoleByName(String userName);

	@Select("select p.perm_tag from user_info u "
			+ "inner join user_role_relation urr on u.user_id=urr.user_id "
			+ "inner join role r on urr.role_id=r.role_id "
			+ "inner join role_perms_relation rpr on r.role_id=rpr.role_id "
			+ "inner join perms p ON rpr.perm_id=p.perm_id "
			+ "where user_name=#{0}")
	public Set<String> queryPermsByName(String usernName);

	@Select("select menu_id as menuid,menu_name as menuname,menu_url as menuurl,menu_filter as menufilter,is_menu as ismenu from menu")
	public List<Menu> queryMenu();
	
	@Select("select menu_id as menuid,menu_name as menuname,menu_url as menuurl,menu_filter as menufilter,is_menu as ismenu from menu where menu_url=#{0}")
	public List<Menu> queryMenuByUrl(String url);

	@Select("SELECT menu_name as menuname,menu_url as menuurl,menu_filter as menufilter,is_menu as ismenu FROM menu m INNER JOIN user_menu_relation umr ON m.menu_id=umr.menu_id "
			+ "INNER JOIN user_info u ON umr.user_id=u.user_id "
			+ "WHERE user_name=#{0}")
	public List<Menu> queryMenuByUser(String userName);
	
}
