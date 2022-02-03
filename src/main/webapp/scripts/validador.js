/**
 * Validar campos obrigatorios
 *
 * @author Eduardo Teodoro
 */
 
 function validar(){
	let nome = frmContato.nome.value
	let preco = frmContato.preço.value
	
	if (nome === "") {
		alert('Preencha o campo Nome')
		frmContato.nome.focus()
		return false
	} else if (preco === ""){
		alert('Preencha o campo Preço')
		frmContato.preço.focus()
		return false
	} else {
		document.forms["frmContato"].submit()
	}
}