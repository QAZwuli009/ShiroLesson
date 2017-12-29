package cn.et.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.et.shiro.dao.UserMapper;

@Controller
public class SpringLoginController {
	@Autowired
	UserMapper userMapper;

	@RequestMapping("/loginShiro")
	public String login(String userName,String password,Model model){
		Subject currentUser = SecurityUtils.getSubject();
		
		UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
		try {
			currentUser.login(token);
			
			//≤Àœµ≤Àµ•
			model.addAttribute("menuList",userMapper.queryMenuByUser(userName));
			return "/layout.jsp";
		} catch ( UnknownAccountException uae ) {
			System.out.println("’À∫≈¥ÌŒÛ");
		} catch ( IncorrectCredentialsException ice ) {
			System.out.println("√‹¬Î≤ª∆•≈‰");
		} catch ( LockedAccountException lae ) {
			System.out.println("’À∫≈±ªÀ¯∂®");
		} catch ( AuthenticationException ae ) {
			System.out.println("Œ¥÷™“Ï≥£");
		}
		return "/error.jsp";
	}
}
