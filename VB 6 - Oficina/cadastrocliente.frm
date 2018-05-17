VERSION 5.00
Object = "{C932BA88-4374-101B-A56C-00AA003668DC}#1.1#0"; "MSMASK32.OCX"
Object = "{5E9E78A0-531B-11CF-91F6-C2863C385E30}#1.0#0"; "MSFLXGRD.OCX"
Begin VB.Form cadastrocliente 
   BackColor       =   &H80000007&
   BorderStyle     =   1  'Fixed Single
   Caption         =   "Cadastro Cliente"
   ClientHeight    =   8610
   ClientLeft      =   1950
   ClientTop       =   2025
   ClientWidth     =   9855
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   MDIChild        =   -1  'True
   MinButton       =   0   'False
   ScaleHeight     =   8610
   ScaleWidth      =   9855
   Begin MSFlexGridLib.MSFlexGrid grdConsulta 
      Height          =   4335
      Left            =   30
      TabIndex        =   23
      ToolTipText     =   "Tecla Delete para Excluir"
      Top             =   4140
      Width           =   9735
      _ExtentX        =   17171
      _ExtentY        =   7646
      _Version        =   393216
      BackColorBkg    =   0
   End
   Begin VB.TextBox txtcidade 
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
      MaxLength       =   30
      TabIndex        =   7
      Top             =   1920
      Width           =   3255
   End
   Begin VB.TextBox txtbairro 
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
      MaxLength       =   50
      TabIndex        =   5
      Top             =   1440
      Width           =   3255
   End
   Begin VB.TextBox txtendereco 
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
      MaxLength       =   50
      TabIndex        =   3
      Top             =   960
      Width           =   3255
   End
   Begin VB.Frame fracli 
      BackColor       =   &H00000000&
      Caption         =   "Cadastro Cliente"
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
      Height          =   4095
      Left            =   0
      TabIndex        =   0
      Tag             =   "1"
      Top             =   0
      Width           =   9735
      Begin VB.TextBox txtcodigocliente 
         Height          =   585
         Left            =   1020
         TabIndex        =   24
         Top             =   3150
         Visible         =   0   'False
         Width           =   1155
      End
      Begin VB.TextBox txtcep 
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
         Left            =   6840
         TabIndex        =   10
         Top             =   2400
         Width           =   2415
      End
      Begin VB.TextBox txtcelular 
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
         TabIndex        =   9
         Top             =   2400
         Width           =   2415
      End
      Begin VB.TextBox txttelefone 
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
         Left            =   6840
         TabIndex        =   8
         Top             =   1920
         Width           =   2415
      End
      Begin MSMask.MaskEdBox mskcpf 
         Height          =   375
         Left            =   6240
         TabIndex        =   4
         Top             =   960
         Width           =   1575
         _ExtentX        =   2778
         _ExtentY        =   661
         _Version        =   393216
         ForeColor       =   -2147483635
         MaxLength       =   13
         BeginProperty Font {0BE35203-8F91-11CE-9DE3-00AA004BB851} 
            Name            =   "Monotype Corsiva"
            Size            =   12.75
            Charset         =   0
            Weight          =   400
            Underline       =   0   'False
            Italic          =   -1  'True
            Strikethrough   =   0   'False
         EndProperty
         Mask            =   "999.999.999-9"
         PromptChar      =   "_"
      End
      Begin MSMask.MaskEdBox mskrg 
         Height          =   375
         Left            =   6240
         TabIndex        =   2
         Top             =   480
         Width           =   1575
         _ExtentX        =   2778
         _ExtentY        =   661
         _Version        =   393216
         ForeColor       =   -2147483635
         MaxLength       =   12
         BeginProperty Font {0BE35203-8F91-11CE-9DE3-00AA004BB851} 
            Name            =   "Monotype Corsiva"
            Size            =   12.75
            Charset         =   0
            Weight          =   400
            Underline       =   0   'False
            Italic          =   -1  'True
            Strikethrough   =   0   'False
         EndProperty
         Mask            =   "99.999.999-9"
         PromptChar      =   "_"
      End
      Begin MSMask.MaskEdBox mskdatanasc 
         Height          =   375
         Left            =   8160
         TabIndex        =   6
         Top             =   1440
         Width           =   1335
         _ExtentX        =   2355
         _ExtentY        =   661
         _Version        =   393216
         ForeColor       =   -2147483635
         MaxLength       =   10
         BeginProperty Font {0BE35203-8F91-11CE-9DE3-00AA004BB851} 
            Name            =   "Monotype Corsiva"
            Size            =   12.75
            Charset         =   0
            Weight          =   400
            Underline       =   0   'False
            Italic          =   -1  'True
            Strikethrough   =   0   'False
         EndProperty
         Mask            =   "99/99/9999"
         PromptChar      =   "_"
      End
      Begin VB.TextBox txtncliente 
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
         MaxLength       =   50
         TabIndex        =   1
         Top             =   480
         Width           =   3255
      End
      Begin VB.CommandButton cmdOk 
         BackColor       =   &H8000000D&
         Caption         =   "Incluir"
         Height          =   615
         Left            =   3480
         MaskColor       =   &H00808080&
         TabIndex        =   11
         Top             =   3120
         Width           =   1215
      End
      Begin VB.CommandButton cmdcan 
         Caption         =   "Cancelar"
         Height          =   615
         Left            =   4800
         TabIndex        =   12
         Top             =   3120
         Width           =   1335
      End
      Begin VB.Label Label9 
         AutoSize        =   -1  'True
         BackColor       =   &H00000000&
         Caption         =   "Cep:"
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
         Height          =   330
         Left            =   5520
         TabIndex        =   22
         Top             =   2400
         Width           =   510
      End
      Begin VB.Label Label8 
         AutoSize        =   -1  'True
         BackColor       =   &H00000000&
         Caption         =   "Celular:"
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
         Height          =   330
         Left            =   120
         TabIndex        =   21
         Top             =   2400
         Width           =   945
      End
      Begin VB.Label Label7 
         AutoSize        =   -1  'True
         BackColor       =   &H00000000&
         Caption         =   "Telefone:"
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
         Height          =   330
         Left            =   5520
         TabIndex        =   20
         Top             =   1920
         Width           =   1080
      End
      Begin VB.Label Label6 
         AutoSize        =   -1  'True
         BackColor       =   &H00000000&
         Caption         =   "Cidade:"
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
         Height          =   330
         Left            =   120
         TabIndex        =   19
         Top             =   1920
         Width           =   870
      End
      Begin VB.Label Label5 
         AutoSize        =   -1  'True
         BackColor       =   &H00000000&
         Caption         =   "Bairro:"
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
         Height          =   330
         Left            =   120
         TabIndex        =   18
         Top             =   1440
         Width           =   840
      End
      Begin VB.Label Label4 
         AutoSize        =   -1  'True
         BackColor       =   &H00000000&
         Caption         =   "Endereço:"
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
         Height          =   330
         Left            =   120
         TabIndex        =   17
         Top             =   960
         Width           =   1170
      End
      Begin VB.Label Label3 
         AutoSize        =   -1  'True
         BackColor       =   &H00000000&
         Caption         =   "Cpf:"
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
         Height          =   330
         Left            =   5520
         TabIndex        =   16
         Top             =   960
         Width           =   480
      End
      Begin VB.Label Label1 
         AutoSize        =   -1  'True
         BackColor       =   &H00000000&
         Caption         =   "Rg:"
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
         Height          =   330
         Left            =   5520
         TabIndex        =   15
         Top             =   480
         Width           =   405
      End
      Begin VB.Label lblCliente 
         AutoSize        =   -1  'True
         BackColor       =   &H00000000&
         Caption         =   "Nome Cliente:"
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
         Height          =   330
         Left            =   120
         TabIndex        =   14
         Top             =   480
         Width           =   1665
      End
      Begin VB.Label Label2 
         AutoSize        =   -1  'True
         BackColor       =   &H00000000&
         Caption         =   "Data de Nascimento:"
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
         Height          =   330
         Left            =   5520
         TabIndex        =   13
         Top             =   1440
         Width           =   2445
      End
   End
