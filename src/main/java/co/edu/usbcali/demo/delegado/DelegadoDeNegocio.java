package co.edu.usbcali.demo.delegado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.logica.IClienteLogica;
import co.edu.usbcali.demo.logica.IConsignacionesLogica;
import co.edu.usbcali.demo.logica.ICuentasLogica;
import co.edu.usbcali.demo.logica.IRetirosLogica;
import co.edu.usbcali.demo.logica.ITiposDocumentosLogica;
import co.edu.usbcali.demo.logica.ITiposUsuariosLogica;
import co.edu.usbcali.demo.logica.IUsuariosLogica;
import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.Consignaciones;
import co.edu.usbcali.demo.modelo.ConsignacionesId;
import co.edu.usbcali.demo.modelo.Cuentas;
import co.edu.usbcali.demo.modelo.Retiros;
import co.edu.usbcali.demo.modelo.RetirosId;
import co.edu.usbcali.demo.modelo.TiposDocumentos;
import co.edu.usbcali.demo.modelo.TiposUsuarios;
import co.edu.usbcali.demo.modelo.Usuarios;

@Scope("singleton")
@Component("delegadoDeNegocio")
public class DelegadoDeNegocio implements IDelegadoDeNegocio {
	
	@Autowired
	private IClienteLogica clienteLogica;

	@Autowired
	private IConsignacionesLogica consignacionesLogica;

	@Autowired
	private ICuentasLogica cuentasLogica;

	@Autowired
	private IRetirosLogica retirosLogica;
	
	@Autowired
	private ITiposDocumentosLogica tiposDocumentosLogica;

	@Autowired
	private ITiposUsuariosLogica tiposUsuariosLogica;

	@Autowired
	private IUsuariosLogica usuariosLogica;

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

	@Override
	public void grabarConsignaciones(Consignaciones consignaciones) throws Exception {
		consignacionesLogica.grabar(consignaciones);
		
	}

	@Override
	public void modificarConsignaciones(Consignaciones consignaciones) throws Exception {
		consignacionesLogica.modificar(consignaciones);
		
	}

	@Override
	public void borrarConsignaciones(Consignaciones consignaciones) throws Exception {
		consignacionesLogica.borrar(consignaciones);
		
	}

	@Override
	public Consignaciones consultarConsignacionesPorId(ConsignacionesId consignacionesId) throws Exception {
		return consignacionesLogica.consultarPorId(consignacionesId);
	}

	@Override
	public List<Consignaciones> consultarTodosConsignaciones() throws Exception {
		return consignacionesLogica.consultarTodos();
	}

	@Override
	public void grabarCuentas(Cuentas cuentas) throws Exception {
		cuentasLogica.grabar(cuentas);
		
	}

	@Override
	public void modificarCuentas(Cuentas cuentas) throws Exception {
		cuentasLogica.modificar(cuentas);
		
	}

	@Override
	public void borrarCuentas(Cuentas cuentas) throws Exception {
		cuentasLogica.borrar(cuentas);
		
	}

	@Override
	public Cuentas consultarCuentasPorId(String cuentasId) throws Exception {
		return cuentasLogica.consultarPorId(cuentasId);
	}

	@Override
	public List<Cuentas> consultarTodosCuentas() throws Exception {
		return cuentasLogica.consultarTodos();
	}
	
	@Override
	public Cuentas consultarCuentasPorNumero(String numero) throws Exception {
		return cuentasLogica.consultarPorNumero(numero);
	}

	@Override
	public void grabarRetiros(Retiros retiros) throws Exception {
		retirosLogica.grabar(retiros);
		
	}

	@Override
	public void modificarRetiros(Retiros retiros) throws Exception {
		retirosLogica.modificar(retiros);
		
	}

	@Override
	public void borrarRetiros(Retiros retiros) throws Exception {
		retirosLogica.borrar(retiros);
		
	}

	@Override
	public Retiros consultarRetirosPorId(RetirosId retirosId) throws Exception {
		return retirosLogica.consultarPorId(retirosId);
	}

	@Override
	public List<Retiros> consultarTodosRetiros() throws Exception {
		return retirosLogica.consultarTodos();
	}

	@Override
	public void grabarTiposUsuarios(TiposUsuarios tiposUsuarios) throws Exception {
		tiposUsuariosLogica.grabar(tiposUsuarios);
		
	}

	@Override
	public void modificarTiposUsuarios(TiposUsuarios tiposUsuarios) throws Exception {
		tiposUsuariosLogica.modificar(tiposUsuarios);
		
	}

	@Override
	public void borrarTiposUsuarios(TiposUsuarios tiposUsuarios) throws Exception {
		tiposUsuariosLogica.borrar(tiposUsuarios);
		
	}

	@Override
	public TiposUsuarios consultarTiposUsuariosPorId(Long tiposUsuariosId) throws Exception {
		return tiposUsuariosLogica.consultarPorId(tiposUsuariosId);
	}

	@Override
	public List<TiposUsuarios> consultarTodosTiposUsuarios() throws Exception {
		return tiposUsuariosLogica.consultarTodos();
	}

	@Override
	public void grabarUsuarios(Usuarios usuarios) throws Exception {
		usuariosLogica.grabar(usuarios);
		
	}

	@Override
	public void modificarUsuarios(Usuarios usuarios) throws Exception {
		usuariosLogica.modificar(usuarios);
		
	}

	@Override
	public void borrarUsuarios(Usuarios usuarios) throws Exception {
		usuariosLogica.borrar(usuarios);
		
	}

	@Override
	public Usuarios consultarUsuariosPorId(long usuariosId) throws Exception {
		return usuariosLogica.consultarPorId(usuariosId);
	}

	@Override
	public List<Usuarios> consultarTodosUsuarios() throws Exception {
		return usuariosLogica.consultarTodos();
	}
	
	@Override
	public List<Usuarios> consultarUsuariosPorTiposUsuarios(Long tusuCodigo) throws Exception {
		return usuariosLogica.consultarUsuariosPorTiposUsuarios(tusuCodigo);
	}

	

}
