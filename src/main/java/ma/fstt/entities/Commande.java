package ma.fstt.entities;

import java.sql.Date;

public class Commande {
    private int idCommande;
    private Date dateCommande;
    private int idClient;
    private String nomClient; // Utile pour afficher le nom du client dans la JSP

    public Commande() {}

    public Commande(int idCommande, Date dateCommande, int idClient) {
        this.idCommande = idCommande;
        this.dateCommande = dateCommande;
        this.idClient = idClient;
    }

    public int getIdCommande() { return idCommande; }
    public void setIdCommande(int idCommande) { this.idCommande = idCommande; }

    public Date getDateCommande() { return dateCommande; }
    public void setDateCommande(Date dateCommande) { this.dateCommande = dateCommande; }

    public int getIdClient() { return idClient; }
    public void setIdClient(int idClient) { this.idClient = idClient; }

    public String getNomClient() { return nomClient; }
    public void setNomClient(String nomClient) { this.nomClient = nomClient; }
}