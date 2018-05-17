USE [Tosco]
GO

/****** Object:  StoredProcedure [dbo].[ExcluirClientes]    Script Date: 10/20/2010 09:01:06 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[ExcluirClientes]
@ID   int

AS
BEGIN
	delete from Clientes where ID = @ID;
END

GO

