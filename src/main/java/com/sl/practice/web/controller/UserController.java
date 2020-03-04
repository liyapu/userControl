package com.sl.practice.web.controller;

import com.sl.practice.base.web.PageInfo;
import com.sl.practice.base.web.WebResult;
import com.sl.practice.service.UserService;
import com.sl.practice.web.model.UserModel;
import com.sl.practice.web.model.UserPageQuery;
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
 * 用户主表 前端控制器
 * </p>
 *
 * @author lyp
 * @dateTime 2020-03-03 21:34:11
 * @desc 
 */
@RestController
@RequestMapping("/user")
public class UserController {

   @Autowired
   private UserService userService;

   @ApiOperation(value = "根据id查询",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
   @ApiResponses({
      @ApiResponse(code = WebResult.CODE_SUCCESS, message = "查询成功"),
   })
   @ApiImplicitParams(
   value = {
       @ApiImplicitParam(paramType = "query", name = "id", dataType = "int", required = true, value = "数据记录id",example = "1"),
   }
   )
   @PostMapping("/getById")
   public WebResult<UserVo> getById(Integer id){
      UserVo userVo = userService.getById(id);
      return WebResult.success(userVo);
   }


   @ApiOperation(value = "根据多个id 批量查询",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
   @ApiResponses({
       @ApiResponse(code = WebResult.CODE_SUCCESS, message = "查询成功"),
   })
   @ApiImplicitParams(
   value = {
       @ApiImplicitParam(paramType = "query", name = "ids", dataType = "int", required = true, value = "查询记录的id列表",example = "1"),
   }
   )
   @PostMapping("/listByIds")
   public WebResult<List<UserVo>> listByIds(@RequestParam("ids") List<Integer> ids){
      List<UserVo> userVoList = userService.listByIds(ids);
      return WebResult.success(userVoList);
   }

    @ApiOperation(value = "根据多个条件 分页查询", notes = "返回复杂的分页参数对象", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({
            @ApiResponse(code = WebResult.CODE_SUCCESS, message = "查询成功"),
    })
    @ApiImplicitParam(name = "userPageQuery", value = "分页查询条件参数", required = true, dataType = "UserPageQuery")
    @PostMapping("/getByPage")
    public WebResult<PageInfo<UserVo>> getByPage(UserPageQuery userPageQuery){
        PageInfo<UserVo> userVoPageInfo = userService.getByPage(userPageQuery);
        return WebResult.success(userVoPageInfo);
    }


    @ApiOperation(value = "新增保存一条记录",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({
       @ApiResponse(code = WebResult.CODE_SUCCESS, message = "保存成功"),
    })
    @PostMapping("/save")
    public WebResult save(UserModel userModel){
       userService.save(userModel);
       return WebResult.successSave();
    }


     @ApiOperation(value = "编辑更新一条记录",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
     @ApiResponses({
         @ApiResponse(code = WebResult.CODE_SUCCESS, message = "更新成功"),
     })
     @PostMapping("/update")
     public WebResult update(UserModel userModel){
         userService.update(userModel);
         return WebResult.successUpdate();
     }


//     @ApiOperation(value = "根据id删除一条记录",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//     @ApiResponses({
//         @ApiResponse(code = WebResult.CODE_SUCCESS, message = "删除成功"),
//     })
//     @ApiImplicitParams(
//     value = {
//         @ApiImplicitParam(paramType = "query", name = "id", dataType = "int", required = true, value = "数据记录id",example = "1"),
//     }
//     )
//     @PostMapping("/deleteById")
//     public WebResult deleteById(Integer id){
//         userService.deleteById(id);
//         return WebResult.successDelete();
//     }


//     @ApiOperation(value = "根据id批量删除记录",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//     @ApiResponses({
//         @ApiResponse(code = WebResult.CODE_SUCCESS, message = "删除成功"),
//     })
//     @ApiImplicitParams(
//     value = {
//         @ApiImplicitParam(paramType = "query", name = "ids", dataType = "int", required = true, value = "删除记录的id列表",example = "1"),
//     }
//     )
//     @PostMapping("/deleteByIds")
//     public WebResult deleteByIds(@RequestParam("ids") List<Integer> ids){
//         userService.deleteByIds(ids);
//         return WebResult.successDelete();
//     }


    @ApiOperation(value = "根据用户名删除一条记录",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({
            @ApiResponse(code = WebResult.CODE_SUCCESS, message = "删除成功"),
    })
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(paramType = "query", name = "username", dataType = "string", required = true, value = "用户名称",example = "张三"),
            }
    )
    @PostMapping("/deleteByUsername")
    public WebResult deleteByUsername(String username){
        userService.deleteByUsername(username);
        return WebResult.successDelete();
    }


}
