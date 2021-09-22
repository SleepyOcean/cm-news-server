package com.netease.common.exception.user;

import com.netease.common.exception.base.BaseException;

/**
 * 用户信息异常类
 *
 * @author netease
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
