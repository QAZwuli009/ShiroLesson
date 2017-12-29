package cn.et.conf;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.et.shiro.dao.UserMapper;
import cn.et.shiro.entity.UserInfo;

@Component
public class MyDbRealm extends AuthorizingRealm{
	
	@Autowired
	UserMapper userMapper;
	
	/**
	 * ��ȡ��ǰ�û�����Ȩ����
	 * ����ǰ�û������ݿ�Ľ�ɫ��Ȩ��  ���ص�AuthorizationInfo
	 * Ĭ��  �ڽ�����Ȩ��֤�ǵ���
	 *   ���Ȩ�޵���  checkRole   checkPerm
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("��ȡȨ����Ϣ");
		String userName=principals.getPrimaryPrincipal().toString();
		Set<String> roleList=userMapper.queryRoleByName(userName);
		Set<String> permsList=userMapper.queryRoleByName(userName);
		//��ɫ��Ȩ�޼��϶���
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
		
		authorizationInfo.setRoles(roleList);
		authorizationInfo.setStringPermissions(permsList);
		return authorizationInfo;
	}
	
	/**
	 * ��֤
	 * ����¼������û�������������ݿ��е��û���������Ա�   �Ƿ����
	 * ����ֵ null��ʾ��֤ʧ��   ��null��֤ͨ��
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//ҳ�洫���token
		UsernamePasswordToken upt=(UsernamePasswordToken)token;
		UserInfo queryUser = userMapper.queryUser(upt.getPrincipal().toString());
		if(queryUser!=null && queryUser.getPassword().equals(new String(upt.getPassword()))){
			SimpleAuthenticationInfo sa=new SimpleAuthenticationInfo(upt.getUsername(),upt.getPassword(),"MyDbRealm");
			System.out.println("�ɹ�");
			return sa;
		}
		System.out.println("ʧ��");
		return null;
	}

}
