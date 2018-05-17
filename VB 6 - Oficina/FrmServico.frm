VERSION 5.00
Object = "{C932BA88-4374-101B-A56C-00AA003668DC}#1.1#0"; "MSMASK32.OCX"
Object = "{5E9E78A0-531B-11CF-91F6-C2863C385E30}#1.0#0"; "MSFLXGRD.OCX"
Begin VB.Form cadastroservico 
   BackColor       =   &H00000000&
   BorderStyle     =   1  'Fixed Single
   Caption         =   "Cadastro Servico"
   ClientHeight    =   8835
   ClientLeft      =   1605
   ClientTop       =   1725
   ClientWidth     =   10575
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   MDIChild        =   -1  'True
   MinButton       =   0   'False
   ScaleHeight     =   8835
   ScaleWidth      =   10575
   Begin VB.TextBox txtcodservico 
      Height          =   495
      Left            =   480
      TabIndex        =   12
      Top             =   3120
      Visible         =   0   'False
      Width           =   975
   End
   Begin MSFlexGridLib.MSFlexGrid grdConsulta 
      Height          =   4635
      Left            =   150
      TabIndex        =   7
      ToolTipText     =   "Tecla Delete para Excluir"
      Top             =   4050
      Width           =   10305
      _ExtentX        =   18177
      _ExtentY        =   8176
      _Version        =   393216
      BackColorFixed  =   -2147483624
      BackColorBkg    =   0
      AllowUserResizing=   1
   End
   Begin VB.Frame fracar 
      BackColor       =   &H00000000&
      Caption         =   "Inclusão"
      BeginProperty Font 
         Name            =   "Monotype Corsiva"
         Size            =   14.25
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   -1  'True
         Strikethrough   =   0   'False
      EndProperty
      ForeColor       =   &H0000FFFF&
      Height          =   2895
      Left            =   120
      TabIndex        =   0
      Tag             =   "1"
      Top             =   120
      Width           =   10335
      Begin MSMask.MaskEdBox mskdata 
         Height          =   375
         Left            =   2040
         TabIndex        =   13
         Top             =   480
         Width           =   1935
         _ExtentX        =   3413
         _ExtentY        =   661
         _Version        =   393216
         ForeColor       =   -2147483635
         MaxLength       =   10
         BeginProperty Font {0BE35203-8F91-11CE-9DE3-00AA004BB851} 
            Name            =   "Monotype Corsiva"
            Size            =   12
            Charset         =   0
            Weight          =   700
            Underline       =   0   'False
            Italic          =   -1  'True
            Strikethrough   =   0   'False
         EndProperty
         Mask            =   "99/99/9999"
         PromptChar      =   "_"
      End
      Begin VB.TextBox txtdescricao 
         Height          =   1095
         Left            =   2040
         TabIndex        =   10
         Top             =   1560
         Width           =   5055
      End
      Begin VB.TextBox txtvalor 
         BeginProperty Font 
            Name            =   "Monotype Corsiva"
            Size            =   14.25
            Charset         =   0
            Weight          =   400
            Underline       =   0   'False
            Italic          =   -1  'True
            Strikethrough   =   0   'False
         EndProperty
         ForeColor       =   &H8000000D&
         Height          =   375
         Left            =   5160
         TabIndex        =   9
         Top             =   480
         Width           =   4905
      End
      Begin VB.ComboBox cmbCarro 
         Height          =   315
         Left            =   2040
         TabIndex        =   8
         Top             =   1080
         Width           =   7995
      End
      Begin MSFlexGridLib.MSFlexGrid MSFlexGrid1 
         Height          =   30
         Left            =   0
         TabIndex        =   6
         Top             =   2880
         Width           =   9615
         _ExtentX        =   16960
         _ExtentY        =   53
         _Version        =   393216
      End
      Begin VB.CommandButton cmdcan 
         Caption         =   "Cancelar"
         Height          =   615
         Left            =   8895
         TabIndex        =   2
         Top             =   1560
         Width           =   1215
      End
      Begin VB.CommandButton cmdOk 
         BackColor       =   &H8000000D&
         Caption         =   "Incluir"
         Height          =   615
         Left            =   7680
         MaskColor       =   &H00808080&
         TabIndex        =   1
         Top             =   1560
         Width           =   1215
      End
      Begin VB.Label Label3 
         Alignment       =   1  'Right Justify
         BackColor       =   &H80000009&
         BackStyle       =   0  'Transparent
         Caption         =   "Descrição:"
         BeginProperty Font 
            Name            =   "Monotype Corsiva"
            Size            =   14.25
            Charset         =   0
            Weight          =   700
            Underline       =   0   'False
            Italic          =   -1  'True
            Strikethrough   =   0   'False
         EndProperty
         ForeColor       =   &H00FFFFFF&
         Height          =   375
         Left            =   120
         TabIndex        =   11
         Top             =   1920
         Width           =   1815
      End
      Begin VB.Label Label2 
         Alignment       =   2  'Center
         BackColor       =   &H80000009&
         BackStyle       =   0  'Transparent
         Caption         =   "Valor:"
         BeginProperty Font 
            Name            =   "Monotype Corsiva"
            Size            =   14.25
            Charset         =   0
            Weight          =   700
            Underline       =   0   'False
            Italic          =   -1  'True
            Strikethrough   =   0   'False
         EndProperty
         ForeColor       =   &H00FFFFFF&
         Height          =   375
         Left            =   3960
         TabIndex        =   5
         Top             =   480
         Width           =   1335
      End
      Begin VB.Label Label1 
         BackColor       =   &H80000009&
         BackStyle       =   0  'Transparent
         Caption         =   "Codigo Carro:"
         BeginProperty Font 
            Name            =   "Monotype Corsiva"
            Size            =   14.25
            Charset         =   0
            Weight          =   700
            Underline       =   0   'False
            Italic          =   -1  'True
            Strikethrough   =   0   'False
         EndProperty
         ForeColor       =   &H00FFFFFF&
         Height          =   375
         Left            =   120
         TabIndex        =   4
         Top             =   1080
         Width           =   1815
      End
      Begin VB.Label lblMarca 
         Alignment       =   1  'Right Justify
         BackColor       =   &H80000009&
         BackStyle       =   0  'Transparent
         Caption         =   "Data:"
         BeginProperty Font 
            Name            =   "Monotype Corsiva"
            Size            =   14.25
            Charset         =   0
            Weight          =   700
            Underline       =   0   'False
            Italic          =   -1  'True
            Strikethrough   =   0   'False
         EndProperty
         ForeColor       =   &H00FFFFFF&
         Height          =   375
         Left            =   120
         TabIndex        =   3
         Top             =   480
         Width           =   1815
      End
   End
