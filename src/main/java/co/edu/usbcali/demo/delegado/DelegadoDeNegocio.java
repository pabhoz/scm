package co.edu.usbcali.demo.delegado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import co.edu.usbcali.demo.logica.IClienteLogica;
import co.edu.usbcali.demo.logica.ITiposDocumentosLogica;
import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.TiposDocumentos;

@Scope("singleton")
@Component("delegadoDeNegocio")
public class DelegadoDeNegocio implements IDelegadoDeNegocio {
	
	@Autowired
	private IClienteLogica clienteLogica;
	
	@Autowired
	private ITiposDocumentosLogica tiposDocumentosLogica;

	@Override
	public void grabarClientes(Clientes clientes) throws Exception {
		clienteLogica.grabar(clientes);
	}

	@Override
	public void modificarClientes(Clientes clientes) throws Exception {
		clienteLogica.modificar(clientes);
		
	}

	@Override
	public void borrarClientes(Clientes clientes) throws Exception {
		clienteLogica.borrar(clientes);
		
	}

	@Override
	public Clientes consultarClinetePorId(long cliId) throws Exception {		
		return clienteLogica.consultarPorId(cliId);
	}

	@Override
	public List<Clientes> consultarTodosClientes() throws Exception {
		return clienteLogica.consultarTodos();
	}

	@Override
	public void grabarTiposDocumentos(TiposDocumentos entity) throws Exception {
		tiposDocumentosLogica.grabar(entity);		
	}

	@Override
	public void modificarTiposDocumentos(TiposDocumentos entity) throws Exception {
		tiposDocumentosLogica.modificar(entity);
		
	}

	@Override
	public void borrarTiposDocumentos(TiposDocumentos entity) throws Exception {
		tiposDocumentosLogica.borrar(entity);
		
	}

	@Override
	public TiposDocumentos consultarTipoDocumentoId(long tdocCodigo) throws Exception {
		return tiposDocumentosLogica.consultarPorId(tdocCodigo);
	}

	@Override
	public List<TiposDocumentos> consultarTodosTiposDocumentos() throws Exception {
		
		return tiposDocumentosLogica.consultarTodos();
	}

}
