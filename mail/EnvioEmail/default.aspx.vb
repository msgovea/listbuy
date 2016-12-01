Public Class _default
    Inherits System.Web.UI.Page

    Public Function RemoteCertificateValidationCallback(ByVal sender As Object, ByVal certificate As System.Security.Cryptography.X509Certificates.X509Certificate, ByVal chain As System.Security.Cryptography.X509Certificates.X509Chain, ByVal sslPolicyErrors As System.Net.Security.SslPolicyErrors) As Boolean
        'MsgBox("chamado ")
        Return True
    End Function

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load
        Try
            System.Net.ServicePointManager.ServerCertificateValidationCallback = New System.Net.Security.RemoteCertificateValidationCallback(AddressOf RemoteCertificateValidationCallback)

            '--------- RECEBENDO GET ---------'
            Dim keyAcesso As String = Nothing
            Dim destinatario As String = Nothing
            Dim nome As String = Nothing

            If String.IsNullOrEmpty(Request.QueryString("keyAcesso")) = False Then
                keyAcesso = Replace(Request.QueryString("keyAcesso"), "'", "")
                destinatario = Replace(Request.QueryString("destinatario"), "'", "")
                nome = Replace(Request.QueryString("nome"), "'", "")
            Else
                Response.Write("Parâmetro para identificação do envio não foi encontrado.")
                Response.End()
            End If

            ''--------- CONFIGURANDO O BANCO DE DADOS ---------'
            'Dim dadosBanco As New ClasseConfiguracao.Banco
            'With dadosBanco
            '    .server = "PT\SQLEXPRESS" '"198.50.181.161,1051"
            '    .database = "SITE_cPT"
            '    .usuario = "sa"
            '    .senha = "m@2409"
            '    If .conexao(dadosBanco) = False Then
            '        Response.Write("Conexão com banco de dados não realizada!")
            '        Response.End()
            '    End If
            'End With

            '--------- CONFIGURANDO DADOS DO SMTP ---------'
            Dim dadosEmail As New ClasseConfiguracao.Email
            With dadosEmail
                .usuario = String.Empty 'ESSA OPÇÃO É PARA QUEM TEM CONTRATADO SERVIÇO DE SMTP EM MASSA.
                .smtp = "mx1.hostinger.com.br"
                .portaSmtp = "587"
                .ssl = "True"
                .emailRemetente = "no-reply@listbuy.me"
                .senhaRementente = "mateus00"
                .nomePT = "ListBuy"
                .sitePT = "servidor.listbuy.me:81"
            End With

            Dim email As New ClasseEnvio(email:=dadosEmail, keyAcesso:=keyAcesso, destinatario:=destinatario, nome:=nome)
            Dim retornoEnvio As String = email.VERIFICAR_SOLICITACAO_EMAIL()
            Response.Write(retornoEnvio)

        Catch ex As Exception

            Response.Write(ex.Message)
        End Try
    End Sub

End Class