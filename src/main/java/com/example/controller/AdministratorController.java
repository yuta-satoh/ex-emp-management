package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.form.InsertAdministratorForm;


@Controller
@RequestMapping("/")
public class AdministratorController {

    /**
     * 検索フォーム表示
     * @param form
     * @return
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form) {
        return "administrator/insert";
    }
    
}
