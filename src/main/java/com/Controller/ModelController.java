package com.Controller;

import com.alibaba.fastjson.JSON;
import com.pojo.RE;
import com.pojo.Student;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@RestController
public class ModelController {
    @RequestMapping("/login")
    public RE login(String passWord, String userName, HttpSession session){
        System.out.println(userName+","+passWord);
        if (userName.equals("admin")&&passWord.equals("admin")){
            session.setAttribute("userName",userName);
            RE re = new RE(1, "登录成功", null);
            return re;
        }else {
            RE re = new RE(2, "登录失败", null);
            return re;
        }
    }
    @RequestMapping("/getAll")
    public ArrayList<Student> getAll(){
        System.out.println("getAll");
        ArrayList<Student> list = new ArrayList<Student>();
        Student c = new Student(1, "陈儿子");
        Student s = new Student(2, "石最帅");
        list.add(c);
        list.add(s);
        System.out.println(list);
        return list;
    }

}
