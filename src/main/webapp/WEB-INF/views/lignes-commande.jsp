<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Détails de la Commande #${commande.idCommande}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-4">
    <a href="commandes" class="btn btn-secondary mb-3">&larr; Retour aux commandes</a>
    <div class="card shadow-sm p-3 mb-4">
        <h4>Commande #${commande.idCommande} — Client : <strong>${commande.nomClient}</strong> (Date : ${commande.dateCommande})</h4>
    </div>

    <div class="row">
        <!-- Ajouter un produit à la commande -->
        <div class="col-md-4">
            <div class="card shadow-sm p-3">
                <h5>Ajouter une Ligne de Commande</h5>
                <form action="commandes" method="post">
                    <input type="hidden" name="type" value="ligne">
                    <input type="hidden" name="idCommande" value="${commande.idCommande}">
                    <div class="mb-2">
                        <label>Produit</label>
                        <select name="idProduit" class="form-select" required>
                            <c:forEach var="p" items="${produits}">
                                <option value="${p.idProduit}">${p.libelle} (${p.prixUnitaire} DH)</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label>Quantité</label>
                        <input type="number" name="quantite" min="1" value="1" class="form-control" required>
                    </div>
                    <button type="submit" class="btn btn-primary w-100">Ajouter à la commande</button>
                </form>
            </div>
        </div>

        <!-- Liste des lignes de la commande -->
        <div class="col-md-8">
            <div class="card shadow-sm p-3">
                <h5>Produits commandés</h5>
                <table class="table table-bordered mt-2">
                    <thead class="table-dark">
                    <tr>
                        <th>Produit</th>
                        <th>Prix Unitaire</th>
                        <th>Quantité</th>
                        <th>Sous-total</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="total" value="0" />
                    <c:forEach var="l" items="${lignes}">
                        <tr>
                            <td>${l.libelleProduit}</td>
                            <td>${l.prixUnitaire} DH</td>
                            <td>${l.quantite}</td>
                            <td>${l.prixUnitaire * l.quantite} DH</td>
                            <td>
                                <a href="commandes?action=deleteLigne&idLigne=${l.idLigne}&idCommande=${commande.idCommande}" class="btn btn-sm btn-danger">Retirer</a>
                            </td>
                        </tr>
                        <c:set var="total" value="${total + (l.prixUnitaire * l.quantite)}" />
                    </c:forEach>
                    </tbody>
                    <tfoot>
                    <tr class="table-secondary fw-bold">
                        <td colspan="3" class="text-end">Total de la commande :</td>
                        <td colspan="2">${total} DH</td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>