/**
 * Arquivo de conexão com o banco de dados.
 *
 * @author Wanderlei Silva do Carmo
 * @version 20221219
 *
 */
package conexao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * Classe utilizada para conectar e desconectar do banco de dados.
 */
public class Conexao {

    private static Connection connection;

    public static Connection conectar() {

        try {

            FileReader arq;

            String path = new File(".").getCanonicalPath();
            String filePath = path + "/config.json";
            StringBuilder sb = new StringBuilder();

            arq = new FileReader(filePath);

            BufferedReader lerArq = new BufferedReader(arq);

            lerArq.lines().forEach(linha -> {
                sb.append(linha);
            });

            arq.close();

            System.out.println(sb.toString());

            Object configJson = JSONValue.parse(sb.toString());
            JSONObject json = (JSONObject) configJson;
            String dsn = (String) json.get("dsn");
            String user = (String) json.get("user");
            String password = (String) json.get("password");
            try {

                Class.forName("com.mysql.cj.jdbc.Driver");

                if (connection == null) {

                    connection = DriverManager.getConnection(dsn, user, password);

                    System.out.println("Conectado com sucesso.");
                }

            } catch (ClassNotFoundException ex) {
                System.out.println("O erro foi: " + ex.getMessage());
                System.out.println("Erro: Não encontrou o driver do BD.");
            } catch (SQLException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return connection;
    }

    /**
     * Desconectar o banco de dados
     * @param conn 
     */
    public  static void desconectar(Connection conn) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Desconectou do banco de dados.");
            }
        } catch (SQLException ex) {
            System.out.println("Não conseguiu desconectar do BD.");
        }
    }
}