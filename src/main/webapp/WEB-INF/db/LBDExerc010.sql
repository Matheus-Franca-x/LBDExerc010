

CREATE DATABASE produtoSpring
GO
USE produtoSpring
GO
CREATE TABLE produto
(
	codigo		INT	IDENTITY(1, 1)	NOT NULL,
	nome		VARCHAR(50)			NOT NULL,
	valor		DECIMAL(7, 2)		NOT NULL,
	qtd_estoque	INT 				NOT NULL
	PRIMARY KEY (codigo)
)


DROP PROCEDURE sp_crud_produto

CREATE PROCEDURE sp_crud_produto
(
	@acao VARCHAR(1), 
	@codigo INT, 
	@nome VARCHAR(50), 
	@valor DECIMAL(7, 2),
	@qtd_estoque INT,
	@saida VARCHAR(100) OUTPUT
)
AS
	IF (UPPER(@acao) = 'I')
	BEGIN
		INSERT INTO produto (nome, valor, qtd_estoque)
		VALUES
		(@nome, @valor, @qtd_estoque)
		SET @saida = 'Produto Cadastrado!'
	END
	IF (UPPER(@acao) = 'U')
	BEGIN
		IF EXISTS (SELECT codigo FROM produto WHERE codigo = @codigo)
		BEGIN 
			UPDATE produto
			SET nome = @nome, valor = @valor, qtd_estoque = @qtd_estoque
			WHERE codigo = @codigo
			SET @saida = 'Produto Atualizado!'
		END
		ELSE 
		BEGIN 
			SET @saida = 'Produto não existe!'
		END
	END
	IF (UPPER(@acao) = 'D')
	BEGIN
		IF EXISTS (SELECT codigo FROM produto WHERE codigo = @codigo)
		BEGIN 
			DELETE produto
			WHERE codigo = @codigo
			SET @saida = 'Produto Excluido!'
		END
		ELSE 
		BEGIN 
			SET @saida = 'Produto não existe!'
		END
	END
	
DECLARE @out VARCHAR(100)
EXEC sp_crud_produto 'D', 6, 'seilASDASAAa', 1.50, 7, @out OUTPUT
PRINT @out

CREATE FUNCTION fn_qtd_estoque(@estoque_min INT)
RETURNS INT
AS
BEGIN
	DECLARE @qtd_estoque INT
	
	SELECT @qtd_estoque = COUNT(qtd_estoque) FROM produto WHERE qtd_estoque <= @estoque_min
	
	RETURN @qtd_estoque
END

SELECT dbo.fn_qtd_estoque(25) AS qtd_estoque

CREATE FUNCTION fn_qtd_produto(@estoque_min INT)
RETURNS @table TABLE
(
	codigo 		INT,
	nome		VARCHAR(50),
	qtd_estoque	INT
)
AS
BEGIN
	INSERT INTO @table
		SELECT codigo, nome, qtd_estoque FROM produto WHERE qtd_estoque <= @estoque_min
		
	RETURN
END

SELECT * FROM fn_qtd_produto(25) 

SELECT codigo, nome, valor, qtd_estoque FROM produto

SELECT * FROM produto p 