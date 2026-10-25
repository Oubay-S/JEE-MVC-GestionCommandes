package ma.fstt.dao;

import ma.fstt.entities.Produit;
import ma.fstt.service.BaseRepository;

import javax.enterprise.context.ApplicationScoped;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProduitDao implements BaseRepository<Produit> {

    @Override
    public void save(Produit produit) throws SQLException {
        String sql = "INSERT INTO produit (libelle, prix_unitaire, quantite_stock) VALUES (?, ?, ?)";
        try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql)) {
            ps.setString(1, produit.getLibelle());
            ps.setDouble(2, produit.getPrixUnitaire());
            ps.setInt(3, produit.getQuantiteStock());
            ps.executeUpdate();
        }
    }

    @Override
    public void update(Produit produit) throws SQLException {
        String sql = "UPDATE produit SET libelle=?, prix_unitaire=?, quantite_stock=? WHERE id_produit=?";
        try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql)) {
            ps.setString(1, produit.getLibelle());
            ps.setDouble(2, produit.getPrixUnitaire());
            ps.setInt(3, produit.getQuantiteStock());
            ps.setInt(4, produit.getIdProduit());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM produit WHERE id_produit=?";
        try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public Produit getById(int id) throws SQLException {
        String sql = "SELECT * FROM produit WHERE id_produit=?";
        try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Produit(
                            rs.getInt("id_produit"),
                            rs.getString("libelle"),
                            rs.getDouble("prix_unitaire"),
                            rs.getInt("quantite_stock")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Produit> getAll() throws SQLException {
        List<Produit> list = new ArrayList<>();
        String sql = "SELECT * FROM produit";
        try (Statement st = ConnectionManager.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Produit(
                        rs.getInt("id_produit"),
                        rs.getString("libelle"),
                        rs.getDouble("prix_unitaire"),
                        rs.getInt("quantite_stock")
                ));
            }
        }
        return list;
    }
}