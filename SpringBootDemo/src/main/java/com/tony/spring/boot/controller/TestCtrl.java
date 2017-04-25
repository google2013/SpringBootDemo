package com.tony.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tony.spring.boot.entity.UserInfo;
import com.tony.spring.boot.mapper.UserInfoMapper;
import com.tony.spring.boot.utils.JsonUtil;
import com.tony.spring.boot.utils.RedisUtil;

/**
 * Created by Administrator on 2017/4/19.
 */
@RestController
@RequestMapping(value="/test")
public class TestCtrl {
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired  
    private UserInfoMapper userInfoMapper;  

    @RequestMapping(value="/index")
    public String index(){
        return "hello world";
    }
    
    /**
     * 向redis存储值
     * @param key
     * @param value
     * @return
     * @throws Exception
     */
    @RequestMapping("/set")  
    public String set(String key, String value) throws Exception{  
    	redisUtil.set(key, value);  
        return "success";  
    }  
    
    /**
     * 获取redis中的值
     * @param key
     * @return
     */
    @RequestMapping("/get")  
    public String get(String key){  
        try {
			return redisUtil.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";  
    }  
    
    /**
     * 获取数据库中的用户
     * @Description TODO
     * @param id
     * @return
     */
    @RequestMapping("/getUser/{id}")  
    public String get(@PathVariable("id")int id){  
        try {
        	System.err.println(id);
        	UserInfo user= userInfoMapper.selectByPrimaryKey(id);
			return JsonUtil.getJsonString(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";  
    }  
}