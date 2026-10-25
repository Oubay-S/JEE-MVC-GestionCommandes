package ma.fstt.dao;

import ma.fstt.entities.Commande;
import ma.fstt.service.BaseRepository;

import javax.enterprise.context.ApplicationScoped;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CommandeDao implements BaseRepository<Commande> {

    @Override
    public void save(Commande commande) throws SQLException {
        String sql = "INSERT INTO commande (date_commande, id_client) VALUES (?, ?)";
        try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql)) {
            ps.setDate(1, commande.getDateCommande());
            ps.setInt(2, commande.getIdClient());
            ps.executeUpdate();
        }
    }

    @Override
    public void update(Commande commande) throws SQLException {
        String sql = "UPDATE commande SET date_commande=?, id_client=? WHERE id_commande=?";
        try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql)) {
            ps.setDate(1, commande.getDateCommande());
            ps.setInt(2, commande.getIdClient());
            ps.setInt(3, commande.getIdCommande());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM commande WHERE id_commande=?";
        try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public Commande getById(int id) throws SQLException {
        String sql = "SELECT c.*, cl.nom, cl.prenom FROM commande c JOIN client cl ON c.id_client = cl.id_client WHERE c.id_commande=?";
        try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Commande cmd = new Commande(
                            rs.getInt("id_commande"),
                            rs.getDate("date_commande"),
                            rs.getInt("id_client")
                    );
                    cmd.setNomClient(rs.getString("nom") + " " + rs.getString("prenom"));
                    return cmd;
                }
            }
        }
        return null;
    }

    @Override
    public List<Commande> getAll() throws SQLException {
        List<Commande> list = new ArrayList<>();
        String sql = "SELECT c.*, cl.nom, cl.prenom FROM commande c JOIN client cl ON c.id_client = cl.id_client ORDER BY c.id_commande DESC";
        try (Statement st = ConnectionManager.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Commande cmd = new Commande(
                        rs.getInt("id_commande"),
                        rs.getDate("date_commande"),
                        rs.getInt("id_client")
                );
                cmd.setNomClient(rs.getString("nom") + " " + rs.getString("prenom"));
                list.add(cmd);
            }
        }
        return list;
    }
}