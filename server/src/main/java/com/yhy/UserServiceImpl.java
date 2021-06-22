package com.yhy;

public class UserServiceImpl implements IUserService{
    public UserServiceImpl() {
    }

    @Override
    public String getNameById(Integer uid) {
        return uid+"张三";
    }
}
