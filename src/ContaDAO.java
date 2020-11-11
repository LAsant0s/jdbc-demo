import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContaDAO {
	public void inserir(Conta c) throws SQLException {
		Connection conexao = FabricaDeConexao.getConnection();
		String sql = "insert into conta" + "(titular,numero,agencia,limite,saldo)" + " values (?,?,?,?,?)";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, c.getTitular());
		stmt.setInt(2, c.getConta());
		stmt.setInt(3, c.getAgencia());
		stmt.setDouble(4, c.getLimite());
		stmt.setDouble(5, c.getSaldo());
		stmt.execute();
		stmt.close();
		conexao.close();
	}

	
	public void listagem() throws SQLException {
		  Connection conexao = FabricaDeConexao.getConnection();
		  String sql = "select * from conta";
		  PreparedStatement stmt = conexao.prepareStatement(sql);
		  ResultSet resultado = stmt.executeQuery();
		  while (resultado.next()) {
		    System.out.println(resultado.getString("titular") + " " + resultado.getDouble("saldo"));
		  }
		  resultado.close();
		  stmt.close();
		  conexao.close();
		}

	public void atualiza(Conta c, int id) throws SQLException {
		Connection conexao = FabricaDeConexao.getConnection();		
		String sql = "UPDATE conta SET titular = ?, numero = ?, agencia = ?, limite = ?, saldo = ? WHERE id = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, c.getTitular());
		stmt.setInt(2, c.getConta());
		stmt.setInt(3, c.getAgencia());
		stmt.setDouble(4, c.getLimite());
		stmt.setDouble(5, c.getSaldo());
		stmt.setInt(6, id);
		stmt.execute();
		stmt.close();
		conexao.close();
	}
	
	
	public void exclui(int id) throws SQLException {
		Connection conexao = FabricaDeConexao.getConnection();
		String sql = "DELETE FROM conta WHERE id = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.execute();
		stmt.close();
		conexao.close();
	}
	
	public static void main(String[] args) {
		Conta conta1 = new Conta("Luan de Assis", 1, 901, 100.0, 99.5);
		Conta conta2 = new Conta("Henrique Moreira", 2, 902, 1000.0, 100.5);
		Conta conta3 = new Conta("Raimundo Nonato", 3, 903, 500.5, 25.23);
		Conta conta4 = new Conta("Lucas Assis", 4, 904, 550.0, 100.9);
		Conta conta5 = new Conta("Jonas Assis", 5, 905, 850.0, 710.25);
		ContaDAO dao = new ContaDAO();
		try {
			dao.inserir(conta1);
			dao.inserir(conta2);
			dao.inserir(conta3);
			dao.inserir(conta4);
			dao.inserir(conta5);
			dao.exclui(5);
			dao.listagem();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
