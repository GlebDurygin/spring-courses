package com.luxoft.springdb.lab3.dao;

import com.luxoft.springdb.lab3.model.Country;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryDao extends JdbcDaoSupport {

    private static final CountryRowMapper COUNTRY_ROW_MAPPER = new CountryRowMapper();

    public List<Country> getCountryList() {
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        return jdbcTemplate != null
                ? jdbcTemplate.query("select * from country", COUNTRY_ROW_MAPPER)
                : Collections.emptyList();
    }

    public List<Country> getCountryListStartWith(String name) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        return namedParameterJdbcTemplate.query("select * from country where name like :name",
                Collections.singletonMap("name", name + "%"), COUNTRY_ROW_MAPPER);
    }

    public void updateCountryName(String codeName, String newCountryName) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

        Map<String, Object> params = new HashMap<>();
        params.put("name", newCountryName);
        params.put("codeName", codeName);

        namedParameterJdbcTemplate.update("update country SET name = :name where code_name = :codeName", params);
    }

    public Country getCountryByCodeName(String codeName) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        return namedParameterJdbcTemplate.queryForObject(
                "select * from country where code_name = :codeName",
                Collections.singletonMap("codeName", codeName),
                COUNTRY_ROW_MAPPER);
    }

    public Country getCountryByName(String name) throws CountryNotFoundException {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        try {
            return namedParameterJdbcTemplate.queryForObject(
                    "select * from country where name = :name",
                    Collections.singletonMap("name", name),
                    COUNTRY_ROW_MAPPER);
        } catch (DataAccessException e) {
            throw new CountryNotFoundException();
        }
    }
}
