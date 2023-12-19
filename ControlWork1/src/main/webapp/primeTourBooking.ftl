<!DOCTYPE html>
<html>
<head>
    <title>Profile</title><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <#--    <style><#include "/css/main.css"></style>-->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

</head>
<body>
<#include "/navbar.ftl">
<div class="container">
    <h2>PC ${exhibition.id}</h2>
    <h3>Lasting from: ${pc.startWorkingTime}</h3>
    <h3>Lasting to: ${pc.endWorkingTime}</h3>
    <#if user??>
        <div class="container">
            <h2>Search tour on time</h2>
            <div class="form-group">
                <label for="date">Date:</label>
                <input type="date" class="form-control" id="date" name="date" required>
            </div>
            <div class="form-group">
                <label for="from">Time from:</label>
                <input type="time" class="form-control" id="from" name="from" required>
            </div>
            <div class="form-group">
                <label for="to">Time to:</label>
                <input type="time" class="form-control" id="to" name="to" required>
            </div>
            <a class="btn btn-warning" id="create_booking">Create booking</a>
        </div>
</div>
<table class="table" id="booking_table">
    <thead>
    <tr>
        <th>From</th>
        <th>To</th>
        <th>Status</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    </#if>
    <#list bookingList as booking>
        <tr>
            <td>${booking.timeFrom}</td>
            <td>${booking.timeTo}</td>
            <td>${booking.status.getValue()}</td>
            <td><a id="delete_booking" data-target="${booking.id}" class="btn btn-warning delete_booking">Delete</a>
            </td>
        </tr>
    </#list>
    </tbody>
</table>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<script type="text/javascript" src="http://localhost:8080/js/murder.js"></script>
</html>