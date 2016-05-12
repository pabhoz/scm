package co.edu.usbcali.demo.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.dao.ITipoUsuariosDAO;
import co.edu.usbcali.demo.modelo.TiposUsuarios;

@Service
@Scope("singleton")
public class TipoUsuarioLogica implements ITipoUsuarioLogica{
	
	@Autowired
	private ITipoUsuariosDAO tipoUsuarioDAO;
	
	@Override
	public void grabar(TiposUsuarios tiposUsuarios) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificar(TiposUsuarios tiposUsuarios) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void borrar(TiposUsuarios tiposUsuarios) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional(readOnly=true)
	public TiposUsuarios consultarPorId(Long id) throws Exception {		
		return tipoUsuarioDAO.consultarPorId(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<TiposUsuarios> consultarTodos() throws Exception {
		return tipoUsuarioDAO.consultarTodos();
	}
	
}
