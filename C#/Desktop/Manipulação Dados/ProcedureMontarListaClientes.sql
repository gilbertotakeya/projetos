USE [Tosco]
GO

/****** Object:  StoredProcedure [dbo].[MontarListaClientes]    Script Date: 10/20/2010 09:01:25 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[MontarListaClientes] 
AS
BEGIN
	SELECT * from Clientes;
END

GO

