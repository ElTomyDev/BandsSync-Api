package com.heavydelay.BandsSync.Api.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heavydelay.BandsSync.Api.model.mapper.IUserEmailMapper;
import com.heavydelay.BandsSync.Api.repository.user.UserEmailRepository;

@Service
public class EmailService {

    @Autowired
    UserEmailRepository emailRepository;
    IUserEmailMapper emailMapper;


    


}
