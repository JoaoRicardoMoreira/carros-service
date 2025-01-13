package com.crudDemo.rest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
@RestController
public class TesteController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/teste-jdbc")
    public String testarConexaoJDBC() {
        try (Connection connection = dataSource.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT 1 FROM DUAL")) {

            if (rs.next()) {
                return "Conexão bem-sucedida! Resultado: " + rs.getInt(1);
            } else {
                return "Conexão falhou: Sem resultado.";
            }
        } catch (Exception e) {
            return "Erro na conexão: " + e.getMessage();
        }
    }
}
