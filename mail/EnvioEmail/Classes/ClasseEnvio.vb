Imports System.Data.SqlClient
Imports System.IO
Imports System.Net.Mail
Imports EnvioEmail

Public Class ClasseEnvio

    Dim Sql As String = String.Empty
    Dim rs As New DataView

    ' Dim banco As New ClasseConfiguracao.Banco
    Dim email As New ClasseConfiguracao.Email
    Dim codigo As String = Nothing
    Dim destinatario As String
    Dim nome As String

    Sub New(ByVal email As ClasseConfiguracao.Email, ByVal keyAcesso As String, destinatario As String, nome As String)
        'Me.banco = banco
        Me.email = email
        Me.codigo = keyAcesso
        Me.destinatario = destinatario
        Me.nome = nome
    End Sub

    Public Function VERIFICAR_SOLICITACAO_EMAIL() As String
        Dim retorno As String = Nothing
        Try
            'Dim conexao As New ConexaoBanco(Me.banco)

            'Buscando no banco de dados e-mails para enviar.

            'Tem e-mail para ser enviado.
            'If rs.Count > 0 Then
            Dim envio As New EnvioEmail(Me.email)
            With envio
                ' .login = rs.ToTable.Rows(0)("LOGIN").ToString
                .status = 0
                .destinatario = destinatario
                .codigo = codigo
                .nome = nome
                retorno = .ENVIAR()
                ' If retorno = "sucesso" Then
                '    retorno = "O e-mail foi enviado com sucesso."
                '    Sql = " UPDATE BANCO_CADASTRO SET STATUS = " & CInt(.status + 1)
                ' Else
                'retorno = "O e-mail não foi enviado."
                '    Sql = " UPDATE BANCO_CADASTRO SET STATUS = 3 "
                'End If
                'Sql = Sql & " WHERE BANCO_CADASTRO_ID = " & Me.cadastroID
                'conexao.execute(Sql)
            End With
            'Else
            'retorno = "Nenhum e-mail foi encontrado para envio."
            'End If

        Catch ex As Exception
            retorno = ex.Message
        End Try
        rs.Dispose()
        Return retorno
    End Function

    Public Structure EnvioEmail
        Dim email As ClasseConfiguracao.Email
        Sub New(ByVal dadosEmail As ClasseConfiguracao.Email)
            email = dadosEmail
        End Sub

        Public Property login As String
        Public Property senha As String
        Public Property nome As String
        Public Property destinatario As String
        Public Property codigo As String
        Public Property status As Integer

        Public Function ENVIAR() As String
            Dim retorno As String = String.Empty
            Dim caminho As String = AppDomain.CurrentDomain.BaseDirectory
            Dim ConteudoEmail As String = String.Empty
            Dim linhaTexto As String = String.Empty
            Dim assunto As String = String.Empty
            Dim mensagem As String = String.Empty
            Try
                'Buscando conteúdo dentro do Arquivo.
                If IO.File.Exists(caminho & "Texto_Email.txt") Then
                    Dim fluxoTexto As New StreamReader(caminho & "Texto_Email.txt")
                    linhaTexto = fluxoTexto.ReadLine
                    While linhaTexto <> Nothing
                        ConteudoEmail &= linhaTexto & vbCrLf
                        linhaTexto = fluxoTexto.ReadLine
                    End While
                    fluxoTexto.Close()

                    'Preparando Assunto
                    Select Case status
                        Case 0
                            assunto = "Confirmação de cadastro " & email.nomePT
                            mensagem = "Olá " & nome & ", obrigado por se cadastrar em nosso aplicativo.<br><br>"
                            mensagem = mensagem & "Para confirmar sua conta, clique no link abaixo.<br><a href='http://" & email.sitePT & "/activate/" & codigo & "'>http://" & email.sitePT & "/activate/" & codigo & "</a>"

                        Case 2
                            'assunto = "Recuperação de dados " & email.nomePT
                            'mensagem = "Olá " & nome & ", segue os seus dados.<br><br>"
                            'mensagem = mensagem & "<b>Nome:</b> " & nome & "<br>"
                            'mensagem = mensagem & "<b>Login:</b> " & login & "<br>"
                            'mensagem = mensagem & "<b>Senha:</b> " & senha & "<br><br>"
                            'mensagem = mensagem & "<font color='red'>Para sua segurança, não informe seus dados para ningúem.</font>"

                    End Select

                    'Mensagem que vai chegar no e-mail.

                    ConteudoEmail = ConteudoEmail.Replace("[assunto]", assunto) : ConteudoEmail = ConteudoEmail.Replace("[mensagem]", mensagem)

                    Dim objEmail As New MailMessage()
                    objEmail.From = New MailAddress("listbuy <" & email.emailRemetente & ">")
                    objEmail.To.Add("<" & Trim(destinatario) & ">")
                    objEmail.Priority = MailPriority.Normal
                    objEmail.IsBodyHtml = True
                    objEmail.Subject = assunto
                    objEmail.Body = ConteudoEmail
                    objEmail.SubjectEncoding = Encoding.GetEncoding("ISO-8859-1")
                    objEmail.BodyEncoding = Encoding.GetEncoding("ISO-8859-1")


                    Dim objSmtp As New SmtpClient(email.smtp, email.portaSmtp)
                    objSmtp.Credentials = New System.Net.NetworkCredential(email.emailRemetente, email.senhaRementente)
                    objSmtp.Host = email.smtp
                    objSmtp.Port = email.portaSmtp
                    objSmtp.EnableSsl = email.ssl
                    objSmtp.Send(objEmail)
                    objEmail.Dispose()

                    retorno = "sucesso"
                Else
                    retorno = "Arquivo Texto_Email.txt não existe."
                End If

            Catch ex As Exception
                retorno = ex.Message
            End Try
            Return retorno
        End Function
    End Structure

    'Public Structure ConexaoBanco
    '    Dim banco As ClasseConfiguracao.Banco
    '    Sub New(ByVal dadosBanco As ClasseConfiguracao.Banco)
    '        banco = dadosBanco
    '    End Sub
    '    Private Function abreBanco() As SqlConnection
    '        Dim StringConexao As String = "Server=" & banco.server & ";Database=" & banco.database & ";user id=" & banco.usuario & ";pwd=" & banco.senha
    '        Dim Conn As New SqlConnection
    '        With Conn
    '            .ConnectionString = StringConexao
    '            .Open()
    '        End With
    '        Return Conn
    '    End Function
    '    Public Function execute(ByVal QuerySQL As String) As Object
    '        Dim conn As New SqlConnection
    '        Dim ds As New DataSet
    '        Dim dv As New DataView
    '        Dim retorno As Object = Nothing
    '        Try
    '            conn = abreBanco()
    '            ds = Me.retornaDataSet(QuerySQL, conn)
    '            If ds.Tables.Count <> 0 Then
    '                dv = ds.Tables(0).DefaultView
    '            Else
    '                dv = Nothing
    '            End If
    '            retorno = dv
    '        Catch ex As Exception
    '            retorno = ex.ToString
    '            fechaConexao(conn)
    '        End Try
    '        Return retorno
    '    End Function
    '    Public Function retornaDataSet(ByVal strQuery As String, ByVal Conn As SqlConnection) As DataSet
    '        Dim dsDataSet As New DataSet
    '        Try
    '            Dim cmdComando As New SqlCommand
    '            cmdComando.CommandTimeout = 300
    '            With cmdComando
    '                .CommandText = strQuery
    '                .CommandType = CommandType.Text
    '                .Connection = Conn
    '            End With
    '            Dim daAdaptador As New SqlDataAdapter
    '            daAdaptador.SelectCommand = cmdComando
    '            daAdaptador.Fill(dsDataSet)
    '        Catch ex As Exception
    '        Finally
    '        End Try
    '        Return dsDataSet
    '    End Function
    '    Private Sub fechaConexao(ByVal Conn As SqlConnection)
    '        If Conn.State = ConnectionState.Open Then
    '            Conn.Close()
    '        End If
    '    End Sub
    'End Structure

End Class
