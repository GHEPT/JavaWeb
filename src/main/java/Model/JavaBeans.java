package Model;

// TODO: Auto-generated Javadoc
/**
 * The Class JavaBeans.
 */
public class JavaBeans {
	
	/** The idprod. */
	private String idprod;
	
	/** The nome. */
	private String nome;
	
	/** The preço formatado. */
	private String preçoFormatado;
	
	/** The imagem. */
	private String imagem;
	
	
	/**
	 * Instantiates a new java beans.
	 */
	public JavaBeans() {
		super();
		
	}	
	
	/**
	 * Instantiates a new java beans.
	 *
	 * @param idprod the idprod
	 * @param nome the nome
	 * @param preçoFormatado the preço formatado
	 * @param imagem the imagem
	 */
	public JavaBeans(String idprod, String nome, String preçoFormatado, String imagem) {
		super();
		this.idprod = idprod;
		this.nome = nome;
		this.preçoFormatado = preçoFormatado;
		this.imagem = imagem;
	}

	/**
	 * Gets the idprod.
	 *
	 * @return the idprod
	 */
	public String getIdprod() {
		return idprod;
	}
	
	/**
	 * Sets the idprod.
	 *
	 * @param idprod the new idprod
	 */
	public void setIdprod(String idprod) {
		this.idprod = idprod;
	}
	
	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Gets the preço formatado.
	 *
	 * @return the preço formatado
	 */
	public String getPreçoFormatado() {
		return preçoFormatado;
	}
	
	/**
	 * Sets the preço formatado.
	 *
	 * @param preçoFormatado the new preço formatado
	 */
	public void setPreçoFormatado(String preçoFormatado) {
		this.preçoFormatado = preçoFormatado;
	}
	
	/**
	 * Gets the imagem.
	 *
	 * @return the imagem
	 */
	public String getImagem() {
		return imagem;
	}
	
	/**
	 * Sets the imagem.
	 *
	 * @param imagem the new imagem
	 */
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}	
}
