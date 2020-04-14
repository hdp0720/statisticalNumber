package com.example.demo.service;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class OnLineCount implements HttpSessionListener {

    //记录session总数
    public int count=0;

    public OnLineCount(){
        System.out.println("online触发");
    }
    @Override
    public synchronized void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("监听count++增加");
        count++;
        httpSessionEvent.getSession().getServletContext().setAttribute("count",count);
    }

    @Override
    public synchronized void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("监听count--减少");
        count--;
        httpSessionEvent.getSession().getServletContext().setAttribute("count",count);
    }
}
