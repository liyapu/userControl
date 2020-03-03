package com.sl.practice.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 主子关系表
 * </p>
 *
 * @author lyp
 * @dateTime 2020-03-03 21:34:11
 * @desc 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Relation对象", description="主子关系表")
public class RelationVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

            @ApiModelProperty(value = "用户名", position = 1)
    private String username;

            @ApiModelProperty(value = "是否是主账号  0是，1", position = 2)
    private Integer master;




}
