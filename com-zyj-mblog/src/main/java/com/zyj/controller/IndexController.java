package com.zyj.controller;

import com.zyj.beans.ResultBean;
import com.zyj.model.Resource;
import com.zyj.service.IResourceService;
import com.zyj.util.ResultBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        model.addObject("menuList",resourceService.findMenu());
        return model;
    }

    @RequestMapping("/home")
    @ResponseBody
    public ResultBean home(){
        return ResultBeanUtil.getResultBean(pushSystemStatus());
    }

    private Map<String,Object> pushSystemStatus() {
        Map<String,Object> map = new HashMap<>();
        float freeMemory = (float) Runtime.getRuntime().freeMemory();
        float totalMemory = (float) Runtime.getRuntime().totalMemory();
        float usedMemory = (totalMemory - freeMemory);
        float memPercent = Math.round(freeMemory / totalMemory * 100) ;
        String os = System.getProperty("os.name");
        String javaVersion = System.getProperty("java.version");



        map.put("freeMemory", freeMemory);
        map.put("totalMemory", aFloat(totalMemory / 1024 / 1024));
        map.put("usedMemory", aFloat(usedMemory / 1024 / 1024));
        map.put("memPercent", aFloat(memPercent));
        map.put("os", os);
        map.put("javaVersion", javaVersion);
        return map;
    }

    private Float aFloat(Float f){
        BigDecimal b  =   new  BigDecimal(f);
        float   f1   =  b.setScale(2,  BigDecimal.ROUND_HALF_UP).floatValue();
        return f1;
    }


}
