USE [LojaCerta]
GO
/****** Object:  StoredProcedure [dbo].[BuscaClientePorNome]    Script Date: 09/17/2010 11:47:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER PROCEDURE [dbo].[BuscaClientePorNome]
@Nome varchar(100)

AS
BEGIN
	SELECT idCliente,Nome,Idade,Email,DataNascimento 
	FROM Clientes 
	WHERE Nome = @Nome 
END
