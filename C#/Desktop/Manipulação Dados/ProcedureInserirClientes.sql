USE [Tosco]
GO

/****** Object:  StoredProcedure [dbo].[InserirClientes]    Script Date: 10/20/2010 09:01:16 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[InserirClientes]
@Nome varchar(50),
@CPF varchar(50)

AS
BEGIN

	insert into Clientes( Nome, CPF ) values( @Nome, @CPF )

END

GO

