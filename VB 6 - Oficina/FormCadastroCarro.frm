VERSION 5.00
Object = "{5E9E78A0-531B-11CF-91F6-C2863C385E30}#1.0#0"; "MSFLXGRD.OCX"
Begin VB.Form cadastrocarro 
   BackColor       =   &H00000000&
   BorderStyle     =   1  'Fixed Single
   Caption         =   "Cadastro Carro"
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
   Begin MSFlexGridLib.MSFlexGrid grdConsulta 
      Height          =   6195
      Left            =   120
      TabIndex        =   7
      ToolTipText     =   "Tecla Delete para Excluir"
      Top             =   2490
      Width           =   10305
      _ExtentX        =   18177
      _ExtentY        =   10927
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
      Height          =   2415
      Left            =   120
      TabIndex        =   0
      Tag             =   "1"
      Top             =   0
      Width           =   10335
      Begin VB.TextBox txtmarca 
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
         Left            =   2040
         TabIndex        =   11
         Top             =   480
         Width           =   1575
      End
      Begin VB.TextBox txtncarro 
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
         Left            =   5190
         TabIndex        =   10
         Top             =   480
         Width           =   4905
      End
      Begin VB.ComboBox cmbCliente 
         Height          =   315
         Left            =   2040
         TabIndex        =   9
         Top             =   1080
         Width           =   7995
      End
      Begin VB.TextBox txtcodigocarro 
         Height          =   315
         Left            =   480
         TabIndex        =   8
         Top             =   1800
         Visible         =   0   'False
         Width           =   1815
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
      Begin VB.Label Label2 
         BackColor       =   &H80000009&
         BackStyle       =   0  'Transparent
         Caption         =   "Modelo:"
         BeginProperty Font 
            Name            =   "MS Sans Serif"
            Size            =   12
            Charset         =   0
            Weight          =   700
            Underline       =   0   'False
            Italic          =   0   'False
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
         Caption         =   "Codigo Cliente:"
         BeginProperty Font 
            Name            =   "MS Sans Serif"
            Size            =   12
            Charset         =   0
            Weight          =   700
            Underline       =   0   'False
            Italic          =   0   'False
            Strikethrough   =   0   'False
         EndProperty
         ForeColor       =   &H00FFFFFF&
         Height          =   375
         Left            =   120
         TabIndex        =   4
         Top             =   1080
         Width           =   2175
      End
      Begin VB.Label lblMarca 
         BackColor       =   &H80000009&
         BackStyle       =   0  'Transparent
         Caption         =   "Marca:"
         BeginProperty Font 
            Name            =   "MS Sans Serif"
            Size            =   12
            Charset         =   0
            Weight          =   700
            Underline       =   0   'False
            Italic          =   0   'False
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
Attribute VB_Name = "cadastrocarro"
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
  
  If Trim(txtmarca.Text) = "" Then
     MsgBox "É necessario informar a marca do carro!!!", vbInformation
     txtmarca.SetFocus
     Exit Function
  End If
    
  If Trim(txtncarro.Text) = "" Then
     MsgBox "É necessario informar o modelo do carro!!!", vbInformation
     txtncarro.SetFocus
     Exit Function
  End If
  
  If cmbCliente.ListIndex = -1 Then
     MsgBox "É necessario informar o codigo do cliente!!!", vbInformation
     cmbCliente.SetFocus
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
  Set Banco = rdoEnvironments(0).OpenConnection("", rdDriverNoPrompt, False, "DSN=BancoOficina;UID=SYSDBA;PWD=masterkey")
  InicializarGrid
  MontarGrid
  CarregaQuery
