Attribute VB_Name = "Module1"
Public Banco As rdoConnection
Sub CarregaCombo(sql As String, Combo As ComboBox)
On Error GoTo Erro
Dim tbl As rdoResultset
  Set tbl = Banco.OpenResultset(sql, rdOpenStatic)
  While Not tbl.EOF
   Combo.AddItem tbl(1)
   Combo.ItemData(Combo.NewIndex) = tbl(0)
   tbl.MoveNext
  Wend
  tbl.Close
   
  Exit Sub
Erro:
  MsgBox "erro ao carregar o combo " & Combo.Name & "!!" & Chr(13) & Err.Number & " -> " & Err.Description, vbCritical, "Erro"
End Sub

Sub selecionaCombo(Combo As ComboBox, valor As String)
On Error GoTo Erro
 Dim i As Integer
  For i = 0 To Combo.ListCount - 1
  If Combo.ItemData(i) = valor Then
   Combo.ListIndex = i
   Exit For
  End If
  Next i
  Exit Sub
Erro:
 MsgBox "Erro ao selecionar o item do combo " & Combo.Name & "!!" & Chr(13) & Err.Number & "->" & Err.Description, vbCritical, "Erro"
End Sub

Public Sub TratarErro()
    
   Select Case Err.Number
   Case 40002: MsgBox "Existem dados relacionados a esse registro!! É necessário excluí-los antes!!", vbCritical, "Erro"
   Case Else:
     MsgBox Err.Description, vbCritical, "Erro"
   End Select
End Sub

