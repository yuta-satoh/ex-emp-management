package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;

@Service
@Transactional
public class AdministratorService {
    @Autowired
    private AdministratorRepository repository;

    /**
     * 管理者登録
     * 
     * @param name, mail_address, password
     */
    public void insert(Administrator administrator) {
        repository.insert(administrator);
    }

    /**
     * ログイン処理
     * 
     * @param mailAddress
     * @param password
     * @return 管理者情報
     */
    public Administrator login(String mailAddress, String password) {
        Administrator administrator = repository.findByMailAddressAndPassword(mailAddress, password);
        return administrator;
    }
}
