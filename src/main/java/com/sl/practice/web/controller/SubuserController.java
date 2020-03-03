package com.sl.practice.web.controller;

import com.sl.practice.base.web.WebResult;
import com.sl.practice.service.SubuserService;
import com.sl.practice.web.model.SubuserModel;
import com.sl.practice.web.vo.SubuserVo;
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
 * 子用户表 前端控制器
 * </p>
 *
 * @author lyp
 * @dateTime 2020-03-03 21:34:11
 * @desc 
 */
@RestController
@RequestMapping("/subuser")
public class SubuserController {

   @Autowired
   private SubuserService subuserService;

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
   public WebResult<SubuserVo> getById(Integer id){
      SubuserVo subuserVo = subuserService.getById(id);
      return WebResult.success(subuserVo);
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
   public WebResult<List<SubuserVo>> listByIds(@RequestParam("ids") List<Integer> ids){
      List<SubuserVo> subuserVoList = subuserService.listByIds(ids);
      return WebResult.success(subuserVoList);
   }


    @ApiOperation(value = "新增保存一条记录",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({
       @ApiResponse(code = WebResult.CODE_SUCCESS, message = "保存成功"),
    })
    @PostMapping("/save")
    public WebResult save(SubuserModel subuserModel){
       subuserService.save(subuserModel);
       return WebResult.successSave();
    }


     @ApiOperation(value = "编辑更新一条记录",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
     @ApiResponses({
         @ApiResponse(code = WebResult.CODE_SUCCESS, message = "更新成功"),
     })
     @PostMapping("/update")
     public WebResult update(SubuserModel subuserModel){
         subuserService.update(subuserModel);
         return WebResult.successUpdate();
     }


     @ApiOperation(value = "根据id删除一条记录",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
     @ApiResponses({
         @ApiResponse(code = WebResult.CODE_SUCCESS, message = "删除成功"),
     })
     @ApiImplicitParams(
     value = {
         @ApiImplicitParam(paramType = "query", name = "id", dataType = "int", required = true, value = "数据记录id",example = "1"),
     }
     )
     @PostMapping("/deleteById")
     public WebResult deleteById(Integer id){
         subuserService.deleteById(id);
         return WebResult.successDelete();
     }


     @ApiOperation(value = "根据id批量删除记录",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
     @ApiResponses({
         @ApiResponse(code = WebResult.CODE_SUCCESS, message = "删除成功"),
     })
     @ApiImplicitParams(
     value = {
         @ApiImplicitParam(paramType = "query", name = "ids", dataType = "int", required = true, value = "删除记录的id列表",example = "1"),
     }
     )
     @PostMapping("/deleteByIds")
     public WebResult deleteByIds(@RequestParam("ids") List<Integer> ids){
         subuserService.deleteByIds(ids);
         return WebResult.successDelete();
     }


}
