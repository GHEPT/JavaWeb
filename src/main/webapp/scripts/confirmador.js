/**
 * Confirmar a exclusão de um contato e mostrar snackbar de sucesso
 * @author Eduardo Teodoro
 * @param idprod
 */
 
 function showSnackbar(){
	// Get the snackbar DIV
  let x = document.getElementById("snackbar");

  // Add the "show" class to DIV
  x.className = "show";

  // After 3 seconds, remove the show class from DIV
  setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
  
}
 
 
 function confirmar(idprod){
	let resposta = confirm("Você deseja realmente excluir este produto?")
	if (resposta === true){	
		window.location.href = "delete?idprod=" + idprod
		showSnackbar()
	} 
}

