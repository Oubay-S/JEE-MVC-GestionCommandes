package ma.fstt.controller;

import ma.fstt.dao.ClientDao;
import ma.fstt.dao.CommandeDao;
import ma.fstt.dao.LignedeCommandeDao;
import ma.fstt.dao.ProduitDao;
import ma.fstt.entities.Commande;
import ma.fstt.entities.LignedeCommande;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet("/commandes")
public class CommandeServlet extends HttpServlet {

    @Inject
    private CommandeDao commandeDao;
    @Inject
    private ClientDao clientDao;
    @Inject
    private ProduitDao produitDao;
    @Inject
    private LignedeCommandeDao ligneDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "delete":
                    commandeDao.delete(Integer.parseInt(req.getParameter("id")));
                    resp.sendRedirect("commandes");
                    return;

                case "edit":
                    int idEdit = Integer.parseInt(req.getParameter("id"));
                    req.setAttribute("commandeEdit", commandeDao.getById(idEdit));
                    break;

                case "details":
                    int idCmd = Integer.parseInt(req.getParameter("id"));
                    req.setAttribute("commande", commandeDao.getById(idCmd));
                    req.setAttribute("lignes", ligneDao.getByCommandeId(idCmd));
                    req.setAttribute("produits", produitDao.getAll());
                    req.getRequestDispatcher("/WEB-INF/views/lignes-commande.jsp").forward(req, resp);
                    return;

                case "deleteLigne":
                    int idLigne = Integer.parseInt(req.getParameter("idLigne"));
                    int idCommande = Integer.parseInt(req.getParameter("idCommande"));
                    ligneDao.delete(idLigne);
                    resp.sendRedirect("commandes?action=details&id=" + idCommande);
                    return;
            }

            req.setAttribute("commandes", commandeDao.getAll());
            req.setAttribute("clients", clientDao.getAll());
            req.getRequestDispatcher("/WEB-INF/views/commandes.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String type = req.getParameter("type");

        try {
            if ("ligne".equals(type)) {
                int idCommande = Integer.parseInt(req.getParameter("idCommande"));
                int idProduit = Integer.parseInt(req.getParameter("idProduit"));
                int quantite = Integer.parseInt(req.getParameter("quantite"));

                ligneDao.save(new LignedeCommande(0, quantite, idCommande, idProduit));
                resp.sendRedirect("commandes?action=details&id=" + idCommande);
            } else {
                String idStr = req.getParameter("idCommande");
                int id = (idStr == null || idStr.isEmpty()) ? 0 : Integer.parseInt(idStr);
                Date date = Date.valueOf(req.getParameter("dateCommande"));
                int idClient = Integer.parseInt(req.getParameter("idClient"));

                Commande cmd = new Commande(id, date, idClient);
                if (id == 0) {
                    commandeDao.save(cmd);
                } else {
                    commandeDao.update(cmd);
                }
                resp.sendRedirect("commandes");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}