package com.sl.practice.web.model;

import com.sl.practice.base.constant.Const;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: liyapu
 * @description: 分页查询入参
 * @date 2020-02-26 23:28
 */
@Data
@Accessors(chain = true)
public class UserPageQuery {

    private Integer pageNum = Const.PAGE_NUM_DEFAULT;

    private Integer pageSize = Const.PAGE_SIZE_DEFAULT;

    private Integer status;

    private String orderBy;
}
