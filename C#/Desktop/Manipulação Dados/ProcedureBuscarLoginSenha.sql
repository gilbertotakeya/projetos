USE [Tosco]
GO

/****** Object:  StoredProcedure [dbo].[BuscarLoginSenha]    Script Date: 10/20/2010 09:00:56 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[BuscarLoginSenha]
@Login varchar(50)
AS
BEGIN
 select * from dbo.Usuarios_Sistema where dbo.Usuarios_Sistema.Login = @Login; 
END

GO

