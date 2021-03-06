USE [LojaCerta]
GO
/****** Object:  StoredProcedure [dbo].[ListarItensPedidos]    Script Date: 09/17/2010 11:48:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER procedure [dbo].[ListarItensPedidos]
@idPedido int
as
begin
	select P.NomeProduto,pd.Qtde,P.Valor,pd.ValorTotalItem
	from PedidosDetalhe pd
	inner join Produtos P ON P.idProduto = pd.idProduto
	where pd.idPedido = @idPedido
END