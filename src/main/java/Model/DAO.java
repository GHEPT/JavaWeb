package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {
	
	/**  M�dulo de conex�o *. */
	// Par�metros de conex�o
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3306/dbprodutos?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "root";

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	// M�todo de conex�o
	private Connection conectar() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			return conn;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}
	// teste de conex�o
	// public void testeConexao() {
	// try {
	// Connection conn = conectar();
	// System.out.println(conn);
	// conn.close();
	// } catch (Exception e) {
	// System.out.println(e);
	// }
	// }

	/**
	 *  CRUD CREATE *.
	 *
	 * @param produto the produto
	 */
	public void inserirProduto(JavaBeans produto) {
		String create = "insert into produtos (nome, pre�o, imagem) values (?,?,?)";
		try {
			// 1� Passo: Abrir a conex�o
			Connection conn = conectar();
			// 2� Passo: Preparar a query para a execu��o no DB
			PreparedStatement pst = conn.prepareStatement(create);
			// 3� Passo: Substituir os par�metros (?) pelo conte�do das vari�veis JavaBeans
			pst.setString(1, produto.getNome());
			pst.setString(2, produto.getPre�oFormatado());
			pst.setString(3, produto.getImagem());
			// 4� Passo: Executar a query. Esse comando insere os dados no DB
			pst.executeUpdate();
			// 5� Passo: Encerrar a conex�o com o banco para eliminar problemas de perform e
			// seg.
			conn.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 *  CRUD READ *.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaBeans> listarProdutos() {
		// Criando um objeto para acessar a classe JavaBeans
		ArrayList<JavaBeans> produtos = new ArrayList<>();
		String read = "select * from produtos order by nome";
		try {
			// Abrir a conex�o com o banco de dados
			Connection conn = conectar();
			PreparedStatement pst = conn.prepareStatement(read);
			// JDBC -> Usado para armazenar o retorno doDB temporariamente em um objeto
			ResultSet result = pst.executeQuery(); // Essa linha executa a query da linha 67
			// Para exibir o resultado
			// O la�o abaixo ser� executado enquanto houver contatos
			while (result.next()) {
				// Vari�veis de apoio que recebem os dados do DB
				String idprod = result.getString(1); // Meu 1� campo no DB � o idprod
				String nome = result.getString(2);
				double pre�o = result.getDouble(3);
				Locale meuLocal = new Locale("pt", "BR");
				String pre�oFormatado = NumberFormat.getCurrencyInstance(meuLocal).format(pre�o); 
				String imagem = result.getString(4);
				// Populando o ArrayList
				produtos.add(new JavaBeans(idprod, nome, pre�oFormatado, imagem));
			}
			conn.close();
			return produtos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/**
	 *  CRUD UPDATE *.
	 *
	 * @param produto the produto
	 */
	// Selecionar o Produto
	public void selecionarProduto(JavaBeans produto) {
		String read2 = "select * from produtos where idprod = ?";
		try {
			Connection conn = conectar();
			PreparedStatement pst = conn.prepareStatement(read2);
			pst.setString(1, produto.getIdprod());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				// Setar as vari�veis JavaBeans
				produto.setIdprod(rs.getString(1));
				produto.setNome(rs.getString(2));
				produto.setPre�oFormatado(rs.getString(3));
				produto.setImagem(rs.getString(4));
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Alterar produto.
	 *
	 * @param produto the produto
	 */
	// Editar o Produto
	public void alterarProduto(JavaBeans produto) {
		String update = "update produtos set nome=?, pre�o=?, imagem=? where idprod=?";
		try {
			Connection conn = conectar();
			PreparedStatement pst = conn.prepareStatement(update);
			pst.setString(1, produto.getNome());
			pst.setString(2, produto.getPre�oFormatado());
			pst.setString(3, produto.getImagem());
			pst.setString(4, produto.getIdprod());
			pst.executeUpdate();
			pst.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 *  CRUD DELETE *.
	 *
	 * @param produto the produto
	 */
	public void deletarProduto(JavaBeans produto) {
		String delete = "delete from produtos where idprod=?";
		try {
			Connection conn = conectar();
			PreparedStatement pst = conn.prepareStatement(delete);
			pst.setString(1, produto.getIdprod());
			pst.executeUpdate();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

