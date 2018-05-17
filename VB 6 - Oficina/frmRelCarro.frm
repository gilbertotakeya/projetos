VERSION 5.00
Object = "{C4847593-972C-11D0-9567-00A0C9273C2A}#8.0#0"; "crviewer.dll"
Begin VB.Form RelatorioCarro 
   Caption         =   "Relatório Carro"
   ClientHeight    =   8130
   ClientLeft      =   60
   ClientTop       =   450
   ClientWidth     =   10020
   LinkTopic       =   "Form1"
   MDIChild        =   -1  'True
   ScaleHeight     =   8130
   ScaleWidth      =   10020
   WindowState     =   2  'Maximized
   Begin CRVIEWERLibCtl.CRViewer CRViewer1 
      Height          =   7335
      Left            =   0
      TabIndex        =   0
      Top             =   600
      Width           =   8895
      DisplayGroupTree=   0   'False
      DisplayToolbar  =   -1  'True
      EnableGroupTree =   -1  'True
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
      EnableSelectExpertButton=   0   'False
      EnableToolbar   =   -1  'True
      DisplayBorder   =   0   'False
      DisplayTabs     =   0   'False
      DisplayBackgroundEdge=   0   'False
      SelectionFormula=   ""
      EnablePopupMenu =   -1  'True
      EnableExportButton=   0   'False
      EnableSearchExpertButton=   0   'False
      EnableHelpButton=   0   'False
   End
End
Attribute VB_Name = "RelatorioCarro"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Dim Report As New RepCar

Private Sub Form_Load()
  Dim tbl As rdoResultset
  
  sql = "SELECT CODIGOCARRO,MARCA,NOMECARRO,CODIGOCLIENTE"
  sql = sql & " FROM CARRO"
  
  Set tbl = Banco.OpenResultset(sql, rdOpenStatic)
  
  Report.DiscardSavedData
  Report.Database.Tables(1).SetPrivateData 3, tbl
    
  CRViewer1.ReportSource = Report
  CRViewer1.ViewReport
End Sub

Private Sub Form_Resize()
   CRViewer1.Top = 0
   CRViewer1.Left = 0
   CRViewer1.Height = RelatorioCarro.ScaleHeight - CRViewer1.Top
   CRViewer1.Width = RelatorioCarro.ScaleWidth
End Sub
