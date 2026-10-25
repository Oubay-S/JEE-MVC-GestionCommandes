<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Espace Commande</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">Gestion Commandes</a>
        <div class="navbar-nav">
            <a class="nav-link" href="clients">Espace Client</a>
            <a class="nav-link" href="produits">Espace Produit</a>
            <a class="nav-link active" href="commandes">Espace Commande</a>
        </div>
    </div>
</nav>

<div class="container">
    <div class="row">
        <!-- Formulaire Commande -->
        <div class="col-md-4">
            <div class="card shadow-sm p-3">
                <h5>${commandeEdit != null ? 'Modifier la Commande' : 'Nouvelle Commande'}</h5>
                <form action="commandes" method="post">
                    <input type="hidden" name="idCommande" value="${commandeEdit.idCommande}">
                    <div class="mb-2">
                        <label>Date de Commande</label>
                        <input type="date" name="dateCommande" class="form-control" value="${commandeEdit.dateCommande}" required>
                    </div>
                    <div class="mb-3">
                        <label>Client</label>
                        <select name="idClient" class="form-select" required>
                            <option value="">-- Choisir un client --</option>
                            <c:forEach var="cl" items="${clients}">
                                <option value="${cl.idClient}" ${commandeEdit.idClient == cl.idClient ? 'selected' : ''}>
                                        ${cl.nom} ${cl.prenom}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-warning w-100">Enregistrer</button>
                    <c:if test="${commandeEdit != null}">
                        <a href="commandes" class="btn btn-secondary w-100 mt-2">Annuler</a>
                    </c:if>
                </form>
            </div>
        </div>

        <!-- Liste des Commandes -->
        <div class="col-md-8">
            <div class="card shadow-sm p-3">
                <h5>Liste des Commandes</h5>
                <table class="table table-striped table-hover mt-2">
                    <thead class="table-dark">
                    <tr>
                        <th>N° Commande</th>
                        <th>Date</th>
                        <th>Client</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="cmd" items="${commandes}">
                        <tr>
                            <td>#${cmd.idCommande}</td>
                            <td>${cmd.dateCommande}</td>
                            <td>${cmd.nomClient}</td>
                            <td>
                                <a href="commandes?action=details&id=${cmd.idCommande}" class="btn btn-sm btn-info text-white">Lignes / Produits</a>
                                <a href="commandes?action=edit&id=${cmd.idCommande}" class="btn btn-sm btn-warning">Modifier</a>
                                <a href="commandes?action=delete&id=${cmd.idCommande}" class="btn btn-sm btn-danger" onclick="return confirm('Supprimer cette commande ?')">Supprimer</a>
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