package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class DemoRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DemoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> getAllUserData() {
        return jdbcTemplate.queryForList("SELECT * FROM user");
    }

    public List<Map<String, Object>> searchUserData(String keyword) {
        String query = "SELECT * FROM user WHERE user.number LIKE '%" + keyword + "%' OR user.name LIKE '%" + keyword + "%'";
        return jdbcTemplate.queryForList(query);
    }

    public void addUserData(String number, String name) {
        String query = "INSERT INTO user (number, name) VALUES (?, ?)";
        jdbcTemplate.update(query, number, name);
    }

    public void editUserData(Long id, String number, String name) {
        String query = "UPDATE user SET number = ?, name = ? WHERE id = ?";
        jdbcTemplate.update(query, number, name, id);
    }

    public void deleteUserData(Long id) {
        String query = "DELETE FROM user WHERE id = ?";
        jdbcTemplate.update(query, id);
    }
}