End
Attribute VB_Name = "cadastroservico"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Dim preInsert As rdoQuery
Dim preDelete As rdoQuery
Dim preUpdate As rdoQuery
Dim sql As String

Private Sub cmdcan_Click()
  ZerarDados
End Sub

Private Function validadados() As Boolean
  validadados = False
  
  If Not IsDate(mskdata.Text) Then
     MsgBox "É necessario informar o numero da placa!!!", vbInformation
     mskdata.SetFocus
     Exit Function
  End If
  
  If Not IsNumeric(txtvalor.Text) Then
     MsgBox "É necessario informar o numero da placa!!!", vbInformation
     txtvalor.SetFocus
     Exit Function
  End If
 
  If cmbCarro.ListIndex = -1 Then
     MsgBox "É necessario informar o codigo do cliente!!!", vbInformation
     cmbCarro.SetFocus
     Exit Function
  End If
  
  validadados = True
  
End Function

Private Sub cmdOk_Click()
 Dim Normal As Boolean
   Normal = False
   If validadados Then
      If fracar.Tag = 1 Then
         Normal = Incluir
      ElseIf fracar.Tag = 2 Then
        Normal = Alterar
      End If
      If Normal Then
         InicializarGrid
         ZerarDados
         MontarGrid
      End If
   End If
End Sub

Private Sub Form_Load()
  InicializarGrid
  MontarGrid
  CarregaQuery
