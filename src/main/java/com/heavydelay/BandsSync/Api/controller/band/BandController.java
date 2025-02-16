package com.heavydelay.BandsSync.Api.controller.band;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heavydelay.BandsSync.Api.service.band.IBand;

@RestController
@RequestMapping("/band")
public class BandController {

    @Autowired
    private IBand bandService;
}
