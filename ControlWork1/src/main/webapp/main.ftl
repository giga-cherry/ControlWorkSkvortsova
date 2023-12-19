<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Nani?Libria</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<#--    <style><#include "/css/main.css"></style>-->
</head>
<body>
<#include "/navbar.ftl">
<div class="container-fluid w-50 h-52" style="margin-top: 5%; background-color: #141414; padding: 2%; border-radius: 15px;">
    <#list exhibitionList as exhibition>
        <div class="card mb-3">
            <img src="localhost:8080/garbage/${exhibition.getId()}${exhibition.getExtension()}" class="card-img-top" alt="${exhibition.getId()}" style="height: 400px;">
            <div class="card-body">
                <h5 class="card-title">${exhibition.getName()}</h5>
                <p class="card-text">${exhibition.getDescription()}</p>
                <p class="card-text"><small>Type: ${exhibition.getType()}</small></p>
                <p class="card-text"><small>${exhibition.getDates()}</small></p>

                <#if user??>
                    <div style="padding-top: 2px">
                        <form action="http://localhost:8080" method="post">
                            <#list exhibition.getTypes() as type>
                                <p class="card-text">${type.getName()}</p>
                                <p class="card-text">${type.getDescription()}</p>
                                <#if user.getTicketId()?seq_contains(exhibition.getId())>
                                    <button class="btn btn-warning text-dark" type="submit" name="favourites" value="${exhibition.getId()}" disabled>Create booking</button>
                                </#if>
                                <#if !(user.getTicketId()?seq_contains(exhibition.getId()))>
                                    <button class="btn btn-warning text-dark" type="submit" name="favourites" value="${exhibition.getId()}">Create booking</button>
                                </#if>
                            </#list>
                        </form>
                    </div>
                </#if>
            </div>
        </div>
    </#list>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<script type="text/javascript" src="http://localhost:8080/js/murder.js"></script>
</html>