package com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.util.result.JsonResult;
import com.ciicsh.gto.basicdataservice.api.CountryServiceProxy;
import com.ciicsh.gto.basicdataservice.api.dto.CountryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 11:18 2018/2/13
 */
@RestController
@RequestMapping("/api/baseData")
public class BaseDataApiController {

//    @Autowired
//    private CountryServiceProxy countryServiceProxy;
//
//    @GetMapping("/getCountry")
//    public JsonResult getCountry(){
//        List<CountryDTO> countryDTOS = countryServiceProxy.listAll();
//        return JsonResult.success(countryDTOS);
//    }
}
