package ma.fstt.entities;

public class LignedeCommande {
    private int idLigne;
    private int quantite;
    private int idCommande;
    private int idProduit;
    private String libelleProduit; // Utile pour l'affichage JSP
    private double prixUnitaire;   // Utile pour calculer le total dans la JSP

    public LignedeCommande() {}

    public LignedeCommande(int idLigne, int quantite, int idCommande, int idProduit) {
        this.idLigne = idLigne;
        this.quantite = quantite;
        this.idCommande = idCommande;
        this.idProduit = idProduit;
    }

    public int getIdLigne() { return idLigne; }
    public void setIdLigne(int idLigne) { this.idLigne = idLigne; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

    public int getIdCommande() { return idCommande; }
    public void setIdCommande(int idCommande) { this.idCommande = idCommande; }

    public int getIdProduit() { return idProduit; }
    public void setIdProduit(int idProduit) { this.idProduit = idProduit; }

    public String getLibelleProduit() { return libelleProduit; }
    public void setLibelleProduit(String libelleProduit) { this.libelleProduit = libelleProduit; }

    public double getPrixUnitaire() { return prixUnitaire; }
    public void setPrixUnitaire(double prixUnitaire) { this.prixUnitaire = prixUnitaire; }
}