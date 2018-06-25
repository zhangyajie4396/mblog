package com.zyj.util;

import com.zyj.beans.ResultBean;

/**
 * @Author: ZhangYajie
 * @Description:
 * @Date: Created in 16:09 2018/6/25
 */
public class ResultBeanUtil {
    public static ResultBean getResultBean(Object data){
        return new ResultBean(data,200,"操作成功",true);
    }

    public static ResultBean getSuccess(){
        return new ResultBean(null,200,"操作成功",true);
    }

    public static ResultBean getFail(String msg){
        return new ResultBean(null,500,msg,false);
    }
}
