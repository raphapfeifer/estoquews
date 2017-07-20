package br.com.caelum.estoque.ws;



import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import br.com.caelum.estoque.modelo.item.Filtro;
import br.com.caelum.estoque.modelo.item.Filtros;
import br.com.caelum.estoque.modelo.item.Item;
import br.com.caelum.estoque.modelo.item.ItemDao;
import br.com.caelum.estoque.modelo.item.ItemValidador;
import br.com.caelum.estoque.modelo.item.ListaItens;
import br.com.caelum.estoque.modelo.usuario.AutorizacaoException;
import br.com.caelum.estoque.modelo.usuario.TokenDao;
import br.com.caelum.estoque.modelo.usuario.TokenUsuario;

@WebService
public class EstoqueWS {

	private ItemDao dao = new ItemDao();
	
	@WebMethod(operationName="todosOsItens")
	//@ResponseWrapper(localName="itens")
	@WebResult(name="itens")
	//@RequestWrapper(localName="listItens")
	public ListaItens getItens(@WebParam(name="filtros")Filtros filtros){
		
		System.out.println("Chamando getItens()");
		
		List<Filtro> listaFiltros = filtros.getLista();
		List<Item> lista = dao.todosItens(listaFiltros);
		return new ListaItens(lista);
	}
	
	@WebMethod(operationName="CadastrarItem")
	@WebResult(name="item")
	public Item cadastrarItem(@WebParam(name="token",header=true)TokenUsuario token
			,@WebParam(name="item")Item item) throws AutorizacaoException{
		
		System.out.println("cadastrando item " + item + ", Token: " + token);
		
		boolean valido = new TokenDao().ehValido(token);
		
		if(!valido){
			throw new AutorizacaoException("Autorização Falhou");
		}
		
		new ItemValidador(item).validate();
		
		dao.cadastrar(item);
		
		return item;
	}
	
}
