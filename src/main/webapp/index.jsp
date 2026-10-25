<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestion des Commandes - JEE MVC</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-5">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">FST Tanger - Gestion Commandes</a>
        <div class="navbar-nav">
            <a class="nav-link" href="clients">Espace Client</a>
            <a class="nav-link" href="produits">Espace Produit</a>
            <a class="nav-link" href="commandes">Espace Commande</a>
        </div>
    </div>
</nav>

<div class="container text-center">
    <h1 class="mb-4">Application Web JEE - Gestion des Commandes</h1>
    <p class="lead mb-5">Sélectionnez un espace ci-dessous pour gérer les données (CRUD) :</p>
    <div class="row justify-content-center">
        <div class="col-md-3">
            <div class="card shadow-sm p-4">
                <h3>Espace Client</h3>
                <p>Gérer les clients de l'application</p>
                <a href="clients" class="btn btn-primary">Accéder</a>
            </div>
        </div>
        <div class="col-md-3">
            <div class="card shadow-sm p-4">
                <h3>Espace Produit</h3>
                <p>Gérer le catalogue de produits</p>
                <a href="produits" class="btn btn-success">Accéder</a>
            </div>
        </div>
        <div class="col-md-3">
            <div class="card shadow-sm p-4">
                <h3>Espace Commande</h3>
                <p>Gérer les commandes et lignes</p>
                <a href="commandes" class="btn btn-warning">Accéder</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>