package ma.fstt.controller;

import ma.fstt.dao.ClientDao;
import ma.fstt.entities.Client;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/clients")
public class ClientServlet extends HttpServlet {

    @Inject
    private ClientDao clientDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        try {
            if ("delete".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                clientDao.delete(id);
                resp.sendRedirect("clients");
                return;
            } else if ("edit".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("clientEdit", clientDao.getById(id));
            }
            req.setAttribute("clients", clientDao.getAll());
            req.getRequestDispatcher("/WEB-INF/views/clients.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String idStr = req.getParameter("idClient");
        int id = (idStr == null || idStr.isEmpty()) ? 0 : Integer.parseInt(idStr);

        Client c = new Client(
                id,
                req.getParameter("nom"),
                req.getParameter("prenom"),
                req.getParameter("email"),
                req.getParameter("telephone"),
                req.getParameter("adresse")
        );

        try {
            if (id == 0) {
                clientDao.save(c);
            } else {
                clientDao.update(c);
            }
            resp.sendRedirect("clients");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}