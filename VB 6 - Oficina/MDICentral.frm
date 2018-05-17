VERSION 5.00
Object = "{831FDD16-0C5C-11D2-A9FC-0000F8754DA1}#2.0#0"; "mscomctl.ocx"
Begin VB.MDIForm MDICentral 
   BackColor       =   &H8000000A&
   Caption         =   "Oficina Medeiros"
   ClientHeight    =   7620
   ClientLeft      =   60
   ClientTop       =   750
   ClientWidth     =   10080
   LinkTopic       =   "MDIForm1"
   Picture         =   "MDICentral.frx":0000
   StartUpPosition =   2  'CenterScreen
   WindowState     =   2  'Maximized
   Begin MSComctlLib.StatusBar StatusBar1 
      Align           =   2  'Align Bottom
      Height          =   255
      Left            =   0
      TabIndex        =   0
      Top             =   7365
      Width           =   10080
      _ExtentX        =   17780
      _ExtentY        =   450
      _Version        =   393216
      BeginProperty Panels {8E3867A5-8586-11D1-B16A-00C0F0283628} 
         NumPanels       =   2
         BeginProperty Panel1 {8E3867AB-8586-11D1-B16A-00C0F0283628} 
            Style           =   6
            Alignment       =   1
            TextSave        =   "21/2/2006"
         EndProperty
         BeginProperty Panel2 {8E3867AB-8586-11D1-B16A-00C0F0283628} 
            Style           =   5
            Alignment       =   1
            TextSave        =   "18:08"
         EndProperty
      EndProperty
      MousePointer    =   4
      BeginProperty Font {0BE35203-8F91-11CE-9DE3-00AA004BB851} 
         Name            =   "Monotype Corsiva"
         Size            =   12
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   -1  'True
         Strikethrough   =   0   'False
      EndProperty
   End
   Begin VB.Menu mnucadastro 
      Caption         =   "Arquivo"
      Begin VB.Menu mnucliente 
         Caption         =   "Cliente"
      End
      Begin VB.Menu mnucarro 
         Caption         =   "Carro"
      End
      Begin VB.Menu mnuservico 
         Caption         =   "Serviço"
      End
      Begin VB.Menu mnuespa 
         Caption         =   "-"
      End
      Begin VB.Menu mnusair 
         Caption         =   "Sair"
      End
   End
   Begin VB.Menu mnurel 
      Caption         =   "Relatórios"
      Begin VB.Menu mnurelcli 
         Caption         =   "Cliente"
      End
      Begin VB.Menu mnurelcar 
         Caption         =   "Carro"
      End
      Begin VB.Menu mnurelser 
         Caption         =   "Serviço"
      End
   End
End
Attribute VB_Name = "MDICentral"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Private Sub MDIForm_Load()
  Set Banco = rdoEnvironments(0).OpenConnection("", rdDriverNoPrompt, False, "DSN=BancoOficina;UID=SYSDBA;PWD=masterkey")
End Sub

Private Sub mnucarro_Click()
 cadastrocarro.Show
End Sub

Private Sub mnucliente_Click()
  cadastrocliente.Show
  
End Sub

Private Sub mnurelcar_Click()
 RelatorioCarro.Show
End Sub

Private Sub mnurelcli_Click()
  frmRelCliente.Show
End Sub

Private Sub mnurelser_Click()
frmRelSer.Show
End Sub

Private Sub mnusair_Click()
 End
End Sub

Private Sub mnuservico_Click()
 cadastroservico.Show
End Sub
