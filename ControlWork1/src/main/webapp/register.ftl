<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <style><#include "/css/main.css"></style>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

</head>
<body>
<div class="container-fluid w-50 h-52 position-absolute top-50 start-50 translate-middle" style="background-color: #e7ff9c">
    <p class="fw-semibold">Enter your data</p>
    <form action="http://localhost:8080/register" method="post">
        <div class="mb-3 row">
            <label class="col-sm-2 col-form-label">Username</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="usernameR" required>
                <#if nameIsPresent??>
                    <p class="text-danger">This username already used</p>
                </#if>
            </div>
        </div>
        <div class="mb-3 row">
            <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="inputPassword" name="passwordR" required>
            </div>
        </div>

        <button type="submit" class="btn btn-warning text-dark mb-3">Sign Up</button>
        <p class="text-muted">
            Already have an account? Go <a href="http://localhost:8080/login" class="text-reset">here</a>.
        </p>
    </form>
</div>
<div class="baton" >
    <a href="#" class="btn btn-warning text-dark themeToggle"><span class="material-symbols-outlined" >bedtime</span></a>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<script type="text/javascript" src="http://localhost:8080/js/murder.js"></script>
</html>