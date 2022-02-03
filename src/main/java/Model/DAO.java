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
	
	/**  Módulo de conexão *. */
	// Parâmetros de conexão
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
	// Método de conexão
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
	// teste de conexão
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
		String create = "insert into produtos (nome, preço, imagem) values (?,?,?)";
		try {
			// 1º Passo: Abrir a conexão
			Connection conn = conectar();
			// 2º Passo: Preparar a query para a execução no DB
			PreparedStatement pst = conn.prepareStatement(create);
			// 3º Passo: Substituir os parâmetros (?) pelo conteúdo das variáveis JavaBeans
			pst.setString(1, produto.getNome());
			pst.setString(2, produto.getPreçoFormatado());
			pst.setString(3, produto.getImagem());
			// 4º Passo: Executar a query. Esse comando insere os dados no DB
			pst.executeUpdate();
			// 5º Passo: Encerrar a conexão com o banco para eliminar problemas de perform e
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
			// Abrir a conexão com o banco de dados
			Connection conn = conectar();
			PreparedStatement pst = conn.prepareStatement(read);
			// JDBC -> Usado para armazenar o retorno doDB temporariamente em um objeto
			ResultSet result = pst.executeQuery(); // Essa linha executa a query da linha 67
			// Para exibir o resultado
			// O laço abaixo será executado enquanto houver contatos
			while (result.next()) {
				// Variáveis de apoio que recebem os dados do DB
				String idprod = result.getString(1); // Meu 1º campo no DB é o idprod
				String nome = result.getString(2);
				double preço = result.getDouble(3);
				Locale meuLocal = new Locale("pt", "BR");
				String preçoFormatado = NumberFormat.getCurrencyInstance(meuLocal).format(preço); 
				String imagem = result.getString(4);
				// Populando o ArrayList
				produtos.add(new JavaBeans(idprod, nome, preçoFormatado, imagem));
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
				// Setar as variáveis JavaBeans
				produto.setIdprod(rs.getString(1));
				produto.setNome(rs.getString(2));
				produto.setPreçoFormatado(rs.getString(3));
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
		String update = "update produtos set nome=?, preço=?, imagem=? where idprod=?";
		try {
			Connection conn = conectar();
			PreparedStatement pst = conn.prepareStatement(update);
			pst.setString(1, produto.getNome());
			pst.setString(2, produto.getPreçoFormatado());
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

