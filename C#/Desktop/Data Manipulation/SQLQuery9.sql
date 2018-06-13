USE [LojaCerta]
GO
/****** Object:  StoredProcedure [dbo].[Produtos_select]    Script Date: 09/17/2010 11:48:20 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER PROCEDURE [dbo].[Produtos_select]
@idProduto int
AS
BEGIN
	select NomeProduto,Valor
	from Produtos
	where idProduto = @idProduto
	
END
