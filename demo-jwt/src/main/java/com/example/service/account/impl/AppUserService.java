package com.example.service.account.impl;

import com.example.model.account.AppUser;
import com.example.repository.account.IAppUserRepository;
import com.example.service.account.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements IAppUserService {

    @Autowired
    private IAppUserRepository IAppUserRepository;

    @Override
    public AppUser findAppUserByUsername(String username) {
        return this.IAppUserRepository.getAppUserByUsername(username);
    }

    @Override
    public void save(AppUser appUser) {
        this.IAppUserRepository.save(appUser);
    }
}
