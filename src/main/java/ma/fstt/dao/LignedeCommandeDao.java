package ma.fstt.dao;

import ma.fstt.entities.LignedeCommande;
import ma.fstt.service.BaseRepository;

import javax.enterprise.context.ApplicationScoped;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class LignedeCommandeDao implements BaseRepository<LignedeCommande> {

    @Override
    public void save(LignedeCommande ligne) throws SQLException {
        String sql = "INSERT INTO lignedecommande (quantite, id_commande, id_produit) VALUES (?, ?, ?)";
        try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, ligne.getQuantite());
            ps.setInt(2, ligne.getIdCommande());
            ps.setInt(3, ligne.getIdProduit());
            ps.executeUpdate();
        }
    }

    @Override
    public void update(LignedeCommande ligne) throws SQLException {
        String sql = "UPDATE lignedecommande SET quantite=?, id_commande=?, id_produit=? WHERE id_ligne=?";
        try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, ligne.getQuantite());
            ps.setInt(2, ligne.getIdCommande());
            ps.setInt(3, ligne.getIdProduit());
            ps.setInt(4, ligne.getIdLigne());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM lignedecommande WHERE id_ligne=?";
        try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public LignedeCommande getById(int id) throws SQLException {
        String sql = "SELECT * FROM lignedecommande WHERE id_ligne=?";
        try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new LignedeCommande(
                            rs.getInt("id_ligne"),
                            rs.getInt("quantite"),
                            rs.getInt("id_commande"),
                            rs.getInt("id_produit")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<LignedeCommande> getAll() throws SQLException {
        List<LignedeCommande> list = new ArrayList<>();
        String sql = "SELECT l.*, p.libelle, p.prix_unitaire FROM lignedecommande l JOIN produit p ON l.id_produit = p.id_produit";
        try (Statement st = ConnectionManager.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                LignedeCommande l = new LignedeCommande(
                        rs.getInt("id_ligne"),
                        rs.getInt("quantite"),
                        rs.getInt("id_commande"),
                        rs.getInt("id_produit")
                );
                l.setLibelleProduit(rs.getString("libelle"));
                l.setPrixUnitaire(rs.getDouble("prix_unitaire"));
                list.add(l);
            }
        }
        return list;
    }

    // Méthode dédiée pour récupérer toutes les lignes d'une commande spécifique
    public List<LignedeCommande> getByCommandeId(int idCommande) throws SQLException {
        List<LignedeCommande> list = new ArrayList<>();
        String sql = "SELECT l.*, p.libelle, p.prix_unitaire FROM lignedecommande l JOIN produit p ON l.id_produit = p.id_produit WHERE l.id_commande=?";
        try (PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idCommande);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    LignedeCommande l = new LignedeCommande(
                            rs.getInt("id_ligne"),
                            rs.getInt("quantite"),
                            rs.getInt("id_commande"),
                            rs.getInt("id_produit")
                    );
                    l.setLibelleProduit(rs.getString("libelle"));
                    l.setPrixUnitaire(rs.getDouble("prix_unitaire"));
                    list.add(l);
                }
            }
        }
        return list;
    }
}