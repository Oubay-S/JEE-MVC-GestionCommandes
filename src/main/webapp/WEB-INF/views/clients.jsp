<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Espace Client</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">Gestion Commandes</a>
        <div class="navbar-nav">
            <a class="nav-link active" href="clients">Espace Client</a>
            <a class="nav-link" href="produits">Espace Produit</a>
            <a class="nav-link" href="commandes">Espace Commande</a>
        </div>
    </div>
</nav>

<div class="container">
    <div class="row">
        <!-- Formulaire Ajout / Modification -->
        <div class="col-md-4">
            <div class="card shadow-sm p-3">
                <h5>${clientEdit != null ? 'Modifier le Client' : 'Ajouter un Client'}</h5>
                <form action="clients" method="post">
                    <input type="hidden" name="idClient" value="${clientEdit.idClient}">
                    <div class="mb-2">
                        <label>Nom</label>
                        <input type="text" name="nom" class="form-control" value="${clientEdit.nom}" required>
                    </div>
                    <div class="mb-2">
                        <label>Prénom</label>
                        <input type="text" name="prenom" class="form-control" value="${clientEdit.prenom}" required>
                    </div>
                    <div class="mb-2">
                        <label>Email</label>
                        <input type="email" name="email" class="form-control" value="${clientEdit.email}" required>
                    </div>
                    <div class="mb-2">
                        <label>Téléphone</label>
                        <input type="text" name="telephone" class="form-control" value="${clientEdit.telephone}" required>
                    </div>
                    <div class="mb-3">
                        <label>Adresse</label>
                        <input type="text" name="adresse" class="form-control" value="${clientEdit.adresse}" required>
                    </div>
                    <button type="submit" class="btn btn-primary w-100">Enregistrer</button>
                    <c:if test="${clientEdit != null}">
                        <a href="clients" class="btn btn-secondary w-100 mt-2">Annuler</a>
                    </c:if>
                </form>
            </div>
        </div>

        <!-- Liste des Clients -->
        <div class="col-md-8">
            <div class="card shadow-sm p-3">
                <h5>Liste des Clients</h5>
                <table class="table table-striped table-hover mt-2">
                    <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nom & Prénom</th>
                        <th>Email</th>
                        <th>Téléphone</th>
                        <th>Adresse</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="c" items="${clients}">
                        <tr>
                            <td>${c.idClient}</td>
                            <td>${c.nom} ${c.prenom}</td>
                            <td>${c.email}</td>
                            <td>${c.telephone}</td>
                            <td>${c.adresse}</td>
                            <td>
                                <a href="clients?action=edit&id=${c.idClient}" class="btn btn-sm btn-warning">Modifier</a>
                                <a href="clients?action=delete&id=${c.idClient}" class="btn btn-sm btn-danger" onclick="return confirm('Supprimer ce client ?')">Supprimer</a>
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