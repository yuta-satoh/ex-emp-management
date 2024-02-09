package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/")
public class AdministratorController {

    @Autowired
    private AdministratorService service;

    /**
     * 検索フォーム表示
     * @param form
     * @return フォーム画面
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form) {
        return "administrator/insert";
    }

    /** 
     * データ挿入処理
     * @param form
     * @return ログイン画面へリダイレクト
     */
    @PostMapping("/insert")
    public String insert(InsertAdministratorForm form) {
        Administrator administrator = new Administrator(); 
        BeanUtils.copyProperties(form, administrator);
        service.insert(administrator);
        return "redirect:/"; 
    }

    /**
     * ログイン画面表示
     * @param form
     * @return ログイン画面表示
     */
    @GetMapping({"/",""})
    public String toLogin(LoginForm form) {
        return "administrator/login";
    }
    
}
