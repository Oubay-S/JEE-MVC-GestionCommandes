package ma.fstt.controller;

import ma.fstt.dao.ProduitDao;
import ma.fstt.entities.Produit;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/produits")
public class ProduitServlet extends HttpServlet {

    @Inject
    private ProduitDao produitDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        try {
            if ("delete".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                produitDao.delete(id);
                resp.sendRedirect("produits");
                return;
            } else if ("edit".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("produitEdit", produitDao.getById(id));
            }
            req.setAttribute("produits", produitDao.getAll());
            req.getRequestDispatcher("/WEB-INF/views/produits.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String idStr = req.getParameter("idProduit");
        int id = (idStr == null || idStr.isEmpty()) ? 0 : Integer.parseInt(idStr);

        Produit p = new Produit(
                id,
                req.getParameter("libelle"),
                Double.parseDouble(req.getParameter("prixUnitaire")),
                Integer.parseInt(req.getParameter("quantiteStock"))
        );

        try {
            if (id == 0) {
                produitDao.save(p);
            } else {
                produitDao.update(p);
            }
            resp.sendRedirect("produits");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}