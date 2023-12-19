<!DOCTYPE html>
<html>
<head>
    <title>Profile</title><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<#--    <style><#include "/css/main.css"></style>-->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

</head>
<body>
<#include "/navbar.ftl">
<div class="container-fluid w-50 h-52" style="margin-top: 5%; background-color: #141414; padding: 2%; border-radius: 15px;">
    <h2 class="text-danger">Your booked tickets<span class="badge bg-light text-dark">${user.getTicketId()?size}</span></h2>
    <#list tickets as ticket>
        <div class="card mb-3">
            <img src="localhost:8080/garbage/${ticket.getId()}${ticket.getExtension()}" class="card-img-top" alt="${ticket.getId()}" style="height: 400px;">
            <div class="card-body">
                <hr class="divider">
                <h5 class="card-title">${ticket.getName()}</h5>
                <p class="card-text">${ticket.getDescription()}</p>
                <p class="card-text"><small class="text-muted">Type: ${ticket.getType()}</small></p>
                <p class="card-text"><small class="text-muted">${ticket.getDates()}</small></p>
                <div style="padding-top: 2px">
                    <form action="http://localhost:8080/profile" method="post">
                        <button class="btn btn-danger" type="submit" name="delete" value="${ticket.getId()}">Delete booking</button>
                    </form>
                </div>
            </div>
        </div>
    </#list>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<script type="text/javascript" src="http://localhost:8080/js/murder.js"></script>
</html>