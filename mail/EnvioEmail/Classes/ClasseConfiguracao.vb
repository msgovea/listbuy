Public Class ClasseConfiguracao

    Public Structure Banco
        '-----------CONEXÃO COM BANCO DE DADOS-----------'
        Dim strServer As String
        Dim strDatabase As String
        Dim strUsuario As String
        Dim strSenha As String

        Property server() As String
            Get
                server = strServer
            End Get
            Set(ByVal Value As String)
                strServer = Value
            End Set
        End Property

        Property database() As String
            Get
                database = strDatabase
            End Get
            Set(ByVal Value As String)
                strDatabase = Value
            End Set
        End Property

        Property usuario() As String
            Get
                usuario = strUsuario
            End Get
            Set(ByVal Value As String)
                strUsuario = Value
            End Set
        End Property

        Property senha() As String
            Get
                senha = strSenha
            End Get
            Set(ByVal Value As String)
                strSenha = Value
            End Set
        End Property

        Public Function conexao(ByVal dados As Banco) As Boolean
            Dim retorno As Boolean = False
            Try
                Dim abrirConexao As New System.Data.SqlClient.SqlConnection
                abrirConexao.ConnectionString = "Server=" & dados.server & ";Database=" & dados.database & ";user id=" & dados.usuario & ";pwd=" & dados.senha & " "
                abrirConexao.Open()
                If abrirConexao.State = ConnectionState.Open Then
                    abrirConexao.Close()
                    retorno = True
                End If
            Catch ex As Exception
            End Try
            Return retorno
        End Function

    End Structure

    Public Structure Email
        Dim strUsuario As String
        Dim strSmtp As String
        Dim strPortaSmtp As String
        Dim strSSL As String
        Dim strEmailRemetente As String
        Dim strSenhaRemetente As String
        Dim strNomePT As String
        Dim strSitePT As String

        Property usuario() As String
            Get
                usuario = strUsuario
            End Get
            Set(ByVal Value As String)
                strUsuario = Value
            End Set
        End Property
        Property smtp() As String
            Get
                smtp = strSmtp
            End Get
            Set(ByVal Value As String)
                strSmtp = Value
            End Set
        End Property
        Property portaSmtp() As String
            Get
                portaSmtp = strPortaSmtp
            End Get
            Set(ByVal Value As String)
                strPortaSmtp = Value
            End Set
        End Property
        Property ssl() As String
            Get
                ssl = strSSL
            End Get
            Set(ByVal Value As String)
                strSSL = Value
            End Set
        End Property
        Property emailRemetente() As String
            Get
                emailRemetente = strEmailRemetente
            End Get
            Set(ByVal Value As String)
                strEmailRemetente = Value
            End Set
        End Property
        Property senhaRementente() As String
            Get
                senhaRementente = strSenhaRemetente
            End Get
            Set(ByVal Value As String)
                strSenhaRemetente = Value
            End Set
        End Property
        Property nomePT() As String
            Get
                nomePT = strNomePT
            End Get
            Set(ByVal Value As String)
                strNomePT = Value
            End Set
        End Property
        Property sitePT() As String
            Get
                sitePT = strSitePT
            End Get
            Set(ByVal Value As String)
                strSitePT = Value
            End Set
        End Property
    End Structure

End Class
