USE [LojaCerta]
GO
/****** Object:  StoredProcedure [dbo].[Clientes_Insert]    Script Date: 09/17/2010 11:47:51 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER PROCEDURE [dbo].[Clientes_Insert]
@Nome varchar(100),
@Idade int,
@Email varchar(200),
@DataNascimento smalldatetime

AS
BEGIN
	INSERT INTO Clientes (Nome,Idade,Email,DataNascimento)
	VALUES(@Nome,@Idade,@Email,@DataNascimento)
END
