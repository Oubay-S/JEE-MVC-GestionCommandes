<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Espace Produit</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">Gestion Commandes</a>
        <div class="navbar-nav">
            <a class="nav-link" href="clients">Espace Client</a>
            <a class="nav-link active" href="produits">Espace Produit</a>
            <a class="nav-link" href="commandes">Espace Commande</a>
        </div>
    </div>
</nav>

<div class="container">
    <div class="row">
        <!-- Formulaire Produit -->
        <div class="col-md-4">
            <div class="card shadow-sm p-3">
                <h5>${produitEdit != null ? 'Modifier le Produit' : 'Ajouter un Produit'}</h5>
                <form action="produits" method="post">
                    <input type="hidden" name="idProduit" value="${produitEdit.idProduit}">
                    <div class="mb-2">
                        <label>Libellé</label>
                        <input type="text" name="libelle" class="form-control" value="${produitEdit.libelle}" required>
                    </div>
                    <div class="mb-2">
                        <label>Prix Unitaire (DH)</label>
                        <input type="number" step="0.01" name="prixUnitaire" class="form-control" value="${produitEdit.prixUnitaire}" required>
                    </div>
                    <div class="mb-3">
                        <label>Quantité en Stock</label>
                        <input type="number" name="quantiteStock" class="form-control" value="${produitEdit.quantiteStock}" required>
                    </div>
                    <button type="submit" class="btn btn-success w-100">Enregistrer</button>
                    <c:if test="${produitEdit != null}">
                        <a href="produits" class="btn btn-secondary w-100 mt-2">Annuler</a>
                    </c:if>
                </form>
            </div>
        </div>

        <!-- Liste des Produits -->
        <div class="col-md-8">
            <div class="card shadow-sm p-3">
                <h5>Catalogue des Produits</h5>
                <table class="table table-striped table-hover mt-2">
                    <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Libellé</th>
                        <th>Prix Unitaire</th>
                        <th>Stock</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="p" items="${produits}">
                        <tr>
                            <td>${p.idProduit}</td>
                            <td>${p.libelle}</td>
                            <td>${p.prixUnitaire} DH</td>
                            <td>${p.quantiteStock}</td>
                            <td>
                                <a href="produits?action=edit&id=${p.idProduit}" class="btn btn-sm btn-warning">Modifier</a>
                                <a href="produits?action=delete&id=${p.idProduit}" class="btn btn-sm btn-danger" onclick="return confirm('Supprimer ce produit ?')">Supprimer</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>