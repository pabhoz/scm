package co.edu.usbcali.demo.delegado;

import java.util.List;

import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.TiposDocumentos;

public interface IDelegadoDeNegocio {
	
	public void grabarClientes(Clientes clientes)throws Exception;
	public void modificarClientes(Clientes clientes)throws Exception;
	public void borrarClientes(Clientes clientes)throws Exception;
	public Clientes consultarClinetePorId(long cliId)throws Exception;
	public List<Clientes> consultarTodosClientes()throws Exception;
	
	
	public void grabarTiposDocumentos(TiposDocumentos entity)throws  Exception;
	public void modificarTiposDocumentos(TiposDocumentos entity)throws  Exception;
	public void borrarTiposDocumentos(TiposDocumentos entity)throws  Exception;
	public TiposDocumentos consultarTipoDocumentoId(long tdocCodigo)throws  Exception;
	public List<TiposDocumentos> consultarTodosTiposDocumentos()throws Exception;

}
