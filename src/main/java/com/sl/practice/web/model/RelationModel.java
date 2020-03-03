package com.sl.practice.web.model;

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
public class RelationModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "用户名", position = 1)
    private String username;

    @ApiModelProperty(value = "是否是主账户 0:不是 1:是", position = 2)
    private Integer masterId;



}
