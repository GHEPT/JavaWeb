package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Model.DAO;
import Model.JavaBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/formCriarProduto", "/select", "/update", "/delete", "/report" })
public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The dao. */
	DAO dao = new DAO();
	
	/** The produto. */
	JavaBeans produto = new JavaBeans();

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		super();
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// teste de conexão
		// dao.testeConexao();
		String action = request.getServletPath();
		//System.out.println(action);
		if (action.equals("/main")) {
			produtos(request, response);
		} else if (action.equals("/formCriarProduto")) {
			adicionarProduto(request, response);
		} else if (action.equals("/select")) {
			listarProduto(request, response);
		} else if (action.equals("/update")) {
			editarProduto(request, response);
		} else if (action.equals("/delete")) {
			try {
				removerProduto(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equals("/report")) {
			gerarRelatorio(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	/**
	 * Produtos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Listar Produtos
	protected void produtos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando um objeto que irá receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listarProdutos();
		// Encaminhar a lista ao documento Produtos.jsp
		request.setAttribute("produtos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("Produtos.jsp");
		rd.forward(request, response);

		// Teste de recebimento da lista
		// for (int i = 0; i < lista.size(); i++) {
		// System.out.println(lista.get(i).getIdprod());
		// System.out.println(lista.get(i).getNome());
		// System.out.println(lista.get(i).getPreço());
		// System.out.println(lista.get(i).getImagem());
		// }
	}

	/**
	 * Adicionar produto.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Novo Produto
	protected void adicionarProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Teste de recebimento dos dados do formulário
		// System.out.println(request.getParameter("nome"));
		// System.out.println(request.getParameter("preço"));
		// System.out.println(request.getParameter("imagem"));

		// Setar as variáveis JavaBeans
		produto.setNome(request.getParameter("nome"));
		produto.setPreçoFormatado(request.getParameter("preço"));
		produto.setImagem(request.getParameter("imagem"));

		// Invocar o método inserirProduto passando o objeto produto
		dao.inserirProduto(produto);
		// Redirecionar para o documento Produto.jsp
		response.sendRedirect("main");
	}

	/**
	 * Listar produto.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Editar Produto (1º é seleção deles / 2º é a própria edição)
	protected void listarProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do produto que será editado
		String idprod = request.getParameter("idprod");
		// Setar a variável JavaBeans
		produto.setIdprod(idprod);
		// Executar o método selecionarProduto (DAO)
		dao.selecionarProduto(produto);
		// Setar os atributos do formulário com o conteúdo JavaBeans
		request.setAttribute("idprod", produto.getIdprod());
		request.setAttribute("nome", produto.getNome());
		request.setAttribute("preço", produto.getPreçoFormatado());
		request.setAttribute("imagem", produto.getImagem());
		// Encaminhar ao documento Editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("Editar.jsp");
		rd.forward(request, response);

		// Teste de Recebimento
		// System.out.println(produto.getIdprod());
		// System.out.println(produto.getNome());
		// System.out.println(produto.getPreço());
		// System.out.println(produto.getImagem());
	}

	/**
	 * Editar produto.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void editarProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Setar as variáveis JavaBeans
		produto.setIdprod(request.getParameter("idprod"));
		produto.setNome(request.getParameter("nome"));
		produto.setPreçoFormatado(request.getParameter("preço"));
		produto.setImagem(request.getParameter("imagem"));
		// Executar o método alterarProduto
		dao.alterarProduto(produto);
		// Redirecionar para o documento "Produto.jsp" (atualizando as alterações)
		response.sendRedirect("main");

		// Teste de recebimento
		// System.out.println(request.getParameter("idprod"));
		// System.out.println(request.getParameter("nome"));
		// System.out.println(request.getParameter("preço"));
		// System.out.println(request.getParameter("imagem"));
	}

	/**
	 * Remover produto.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	// Remover um Produto
	protected void removerProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, InterruptedException {
		// Recebimento do id do Produto a ser excluído (validador.js)
		String idprod = request.getParameter("idprod");
		// Setar a variável JavaBeans
		produto.setIdprod(idprod);
		// Executar o método deletarContato (DAO) passando o objeto produto
		dao.deletarProduto(produto);
		// Redirecionar para o documento "Produto.jsp" (atualizando as alterações)
		TimeUnit.SECONDS.sleep(3); 
		response.sendRedirect("main");
	}
	
	/**
	 * Gerar relatorio.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Gerar relatório em PDF
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document documento = new Document();
		try {
			// Tipo de conteúdo
			response.setContentType("apllication/pdf");
			// Nome do documento
			response.addHeader("Content-Disposition", "inline; filename=" + "Produtos.pdf");
			// Criar o documento
			PdfWriter.getInstance(documento, response.getOutputStream());
			// Abrir o documento para gerar o conteúdo
			documento.open();
			documento.add(new Paragraph("Lista de Produtos:"));
			documento.add(new Paragraph(" "));
			// Criar uma tabela
			PdfPTable tabela = new PdfPTable(3); // A tabela terá 3 colunas
			// Cabeçalho
			PdfPCell coluna1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell coluna2 = new PdfPCell(new Paragraph("Preço"));
			PdfPCell coluna3 = new PdfPCell(new Paragraph("Imagem"));			
			tabela.addCell(coluna1);
			tabela.addCell(coluna2);
			tabela.addCell(coluna3);
			// Popular a tabela com os produtos
			List<JavaBeans> lista = dao.listarProdutos();
			for (int i = 0; i < lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(String.valueOf(lista.get(i).getPreçoFormatado()));
				tabela.addCell(lista.get(i).getImagem());
			}
			
			documento.add(tabela);
			documento.close();
		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
	}

}

