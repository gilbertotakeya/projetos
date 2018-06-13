USE [LojaCerta]
GO
/****** Object:  StoredProcedure [dbo].[ListarPedidos]    Script Date: 09/17/2010 11:48:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER procedure [dbo].[ListarPedidos]
as
begin
	select p.idPedido,p.DataPedido,C.Nome
	from Pedidos p
	INNER JOIN Clientes C ON C.idCliente = P.idCliente
end
