package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

/**
 * 使用session获取在线人数
 */
@RestController
@RequestMapping
public class IndexController {

    @GetMapping("")
    public String getIndex(){

        return "欢迎欢迎";
    }

    @GetMapping("/getCount")
    public String count(HttpServletRequest request, HttpServletResponse response){
        try {
            //用于浏览器关闭了在访问session重新生成  用于cookie保证多次浏览器关闭只会存在一个
            Cookie c = new Cookie("JSESSIONID", URLEncoder.encode(request.getSession().getId()));
            c.setPath("/");
            c.setMaxAge(24*60*60);
            response.addCookie(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        Object count = session.getServletContext().getAttribute("count");
        return "当前"+count;
    }
}
