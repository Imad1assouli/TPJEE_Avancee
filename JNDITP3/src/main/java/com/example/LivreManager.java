package com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet(name = "LivreManager", urlPatterns = {"/LivreManager"})
public class LivreManager extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DataSource dataSource;

    public void init() throws ServletException {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/gestionLivres");
        } catch (NamingException e) {
            throw new ServletException("Impossible d'accéder à la source de donnes", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "add":
                showAddForm(request, response);
                break;
            case "delete":
                deleteLivre(request, response);
                break;
            default:
                listLivres(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            addLivre(request, response);
        } else {
            response.sendRedirect("LivreManager");
        }
    }

    private void listLivres(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Livre> livres = new ArrayList<>();
        String sql = "SELECT * FROM livre";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Livre livre = new Livre(
                    rs.getString("isbn"),
                    rs.getString("titre"),
                    rs.getString("auteur"),
                    rs.getDouble("prix"),
                    rs.getInt("annee_publication")
                );
                livres.add(livre);
            }
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de la récupération des livres", e);
        }

        request.setAttribute("livres", livres);
        request.getRequestDispatcher("/WEB-INF/listeLivres.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/ajouterLivre.jsp").forward(request, response);
    }

    private void addLivre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        String titre = request.getParameter("titre");
        String auteur = request.getParameter("auteur");
        double prix = Double.parseDouble(request.getParameter("prix"));
        int anneePublication = Integer.parseInt(request.getParameter("anneePublication"));

        String sql = "INSERT INTO livre (isbn, titre, auteur, prix, annee_publication) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, isbn);
            stmt.setString(2, titre);
            stmt.setString(3, auteur);
            stmt.setDouble(4, prix);
            stmt.setInt(5, anneePublication);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de l'ajout du livre", e);
        }

        response.sendRedirect("LivreManager");
    }

    private void deleteLivre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        String sql = "DELETE FROM livre WHERE isbn = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, isbn);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de la suppression du livre", e);
        }

        response.sendRedirect("LivreManager");
    }
}
