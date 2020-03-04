package com.sl.practice.web.controller;

import com.sl.practice.base.web.WebResult;
import com.sl.practice.service.UserService;
import com.sl.practice.web.model.LoginModel;
import com.sl.practice.web.model.UserModel;
import com.sl.practice.web.vo.UserVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 * 登录 前端控制器
 * </p>
 *
 * @author lyp
 * @dateTime 2020-03-03 21:34:11
 * @desc 
 */
@RestController
@RequestMapping("/login")
public class LoginController {

   @Autowired
   private UserService userService;

   @ApiOperation(value = "登录",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
   @ApiResponses({
      @ApiResponse(code = WebResult.CODE_SUCCESS, message = "登录成功"),
   })
   @ApiImplicitParam(name = "loginModel", value = "登录参数", required = true, dataType = "LoginModel")

   @PostMapping("/login")
   public WebResult login(LoginModel loginModel){
      userService.login(loginModel);
      return WebResult.successLogin();
   }


   @ApiOperation(value = "退出登录",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
   @ApiResponses({
       @ApiResponse(code = WebResult.CODE_SUCCESS, message = "退出成功"),
   })
   @ApiImplicitParams(
   value = {
       @ApiImplicitParam(paramType = "query", name = "username", dataType = "string", required = true, value = "用户名称"),
   }
   )
   @PostMapping("/logout")
   public WebResult logout(String username){
       userService.logout(username);
      return WebResult.successLogout();
   }



}