End Sub
Private Sub CarregaQuery()
   sql = "INSERT INTO SERVICO(DATA,VALOR,DESCRICAO,CODIGOCARRO)"
   sql = sql & " VALUES(?,?,?,?)"
   '---------
   Set preInsert = Banco.CreateQuery("", sql)
   '-----
   sql = "update SERVICO set DATA=?,VALOR=?,DESCRICAO=?,CODIGOCARRO=?"
   sql = sql & " where CODIGOSERVICO=?"
   Set preUpdate = Banco.CreateQuery("", sql)
   
   sql = "DELETE FROM SERVICO"
   sql = sql & " WHERE CODIGOSERVICO=?"
   '-----------------------
   Set preDelete = Banco.CreateQuery("", sql)
   '----------
   sql = "select CODIGOCARRO,NOMECARRO ||'-'|| CODIGOCARRO"
   sql = sql & " from CARRO "
   sql = sql & " order by NOMECARRO"
   CarregaCombo sql, cmbCarro
End Sub

Private Function Incluir() As Boolean
On Error GoTo Erro
 preInsert(0) = mskdata.Text
 preInsert(1) = txtvalor.Text
 preInsert(2) = txtdescricao.Text
 preInsert(3) = cmbCarro.ItemData(cmbCarro.ListIndex)
 preInsert.Execute
 
 Incluir = preInsert.RowsAffected > 0
 
 If Incluir Then
    MsgBox "Incluiu!!", vbInformation, "Atenção"
 Else
    MsgBox "Não Incluiu!!", vbInformation, "Atenção"
 End If
 
 Exit Function
Erro:
  MsgBox "erro na inclusão!! " & Chr(13) & Err.Number & "-> " & Err.Description, vbCritical, "Erro"
End Function

Private Sub Form_Unload(Cancel As Integer)
On Error Resume Next
 preInsert.Close
 preDelete.Close
 preUpdate.Close
 
End Sub
Private Sub InicializarGrid()
With grdConsulta
  .Clear
  .Rows = 2: .Cols = 6 'quantidade linhas e colunas do grid
  .FixedRows = 1 'quantidade de linhas
  .FixedCols = 0 'quantidade colunas
  .FixedAlignment(0) = flexAlignCenterCenter
  .ColAlignment(0) = flexAlignCenterCenter
  .TextMatrix(0, 0) = "Codigo Servico"
  .ColWidth(0) = 0
  
  .FixedAlignment(1) = flexAlignCenterCenter
  .ColAlignment(1) = flexAlignLeftCenter
  .TextMatrix(0, 1) = "Data"
  .ColWidth(1) = 2000
  
  .FixedAlignment(2) = flexAlignCenterCenter
  .ColAlignment(2) = flexAlignCenterCenter
  .TextMatrix(0, 2) = "Valor"
  .ColWidth(2) = 1200
  
  
  .FixedAlignment(3) = flexAlignCenterCenter
  .ColAlignment(3) = flexAlignCenterCenter
  .TextMatrix(0, 3) = "Descrição"
  .ColWidth(3) = 0
  
  .FixedAlignment(4) = flexAlignCenterCenter
  .ColAlignment(4) = flexAlignLeftCenter
  .TextMatrix(0, 4) = "Nome Carro"
  .ColWidth(4) = 4300
  .Col = 0
  
  .FixedAlignment(5) = flexAlignCenterCenter
  .ColAlignment(5) = flexAlignLeftCenter
  .TextMatrix(0, 5) = "Codigo Carro"
  .ColWidth(5) = 4300
  .Col = 0
  End With
  
End Sub

