USE [Tosco]
GO

/****** Object:  StoredProcedure [dbo].[BuscarClientePorNome]    Script Date: 10/20/2010 09:00:42 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[BuscarClientePorNome]

@Nome varchar(50)

AS
BEGIN

	select * from Clientes where Nome like '%' + @Nome +  '%';  

END

GO

