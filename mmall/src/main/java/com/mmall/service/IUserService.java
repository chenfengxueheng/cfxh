package com.mmall.service;

import com.mmall.commom.ServiceResponse;
import com.mmall.pojo.User;

public interface IUserService {

	ServiceResponse<User> login(String username, String password);
	ServiceResponse<String> register(User user);
	ServiceResponse<String> checkValid(String str,String type);
	ServiceResponse<String> selectQusetion(String username);
	ServiceResponse<String> forgetCheckAnswer(String username,String question,String answer);
	ServiceResponse<String> forgetResetPassword(String username,String passwordNew,String forgetToken);
    ServiceResponse<String>  resetPassword(String passwordOld,String passwordNew,User user);
}