Private Sub MontarGrid()
On Error GoTo Erro
   
   Dim tbl As rdoResultset
   Dim Linha As Integer
   
   sql = "select A.CODIGOSERVICO,A.DATA,A.VALOR,A.DESCRICAO,B.NOMECARRO,A.CODIGOCARRO" & Chr(13)
   sql = sql & " from SERVICO A ,CARRO B " & Chr(13)
   sql = sql & "where A.CODIGOCARRO =B.CODIGOCARRO" & Chr(13)
   sql = sql & "order by A.CODIGOSERVICO"
   '-----------
   Set tbl = Banco.OpenResultset(sql, rdOpenStatic)
   
   InicializarGrid
   
   If Not tbl.EOF Then
   
    tbl.MoveLast
    tbl.MoveFirst
      With grdConsulta
       If tbl.RowCount > .Rows - 1 Then
        .Rows = tbl.RowCount + 1
       End If
       
       Linha = 1
       
       Do Until tbl.EOF
       
          .RowData(Linha) = Linha
          
         .TextMatrix(Linha, 0) = tbl!CODIGOSERVICO
         .TextMatrix(Linha, 1) = tbl!Data
         .TextMatrix(Linha, 2) = tbl!valor
         .TextMatrix(Linha, 3) = tbl!DESCRICAO
         .TextMatrix(Linha, 4) = tbl!NOMECARRO
         .TextMatrix(Linha, 5) = tbl!CODIGOCARRO
         
         tbl.MoveNext
         Linha = Linha + 1
         Loop
         
         .Row = 1
         .Col = 0
      End With
     End If
Exit Sub
'----------
Erro:
 MsgBox "Erro ao carregar o grid !!!" & Chr(13) & Err.Number & "->" & Err.Description, vbCritical, "Erro"
 
End Sub
Private Function Alterar() As Boolean
 On Error GoTo Erro
 
 preUpdate(0) = mskdata.Text
 preUpdate(1) = txtvalor.Text
 preUpdate(2) = txtdescricao.Text
 preUpdate(3) = cmbCarro.ItemData(cmbCarro.ListIndex)
 '-------------
 preUpdate(4) = txtcodservico.Text
  
 preUpdate.Execute
 If preUpdate.RowsAffected = 0 Then
   MsgBox "Não foi possivel realizar a alteração!!!!!!!!!!!!!", vbInformation
   Else
   Alterar = True
  End If
 Exit Function
Erro:
 MsgBox " Erro na alteração!!" & Chr(13) & Err.Number & " ->" & Err.Description, vbCritical, "Erro"
 
End Function
Private Sub grdConsulta_DblClick()
 With grdConsulta
  If .TextMatrix(.Row, 0) <> "" Then
   fracar.Caption = "Consulta/Alteração"
   fracar.Tag = 2
   cmdOk.Caption = "Alterar"
   recuperardadosgrid
  End If
 End With
 
End Sub

Private Sub grdConsulta_KeyDown(KeyCode As Integer, Shift As Integer)
 'tecla delete
 If KeyCode = 46 And grdConsulta.TextMatrix(grdConsulta.Row, 1) <> "" Then
  Excluir
 End If
 
End Sub
Private Sub recuperardadosgrid()
With grdConsulta

 txtcodservico.Text = .TextMatrix(.Row, 0)
 mskdata.Text = .TextMatrix(.Row, 1)
 txtvalor.Text = .TextMatrix(.Row, 2)
 txtdescricao.Text = .TextMatrix(.Row, 3)
 selecionaCombo cmbCarro, .TextMatrix(.Row, 5)
 
 txtcodservico.Enabled = False
End With
 mskdata.SetFocus
 
End Sub

Private Sub Excluir()
On Error GoTo Erro
  fracar.Caption = "Exclusão"
  fracar.Tag = 3
  recuperardadosgrid
   If MsgBox("Tem certeza que deseja excluir essa informação ?", vbQuestion + vbYesNo + vbDefaultButton2) = vbNo Then
   ZerarDados
   Exit Sub
   End If
  preDelete(0) = txtcodservico.Text
  preDelete.Execute
    If preDelete.RowsAffected = 0 Then
        MsgBox "Não foi possivel realizar a exclusão!!!", vbInformation
    End If
   ZerarDados
   MontarGrid
   Exit Sub
   '-----------
Erro:
   MsgBox "Erro na Exclusão!!" & Chr(13) & Err.Number & "->" & Err.Description, vbCritical, "Erro"
   
End Sub
Private Sub ZerarDados()
 txtcodservico.Text = ""
 mskdata.Text = "  /  /    "
 txtvalor.Text = ""
 txtdescricao.Text = ""
 cmbCarro.ListIndex = -1
 txtcodservico.Enabled = False
 cmdOk.Caption = "Incluir"
 fracar.Caption = "Inclusão"
 fracar.Tag = "1"
End Sub
