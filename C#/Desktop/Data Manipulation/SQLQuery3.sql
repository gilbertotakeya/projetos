USE [LojaCerta]
GO
/****** Object:  StoredProcedure [dbo].[BuscarClientes]    Script Date: 09/17/2010 11:47:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER PROCEDURE [dbo].[BuscarClientes]
@Nome varchar(100)

AS
BEGIN
	SELECT idCliente,Nome,Idade,Email,DataNascimento 
	FROM Clientes 
	WHERE Nome LIKE '%' +  @Nome + '%' 
END
