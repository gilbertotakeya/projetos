USE [LojaCerta]
GO
/****** Object:  StoredProcedure [dbo].[GerarItemPedido]    Script Date: 09/17/2010 11:47:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[GerarItemPedido]
	@idPedido int,
	@idProduto int,
	@qtde numeric(5,2),
	@valorItem numeric(5,2)
AS
BEGIN
	INSERT INTO PedidosDetalhe(idPedido,idProduto,Qtde,ValorTotalItem)
	VALUES(@idPedido,@idProduto,@qtde,@valorItem)		
END