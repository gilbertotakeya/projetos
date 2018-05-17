VERSION 5.00
Object = "{C4847593-972C-11D0-9567-00A0C9273C2A}#8.0#0"; "crviewer.dll"
Begin VB.Form frmRelSer 
   Caption         =   "Relatório Serviço"
   ClientHeight    =   9375
   ClientLeft      =   60
   ClientTop       =   345
   ClientWidth     =   10260
   LinkTopic       =   "Form1"
   MDIChild        =   -1  'True
   ScaleHeight     =   9375
   ScaleWidth      =   10260
   WindowState     =   2  'Maximized
   Begin CRVIEWERLibCtl.CRViewer CRViewerSer 
      Height          =   8805
      Left            =   0
      TabIndex        =   0
      Top             =   480
      Width           =   10005
      DisplayGroupTree=   0   'False
      DisplayToolbar  =   -1  'True
      EnableGroupTree =   0   'False
      EnableNavigationControls=   -1  'True
      EnableStopButton=   -1  'True
      EnablePrintButton=   -1  'True
      EnableZoomControl=   -1  'True
      EnableCloseButton=   -1  'True
      EnableProgressControl=   -1  'True
      EnableSearchControl=   -1  'True
      EnableRefreshButton=   -1  'True
      EnableDrillDown =   -1  'True
      EnableAnimationControl=   -1  'True
      EnableSelectExpertButton=   -1  'True
      EnableToolbar   =   -1  'True
      DisplayBorder   =   0   'False
      DisplayTabs     =   0   'False
      DisplayBackgroundEdge=   -1  'True
      SelectionFormula=   ""
      EnablePopupMenu =   0   'False
      EnableExportButton=   -1  'True
      EnableSearchExpertButton=   -1  'True
      EnableHelpButton=   0   'False
   End
End
Attribute VB_Name = "frmRelSer"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Dim Report As New RepServico

Private Sub Form_Resize()
   CRViewerSer.Top = 0
   CRViewerSer.Left = 0
   CRViewerSer.Height = frmRelSer.ScaleHeight - CRViewerSer.Top
   CRViewerSer.Width = frmRelSer.ScaleWidth
End Sub
Private Sub Form_Load()
  Dim tbl As rdoResultset
  
  sql = "SELECT CODIGOSERVICO,DATA,VALOR,DESCRICAO,CODIGOCARRO"
  sql = sql & " FROM SERVICO"
  
  Set tbl = Banco.OpenResultset(sql, rdOpenStatic)
  
  Report.DiscardSavedData
  Report.Database.Tables(1).SetPrivateData 3, tbl
    
  CRViewerSer.ReportSource = Report
  CRViewerSer.ViewReport
End Sub