End
Attribute VB_Name = "cadastrocliente"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Dim preInsert As rdoQuery
Dim preDelete As rdoQuery
Dim preUpdate As rdoQuery
Dim sql As String
Private Sub Excluir()
On Error GoTo Erro
  fracli.Caption = "Exclusão"
  fracli.Tag = 3
  recuperardadosgrid
   If MsgBox("Tem certeza que deseja excluir essa informação ?", vbQuestion + vbYesNo + vbDefaultButton2) = vbNo Then
   ZerarDados
   Exit Sub
   End If
  preDelete(0) = txtcodigocliente.Text
  preDelete.Execute
    If preDelete.RowsAffected = 0 Then
        MsgBox "Não foi possivel realizar a exclusão!!!", vbInformation
    End If
      
   ZerarDados
   MontarGrid
   Exit Sub
   '-----------
Erro:
   TratarErro
End Sub
Private Function Alterar() As Boolean
 On Error GoTo Erro

preUpdate(0) = txtncliente.Text
preUpdate(1) = mskrg.Text
preUpdate(2) = mskcpf.Text
preUpdate(3) = mskdatanasc.Text
preUpdate(4) = txtendereco.Text
preUpdate(5) = txtcep.Text
preUpdate(6) = txtbairro.Text
preUpdate(7) = txtcidade.Text
preUpdate(8) = txttelefone.Text
preUpdate(9) = txtcelular.Text
preUpdate(10) = txtcodigocliente.Text

  
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
Private Sub cmdcan_Click()
  ZerarDados
