package com.sl.practice.web.controller;

import com.sl.practice.base.web.WebResult;
import com.sl.practice.service.RelationService;
import com.sl.practice.web.model.RelationModel;
import com.sl.practice.web.vo.RelationVo;
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
 * 主子关系表 前端控制器
 * </p>
 *
 * @author lyp
 * @dateTime 2020-03-03 21:34:11
 * @desc 
 */
@RestController
@RequestMapping("/relation")
public class RelationController {

   @Autowired
   private RelationService relationService;

   @ApiOperation(value = "根据id查询",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
   @ApiResponses({
      @ApiResponse(code = WebResult.CODE_SUCCESS, message = "查询成功"),
   })
   @ApiImplicitParams(
   value = {
       @ApiImplicitParam(paramType = "query", name = "id", dataType = "Long", required = true, value = "数据记录id",example = "1"),
   }
   )
   @PostMapping("/getById")
   public WebResult<RelationVo> getById(Integer id){
      RelationVo relationVo = relationService.getById(id);
      return WebResult.success(relationVo);
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
   public WebResult<List<RelationVo>> listByIds(@RequestParam("ids") List<Integer> ids){
      List<RelationVo> relationVoList = relationService.listByIds(ids);
      return WebResult.success(relationVoList);
   }


    @ApiOperation(value = "新增保存一条记录",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({
       @ApiResponse(code = WebResult.CODE_SUCCESS, message = "保存成功"),
    })
    @PostMapping("/save")
    public WebResult save(RelationModel relationModel){
       relationService.save(relationModel);
       return WebResult.successSave();
    }


     @ApiOperation(value = "编辑更新一条记录",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
     @ApiResponses({
         @ApiResponse(code = WebResult.CODE_SUCCESS, message = "更新成功"),
     })
     @PostMapping("/update")
     public WebResult update(RelationModel relationModel){
         relationService.update(relationModel);
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
         relationService.deleteById(id);
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
         relationService.deleteByIds(ids);
         return WebResult.successDelete();
     }


}
