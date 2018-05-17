USE [Tosco]
GO

/****** Object:  StoredProcedure [dbo].[AlterarClientes]    Script Date: 10/20/2010 09:00:29 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[AlterarClientes]
@ID   int,
@Nome varchar(50),
@CPF varchar(50)

AS
BEGIN
	Update Clientes set Nome = @Nome, CPF = @CPF where ID = @ID;
END

GO

