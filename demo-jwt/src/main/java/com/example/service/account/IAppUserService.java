package com.example.service.account;


import com.example.model.account.AppUser;

public interface IAppUserService {

    AppUser findAppUserByUsername(String username);

    void save(AppUser appUser);
}
