<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

<!-- Script -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

<link rel="stylesheet" type="text/css" href='<c:url value="./resources/css/style.css"/>'>

<title>Produto</title>
</head>
<body>
<div class="bg-black custom">
	<div class="container bg-white scroll" id="nav-container">
		<h1 class="text-center">CRUD - Produto</h1>
		<nav class="navbar navbar-expand">
			<div class="container-fluid">
				<div class="m-auto">
					<div class="row">
						<div class="col-auto p-0 text-center" style="width: 150px;">
							<button class="btn btn-outline-primary" style="width: 140px" OnClick="window.location.href='./'">home</button>
						</div>
						<div class="col-auto p-0 text-center" style="width: 150px;">
							<button class="btn btn-outline-primary" style="width: 140px" OnClick="window.location.href='./produto'">produto</button>
						</div>
						<div class="col-auto p-0 text-center" style="width: 150px;">
							<button class="btn btn-outline-primary" style="width: 140px" OnClick="window.location.href='./produto_info'">info de produto</button>
						</div>
					</div>
				</div>
			</div>
		</nav>
		<main class="rounded-4 border border-primary form-container m-auto mb-3">
			<form action="produto_info" method="post">
				<div class="form-floating d-flex mb-3">
					<input type=text class="form-control input-height" id="floatingInput" placeholder="Valor" name="valor" maxlength="11" oninput="this.value = this.value.replace(/[^0-9]/g, '')">
					<label for="floatingInput" class="font-text">Valor</label>
				</div>
				<div class="d-flex justify-content-between">
					<button class="btn btn-outline-secondary" name="botao" value="qtdProduto">Quantidade produto</button>
					<button class="btn btn-outline-secondary" name="botao" value="qtdEstoque">Quantidade estoque</button>
				</div>
			</form>
		</main>
		<div>
			<c:if test="${not empty erro}">
				<h2 class="text-center"><b><c:out value="${erro}"/></b></h2>
			</c:if>
		</div>
		<div>
			<c:if test="${not empty saida}">
				<h2 class="text-center"><b><c:out value="${saida}"/></b></h2>
			</c:if>
		</div>
		<c:if test="${not empty qtdProduto}">
			<div class="form-container m-auto border border-primary rounded-4" style="max-width: 650px;">
				<table class="table table-striped">
					<thead>
						<tr>
							<th class="col">Codigo</th>
							<th class="col">Nome</th>
							<th class="col">Quantidade de Estoque</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="p" items="${qtdProduto}">
							<tr>
								<th scope="row"><c:out value="${p.codigo}"/></th>
								<td><c:out value="${p.nome}"/></td>
								<td><c:out value="${p.qtdEstoque}"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:if>
		<c:if test="${qtdEstoque != 0}">
			<div class="form-container m-auto border border-primary rounded-4 justify-content-center d-flex" style="max-width: 300px;">
				Quantidade em estoque: <c:out value="${qtdEstoque}"/>
			</div>
		</c:if>
	</div>
</div>
</body>
</html>
