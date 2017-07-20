package br.com.caelum.estoque.modelo.usuario;



import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class InfoFault {

	private Date dataErro;
	private String mensagem;
	
	
	public InfoFault(String mensagem, Date date){
		this.mensagem = mensagem;
		this.dataErro = date;
	}
	
	//JAX-B precisa
	InfoFault(){
		
	}
	
	
	
}
