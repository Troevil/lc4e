package com.jcos.lc4e.core.web.actioncontroller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jcos.lc4e.core.database.model.User;
import com.jcos.lc4e.core.entity.Article;
import com.jcos.lc4e.core.entity.Message;
import com.jcos.lc4e.core.entity.Popup;
import com.jcos.lc4e.core.service.UserService;
import com.jcos.lc4e.core.util.annotation.ValidateField;
import com.jcos.lc4e.core.util.annotation.ValidateGroup;
import com.jcos.lc4e.core.util.credentials.PassDisposer;

@Controller
@RequestMapping(value = "/Member")
public class MemberController {

	@Inject
	private UserService userService;
	@Inject
	private PassDisposer passDisposer;

	@RequestMapping(value = "/SignUp", method = RequestMethod.GET)
	@ResponseBody
	@ValidateGroup(fields = { @ValidateField(index = 0, NotNull = true, minLen = 4, maxLen = 15), @ValidateField(index = 1, NotNull = true, minLen = 6) })
	public Message signUp(String username, String password, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = new User(username, password);
		passDisposer.encryptPassword(user);
		if (userService.createUser(user)) {
			return new Message(true, "Register Success");
		} else {
			return new Message("Register failed");
		}
	}

	@RequestMapping(value = "/SignIn", method = RequestMethod.GET)
	@ResponseBody
	@ValidateGroup(fields = { @ValidateField(index = 0, NotNull = true, minLen = 4, maxLen = 15), @ValidateField(index = 1, NotNull = true, minLen = 6) })
	public Message SignIn(String username, String password, HttpServletRequest request, HttpServletResponse response, Model model) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(true);
		subject.login(token);
		if (subject.isAuthenticated()) {
			return new Message(true, "Login Success");
		} else {
			return new Message("Login failed");
		}
	}

	@RequestMapping(value = "/GetArticles", method = RequestMethod.POST)
	@ResponseBody
	@ValidateGroup(fields = { @ValidateField(index = 0, NotNull = false, defaultInt = 1, minLen = 4, maxLen = 15), @ValidateField(index = 1, NotNull = false, defaultInt = 10, minLen = 6) })
	public List<Article> getTestArticle(Integer page, Integer size, HttpServletRequest request, HttpServletResponse response, Model model) {
		String[] cate = new String[] { "Java", "Obj-C", "C", "C++", "IOS", "Android" };
		String[] users = new String[] { "Admin", "Test", "Myas", "Liakx", "Google", "vsss" };

		List<Article> list = new ArrayList<Article>();
		for (int i = 0; i < size; i++) {
			list.add(new Article("/images/wireframe/image.png", new Popup("Matt", "Matt has been a member since July 2014"), "The friction between your thoughts and your code", cate[new Random().nextInt(5)], users[new Random().nextInt(5)], new Random().nextInt(100), formatdata(randomDate(
					"2015-03-07 11:00:00", "2015-05-07 12:12:12")), users[new Random().nextInt(5)]));
		}
		return list;
	}

	private static String formatdata(Date date) {
		Date now = new Date();
		long l = now.getTime() - date.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		StringBuffer sb = new StringBuffer();
		if (day > 0)
			return sb.append(day).append("天前").toString();
		if (hour > 0)
			return sb.append(hour).append("小时前").toString();
		if (min > 0)
			return sb.append(min).append("分前").toString();
		else
			return sb.append(s).append("秒 前").toString();
	}

	private static Date randomDate(String beginDate, String endDate) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date start = format.parse(beginDate);
			Date end = format.parse(endDate);
			if (start.getTime() >= end.getTime()) {
				return null;
			}
			long date = random(start.getTime(), end.getTime());

			return new Date(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static long random(long begin, long end) {
		long rtnn = begin + (long) (Math.random() * (end - begin));
		if (rtnn == begin || rtnn == end) {
			return random(begin, end);
		}
		return rtnn;
	}

}
