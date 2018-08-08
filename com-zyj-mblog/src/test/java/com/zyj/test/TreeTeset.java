package com.zyj.test;

import com.zyj.dao.ResourceMapper;
import com.zyj.model.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZhangYajie
 * @Description:
 * @Date: Created in 10:26 2018/6/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TreeTeset {

    @Autowired
    private ResourceMapper resourceMapper;

    @Test
    public void testTree(){
        List<Resource> resources = resourceMapper.selectList();
        List<Resource> resultList = new ArrayList<>();
        for(Resource resource : resources){
            if(StringUtils.isEmpty(resource.getPid())){
                resultList.add(resource);
            }
        }

        for(Resource resource : resultList){
            resource.setChildren(getChildList(resource.getId(),resources));
        }

        System.out.println(resultList);
    }

    private List<Resource> getChildList(Integer id,List<Resource> resources) {
        List<Resource> childList = new ArrayList<>();
        for(Resource resource : resources){
            if(id.equals(resource.getPid())){
                childList.add(resource);
            }
        }
        for(Resource resource : childList){
            resource.setChildren(getChildList(resource.getId(),resources));
        }

        if(CollectionUtils.isEmpty(childList)){
            return null;
        }

        return  childList;
    }

    private List<Resource> getTree(List<Resource> menuList, Integer parentId, List<Resource> resultList){
        for(Resource resource : menuList){
            Integer nodeId = resource.getId();
            Integer pid = resource.getPid();
            if(pid == null) {
                resultList.add(resource);
            }else if (pid != null){
                resource.getChildren().add(resource);
//                resultList.add(resource);
                getTree(menuList, nodeId,resultList);
            }
        }
        return resultList;
    }

}
