package com.mmall.controller.portal;


import javax.servlet.http.HttpSession;





import com.mmall.commom.ServiceResponse;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.mmall.commom.Const.CURRENT_USER;

@Controller
public class UserController {

	//public static final String CURRENT_USER="currentUser";
	//@RequestMapping("login.do")
	public String Login(){
		return "user-login/login";
	}
	@Autowired
	private IUserService iUserService;
	
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "login.do",method = RequestMethod.POST)
	@ResponseBody
	public  ServiceResponse<User> login(String username, String password, HttpSession session){
		ServiceResponse response=iUserService.login(username, password);
		if (response.isSuccess()) {
			session.setAttribute(CURRENT_USER, response.getDate());
		}
		return response;
	}
	//退出
	@RequestMapping(value = "logout.do",method = RequestMethod.GET)
    @ResponseBody
	public ServiceResponse<String> logout(HttpSession session) {
		session.removeAttribute(CURRENT_USER);
		return ServiceResponse.creatBySuccess();
	}
	//
	@RequestMapping(value = "register.do",method = RequestMethod.GET)
    @ResponseBody
	public ServiceResponse<String> register(User user){
		return iUserService.register(user);
	}
	
	@RequestMapping(value = "checkValid.do",method = RequestMethod.GET)
    @ResponseBody
	public ServiceResponse<String> checkValid(String str,String type){
		return iUserService.checkValid(str,type);
	}
	@RequestMapping(value = "getUserInfo.do",method = RequestMethod.GET)
    @ResponseBody
	public ServiceResponse<User> getUserInfo(HttpSession session){
		User user = (User) session.getAttribute(CURRENT_USER);
        if(user != null){
            return ServiceResponse.creatBysuccessMessage(user);
        }
        return ServiceResponse.creatByErrorMessage("用户未登陆！");
	}
	
	@RequestMapping(value = "getQuestion.do",method = RequestMethod.GET)
    @ResponseBody
    public ServiceResponse<String> forgetGetQuestion(String username){
        return iUserService.selectQusetion(username);
    }
	@RequestMapping(value = "checkAnswer.do",method = RequestMethod.GET)
    @ResponseBody
    public ServiceResponse<String> forgetCheckAnswer(String username,String question,String answer){
        return iUserService.forgetCheckAnswer(username,question,answer);
    }
	@RequestMapping(value = "forgetPassword.do",method = RequestMethod.GET)
    @ResponseBody
    public ServiceResponse<String> forgetResetPassword(String username,String passwordNew,String forgetToken){
        return iUserService.forgetResetPassword(username,passwordNew,forgetToken);
    }
    
    @RequestMapping(value = "resetPassword.do",method = RequestMethod.GET)
    @ResponseBody
    public ServiceResponse<String> ResetPassword(HttpSession session,String passwordOld,String passwordNew){
        User user = (User)session.getAttribute(CURRENT_USER);
        if(user == null){
            return ServiceResponse.creatByErrorMessage("未登录，请登录！");
        }
        else{
            return iUserService.resetPassword(passwordOld,passwordNew,user);
        }
    }

}
