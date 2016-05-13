package co.edu.usbcali.demo.vista;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.demo.delegado.IDelegadoDeNegocio;

@ViewScoped
@ManagedBean
public class IndexVista {
	
	private String currentView = "";
	private final static Logger log = LoggerFactory.getLogger(IndexVista.class);
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;

	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}

	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}
	
	public void loadClientesView(){
		log.info("Cargando vista de clientes");
		currentView = "./cliente.xhtml";
	}
	
	public void loadUsuariosView(){
		log.info("Cargando vista de usuarios");
		currentView = "./usuario.xhtml";
	}
	
	public void loadConsignacionesView(){
		log.info("Cargando vista de usuarios");
		currentView = "./consignaciones.xhtml";
	}
	
	public void loadRetirosView(){
		log.info("Cargando vista de usuarios");
		currentView = "./retiros.xhtml";
	}

	public String getCurrentView() {
		return currentView;
	}

	public void setCurrentView(String currentView) {
		this.currentView = currentView;
	}
	
	
	

}
