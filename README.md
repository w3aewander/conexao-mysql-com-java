# Classe Java 
 # Conexão com o banco de dados.
 ## No exemplo: driver de conexão com o MySQL.

> 
> Altere o pacote de acordo com a sua aplicação
> Ajuste o config.json de acordo com as credenciais de acesso ao seu banco.
> 

Uso ilustrando uma consulta a uma tabela "produtos":

//Para abrir a conexão com o banco de idade.


Connection con = Conexao.conectar();

Para realizar as consultas:
        var con = Conexao.conectar();

        var sql = "select * from produtos";

        var pstm = con.prepareStatement(sql);
        
        pstm.execute();
        
        ResultSet rs = pstm.executeQuery();
        
        while (rs.next()) {
            System.out.println(rs.getString("descricao") );
        }

        rs.close();
        pstm.close();
        
        Conexao.desconectar(con);

//Para fechar a conexão
Conexao.desconectar(con);