End Sub
Private Sub ZerarDados()
txtncliente.Text = ""

txtendereco.Text = ""
txtcep.Text = ""
txtbairro.Text = ""
txtcidade.Text = ""
txttelefone.Text = ""
txtcelular.Text = ""
txtcodigocliente.Text = ""
mskrg.Text = "  .   .   - "
mskcpf.Text = "   .   .   - "
mskdatanasc.Text = "  /  /    "
txtcodigocliente.Enabled = False
cmdOk.Caption = "Incluir"
fracli.Caption = "Inclusão"

End Sub
Private Function validadados() As Boolean
  validadados = False
  If Trim(txtncliente.Text) = "" Then
     MsgBox "É necessario informar o nome do cliente!!!", vbInformation
     txtncliente.SetFocus
     Exit Function
  End If
    
  If Trim(txtendereco.Text) = "" Then
     MsgBox "É necessario informar o endereço!!!", vbInformation
     txtendereco.SetFocus
     Exit Function
  End If
  If Trim(txtbairro.Text) = "" Then
     MsgBox "É necessario informar o nome do bairro!!!", vbInformation
     txtbairro.SetFocus
     Exit Function
  End If
  If Trim(txtcidade.Text) = "" Then
     MsgBox "É necessario informar o nome da cidade!!!", vbInformation
     txtcidade.SetFocus
     Exit Function
  End If
  If Not IsNumeric(txtcelular.Text) Then
     MsgBox "É necessario informar o numero do celular!!!", vbInformation
     txtcelular.SetFocus
     Exit Function
  End If
    If Not IsNumeric(txttelefone.Text) Then
     MsgBox "É necessario informar o numero do telefone!!!", vbInformation
     txttelefone.SetFocus
     Exit Function
  End If
  If Not IsNumeric(txtcep.Text) Then
     MsgBox "É necessario informar o numero do cep!!!", vbInformation
     txtcep.SetFocus
     Exit Function
  End If

  
  validadados = True
  
End Function

