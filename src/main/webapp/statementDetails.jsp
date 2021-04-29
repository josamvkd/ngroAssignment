<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Statement details</title>
<script type="text/javascript" src="application.js"></script>
</head>
<body>

<nav class="navbar navbar-light bg-light">
  <a class="navbar-brand">Statement Details</a>
  <form class="form-inline">
    <a class="btn btn-primary my-2 my-sm-0" href="/logout">logout</a>
  </form>
</nav>

<form action="/statements" method="GET">
<table>
<tr>
<td>AccountID:</td>
<td>
<input type="text" name="accountId" id="accountId" style="width: 20%">
</td>
</tr>
<tr>
<td>From Date:</td>
<td><input type="date" name="fromDate" id="fromDate" value="" style="width: 20%"></td>
</tr>

<tr>
<td>Todate:</td>
<td><input type="date" name="toDate" id="toDate" style="width: 20%"></td>
</tr>

<tr>
<td>From Amount:</td>
<td><input type="text" name="fromAmount" id="fromAmount" style="width: 20%"></td>
</tr>
<tr>
<td>To Amount:</td>
<td><input type="text" name="toAmount" id="toAmount" style="width: 20%"></td>
</tr>
<tr>
<td>Response:</td>
<td><textarea disabled style="width: 871px;height: 279px;" name="response" id="response" ></textarea></td>
</tr>
<tr>
<td><button type="button" onclick="clearall()">Clear</button></td>
<td><button type="button" onclick="callStatement()">Submit</button></td>
</tr>
</table>
</form>
</body>
</html>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>