package com.zyj.controller;

import com.zyj.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * @Author: ZhangYajie
 * @Description:
 * @Date: Created in 14:22 2018/6/25
 */
@Controller
public class IndexController {

    @Autowired
    private IResourceService resourceService;


    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView model = new ModelAndView("admin/index");
        model.addObject("name","zhangsan");
        model.addObject("menuList",resourceService.selectList());
        pushSystemStatus(model);
        return model;
    }

    private void pushSystemStatus(ModelAndView model) {
        float freeMemory = (float) Runtime.getRuntime().freeMemory();
        float totalMemory = (float) Runtime.getRuntime().totalMemory();
        float usedMemory = (totalMemory - freeMemory);
        float memPercent = Math.round(freeMemory / totalMemory * 100) ;
        String os = System.getProperty("os.name");
        String javaVersion = System.getProperty("java.version");



        model.addObject("freeMemory", freeMemory);
        model.addObject("totalMemory", aFloat(totalMemory / 1024 / 1024));
        model.addObject("usedMemory", aFloat(usedMemory / 1024 / 1024));
        model.addObject("memPercent", aFloat(memPercent));
        model.addObject("os", os);
        model.addObject("javaVersion", javaVersion);
    }

    private Float aFloat(Float f){
        BigDecimal b  =   new  BigDecimal(f);
        float   f1   =  b.setScale(2,  BigDecimal.ROUND_HALF_UP).floatValue();
        return f1;
    }
}