Private Sub cmdOk_Click()
 Dim Normal As Boolean
   Normal = False
   If validadados Then
      If fracli.Tag = 1 Then
         Normal = Incluir
      ElseIf fracli.Tag = 2 Then
        Normal = Alterar
      End If
      
      If Normal Then
         ZerarDados
         MontarGrid
      End If
   End If
End Sub

Private Sub Form_Load()
   CarregaQuery
   MontarGrid
 End Sub
Private Sub CarregaQuery()
   sql = "INSERT INTO CLIENTE (NOMECLIENTE,RG,CPF,DATANASCIMENTO,ENDERECO,CEP,BAIRRO,CIDADE,TELEFONE,CELULAR)"
   sql = sql & " VALUES(?,?,?,?,?,?,?,?,?,?)"
   '---------
   Set preInsert = Banco.CreateQuery("", sql)
   
   sql = "update CLIENTE set NOMECLIENTE=?,RG=?,CPF=?,DATANASCIMENTO=?,ENDERECO=?,CEP=?,BAIRRO=?,CIDADE=?,TELEFONE=?,CELULAR=?"
   sql = sql & " where CODIGOCLIENTE=?"
   Set preUpdate = Banco.CreateQuery("", sql)

   sql = "DELETE FROM CLIENTE"
   sql = sql & " WHERE CODIGOCLIENTE=?"
   '-----------------------
   Set preDelete = Banco.CreateQuery("", sql)
   '----------

End Sub
Private Function Incluir() As Boolean
On Error GoTo Erro
 preInsert(0) = txtncliente.Text
 preInsert(1) = mskrg.Text
 preInsert(2) = mskcpf.Text
 preInsert(3) = mskdatanasc.Text
 preInsert(4) = txtendereco.Text
 preInsert(5) = txtcep.Text
 preInsert(6) = txtbairro.Text
 preInsert(7) = txtcidade.Text
 preInsert(8) = txttelefone.Text
 preInsert(9) = txtcelular.Text
 preInsert.Execute
 
 Incluir = preInsert.RowsAffected > 0
 
 If Incluir Then
    MsgBox "Incluiu!!", vbInformation, "Atenção"
 Else
    MsgBox "Não Incluiu!!", vbInformation, "Atenção"
 End If
 
 Exit Function
Erro:
 TratarErro
End Function

Private Sub Form_Unload(Cancel As Integer)
On Error Resume Next
 preInsert.Close
 preDelete.Close
 preUpdate.Close
 
End Sub
Private Sub InicializaGrid()
With grdConsulta
 .Clear
 .Rows = 2: .Cols = 11 'Quantidade de linhas e colunas do grid
    .FixedRows = 1
    .FixedCols = 0
        
        .FixedAlignment(0) = flexAlignCenterCenter
        .ColAlignment(0) = flexAlignCenterCenter
        .TextMatrix(0, 0) = "Codigo Cliente"
        .ColWidth(0) = 1200
        
        .FixedAlignment(1) = flexAlignCenterCenter
        .ColAlignment(1) = flexAlignCenterCenter
        .TextMatrix(0, 1) = "Nome Cliente"
        .ColWidth(1) = 1600
        
        .FixedAlignment(2) = flexAlignCenterCenter
        .ColAlignment(2) = flexAlignCenterCenter
        .TextMatrix(0, 2) = "Rg"
        .ColWidth(2) = 2000
        
        .FixedAlignment(3) = flexAlignCenterCenter
        .ColAlignment(3) = flexAlignCenterCenter
        .TextMatrix(0, 3) = "Cpf"
        .ColWidth(3) = 2000
        
        .FixedAlignment(4) = flexAlignCenterCenter
        .ColAlignment(4) = flexAlignCenterCenter
        .TextMatrix(0, 4) = "Data Nascimento"
        .ColWidth(4) = 1500
        
        .FixedAlignment(5) = flexAlignCenterCenter
        .ColAlignment(5) = flexAlignCenterCenter
        .TextMatrix(0, 5) = "Endereço"
        .ColWidth(5) = 0
        
        .FixedAlignment(6) = flexAlignCenterCenter
        .ColAlignment(6) = flexAlignCenterCenter
        .TextMatrix(0, 6) = "Cep"
        .ColWidth(6) = 0
        
        .FixedAlignment(7) = flexAlignCenterCenter
        .ColAlignment(7) = flexAlignCenterCenter
        .TextMatrix(0, 7) = "Bairro"
        .ColWidth(7) = 0
        
        .FixedAlignment(8) = flexAlignCenterCenter
        .ColAlignment(8) = flexAlignCenterCenter
        .TextMatrix(0, 8) = "Cidade"
        .ColWidth(8) = 0
        
        .FixedAlignment(9) = flexAlignCenterCenter
        .ColAlignment(9) = flexAlignCenterCenter
        .TextMatrix(0, 9) = "Telefone"
        .ColWidth(9) = 0
        
        .FixedAlignment(10) = flexAlignCenterCenter
        .ColAlignment(10) = flexAlignCenterCenter
        .TextMatrix(0, 10) = "Celular"
        .ColWidth(10) = 1000

