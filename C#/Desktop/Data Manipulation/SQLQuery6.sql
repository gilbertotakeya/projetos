USE [LojaCerta]
GO
/****** Object:  StoredProcedure [dbo].[GerarPedido]    Script Date: 09/17/2010 11:48:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER PROCEDURE [dbo].[GerarPedido]
@idCliente int
as
BEGIN
	INSERT INTO Pedidos(DataPedido,idCliente)
	VALUES (GETDATE(),@idCliente)
	
	SELECT @@IDENTITY as IdPedido

END
	



--CREATE PROCEDURE [dbo].GerarPedido
--@idCliente int,
--@idProduto int,
--@qtde numeric(5,2),
--@valorItem numeric(5,2)
--AS
--BEGIN
		
--END