End Sub
Private Sub CarregaQuery()
   sql = "INSERT INTO CARRO (NOMECARRO,CODIGOCLIENTE,MARCA)"
   sql = sql & " VALUES(?,?,?)"
   '---------
   Set preInsert = Banco.CreateQuery("", sql)
   '-----
   sql = "update CARRO set NOMECARRO=?,CODIGOCLIENTE=?,MARCA=?"
   sql = sql & " where CODIGOCARRO=?"
   Set preUpdate = Banco.CreateQuery("", sql)
   
   sql = "DELETE FROM CARRO"
   sql = sql & " WHERE CODIGOCARRO=?"
   '-----------------------
   Set preDelete = Banco.CreateQuery("", sql)
   '----------
   sql = "select CODIGOCLIENTE,NOMECLIENTE ||'-'|| CODIGOCLIENTE"
   sql = sql & " from CLIENTE "
   sql = sql & " order by NOMECLIENTE"
   CarregaCombo sql, cmbCliente
End Sub

Private Function Incluir() As Boolean
On Error GoTo Erro
 preInsert(0) = txtncarro.Text
 preInsert(1) = cmbCliente.ItemData(cmbCliente.ListIndex)
 preInsert(2) = txtmarca.Text
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
  .Rows = 2: .Cols = 5 'quantidade linhas e colunas do grid
  .FixedRows = 1 'quantidade de linhas
  .FixedCols = 0 'quantidade colunas
  .FixedAlignment(0) = flexAlignCenterCenter
  .ColAlignment(0) = flexAlignCenterCenter
  .TextMatrix(0, 0) = "Codigo Carro"
  .ColWidth(0) = 1000
  
  .FixedAlignment(1) = flexAlignCenterCenter
  .ColAlignment(1) = flexAlignLeftCenter
  .TextMatrix(0, 1) = "Marca"
  .ColWidth(1) = 2000
  
  .FixedAlignment(2) = flexAlignCenterCenter
  .ColAlignment(2) = flexAlignCenterCenter
  .TextMatrix(0, 2) = "Codigo Cliente"
  .ColWidth(2) = 1400
  
  
  .FixedAlignment(3) = flexAlignCenterCenter
  .ColAlignment(3) = flexAlignCenterCenter
  .TextMatrix(0, 3) = "Modelo Carro"
  .ColWidth(3) = 1500
  
  .FixedAlignment(4) = flexAlignCenterCenter
  .ColAlignment(4) = flexAlignLeftCenter
  .TextMatrix(0, 4) = "Nome Cliente"
  .ColWidth(4) = 4300
  .Col = 0
  End With
  
End Sub

Private Sub MontarGrid()
On Error GoTo Erro
   
   Dim tbl As rdoResultset
   Dim Linha As Integer
   
   sql = "select A.CODIGOCARRO,A.NOMECARRO,A.CODIGOCLIENTE,A.MARCA,B.NOMECLIENTE" & Chr(13)
   sql = sql & " from CARRO A ,CLIENTE B " & Chr(13)
   sql = sql & "where A.CODIGOCLIENTE = B.CODIGOCLIENTE" & Chr(13)
   sql = sql & "order by A.CODIGOCARRO"
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
          
         .TextMatrix(Linha, 0) = tbl!CODIGOCARRO
         .TextMatrix(Linha, 1) = tbl!NOMECARRO
         .TextMatrix(Linha, 2) = tbl!CODIGOCLIENTE
         .TextMatrix(Linha, 3) = tbl!MARCA
         .TextMatrix(Linha, 4) = tbl!NOMECLIENTE
         
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
  preUpdate(0) = txtncarro.Text
  preUpdate(1) = cmbCliente.ItemData(cmbCliente.ListIndex)
  preUpdate(2) = txtmarca.Text
  '-------------
  preUpdate(3) = txtcodigocarro.Text
  
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
 txtcodigocarro.Text = .TextMatrix(.Row, 0)
 txtncarro.Text = .TextMatrix(.Row, 1)
 txtmarca.Text = .TextMatrix(.Row, 3)
 selecionaCombo cmbCliente, .TextMatrix(.Row, 2)
 txtcodigocarro.Enabled = False
End With
 txtmarca.SetFocus
 
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
  preDelete(0) = txtcodigocarro.Text
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
 txtcodigocarro.Text = ""
 txtmarca.Text = ""
 txtncarro.Text = ""
 cmbCliente.ListIndex = -1
 txtcodigocarro.Enabled = False
 cmdOk.Caption = "Incluir"
 fracar.Caption = "Inclusão"
End Sub