.Col = 0
End With
End Sub
Private Sub MontarGrid()
On Error GoTo Erro
Dim tbl As rdoResultset
Dim Linha As Integer
   sql = " SELECT A.CODIGOCLIENTE,A.NOMECLIENTE,A.RG,A.CPF,A.DATANASCIMENTO,A.ENDERECO,A.CEP,A.BAIRRO,A.CIDADE,A.TELEFONE,A.CELULAR" & Chr(13)
   sql = sql & " FROM CLIENTE A " & Chr(13)
   sql = sql & " ORDER BY A.CODIGOCLIENTE"
   '---------------------------------------
   Set tbl = Banco.OpenResultset(sql, rdOpenStatic)
  InicializaGrid
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
          .TextMatrix(Linha, 0) = tbl!CODIGOCLIENTE
          .TextMatrix(Linha, 1) = tbl!NOMECLIENTE
          .TextMatrix(Linha, 2) = tbl!CPF
          .TextMatrix(Linha, 3) = tbl!RG
          .TextMatrix(Linha, 4) = tbl!DATANASCIMENTO
          .TextMatrix(Linha, 5) = tbl!ENDERECO
          .TextMatrix(Linha, 6) = tbl!CEP
          .TextMatrix(Linha, 7) = tbl!BAIRRO
          .TextMatrix(Linha, 8) = tbl!CIDADE
          .TextMatrix(Linha, 9) = tbl!TELEFONE
          .TextMatrix(Linha, 10) = tbl!CELULAR
          
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
Private Sub grdConsulta_DblClick()
 With grdConsulta
  If .TextMatrix(.Row, 0) <> "" Then
   fracli.Caption = "Consulta/Alteração"
   fracli.Tag = 2
   cmdOk.Caption = "Alterar"

  End If
 End With
 recuperardadosgrid
End Sub
Private Sub recuperardadosgrid()
With grdConsulta
    txtcodigocliente.Text = .TextMatrix(.Row, 0)
    txtncliente.Text = .TextMatrix(.Row, 1)
    mskcpf.Text = .TextMatrix(.Row, 2)
    mskrg.Text = .TextMatrix(.Row, 3)
    mskdatanasc.Text = .TextMatrix(.Row, 4)
    txtendereco.Text = .TextMatrix(.Row, 5)
    txtcep.Text = .TextMatrix(.Row, 6)
    txtbairro.Text = .TextMatrix(.Row, 7)
    txtcidade.Text = .TextMatrix(.Row, 8)
    txttelefone.Text = .TextMatrix(.Row, 9)
    txtcelular.Text = .TextMatrix(.Row, 10)

    End With
    txtncliente.SetFocus
End Sub

Private Sub grdConsulta_KeyDown(KeyCode As Integer, Shift As Integer)
 'tecla delete
 If KeyCode = 46 And grdConsulta.TextMatrix(grdConsulta.Row, 1) <> "" Then
  Excluir
 End If
 
End Sub

