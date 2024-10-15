package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet("/checkConnection")
public class CheckConnectionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Connection conn = null;

        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/testdb");
            conn = ds.getConnection();

            out.println("Conexion réussie!");
        } catch (NamingException e) {
            out.println("Erreur de configuration JNDI: " + e.getMessage());
        } catch (Exception e) {
            out.println("Erreur connextion BD: " + e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                out.println("Erreur fermeture BD:" + e.getMessage());
            }
        }
    }
}
