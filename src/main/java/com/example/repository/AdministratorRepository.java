package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.domain.Administrator;

@Repository
public class AdministratorRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final String INSERT_ADMINISTRATOR_SQL = """
            INSERT INTO
                administrators(name, mail_address, password)
            VALUES(:name, :mailAddress, :password)
            """;
    
    private static final String FIND_ADMINISTRATOR_SQL = """
            SELECT
                id,
                name,
                mail_address,
                password
            FROM
                administrators
            WHERE
                mail_address = :mailAddress AND password = :password
            """;

    private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, index) -> {
        Administrator administrator = new Administrator();
        administrator.setId(rs.getInt("id"));
        administrator.setName(rs.getString("name"));
        administrator.setMailAddress(rs.getString("mail_address"));
        administrator.setPassword(rs.getString("password"));
        return administrator;
    };

    /**
     * 管理者登録
     * @param name, mail_address, password
     */
    public void insert(Administrator administrator){
        SqlParameterSource params = new BeanPropertySqlParameterSource(administrator);
        KeyHolder holder = new GeneratedKeyHolder();
        String[] keyStrings = {"id"};
        template.update(INSERT_ADMINISTRATOR_SQL, params, holder, keyStrings);
    }

    /**
     * 検索
     * @param mail_address, password
     * @return 管理者情報
     *         該当なしの場合はnull
     */
    public Administrator findByMailAddressAndPassword(String mail_address, String password){
        SqlParameterSource params = new MapSqlParameterSource()
            .addValue("mailAddress", mail_address)
            .addValue("password", password);
        try{
            Administrator administrator = template.queryForObject(FIND_ADMINISTRATOR_SQL, params, ADMINISTRATOR_ROW_MAPPER);
            return administrator;
        }catch(Exception e){
            return null;
        }
    }
}
