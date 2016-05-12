package co.edu.usbcali.demo.logica;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.dao.ITipoUsuariosDAO;
import co.edu.usbcali.demo.dao.IUsuariosDAO;
import co.edu.usbcali.demo.modelo.Usuarios;
import co.edu.usbcali.demo.modelo.TiposUsuarios;

@Service
@Scope("singleton")
public class UsuariosLogica implements IUsuariosLogica{

private static final Logger log = LoggerFactory.getLogger(UsuariosLogica.class);
	
	@Autowired
	private IUsuariosDAO usuarioDAO;
	
	@Autowired
	private ITipoUsuariosDAO tipoUsuarioDAO;
	
	@Autowired
	private Validator validator;
	
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void grabar(Usuarios usuarios) throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		
		Set<ConstraintViolation<Usuarios>> constraintViolations = validator.validate(usuarios);
		if (constraintViolations.size() > 0) {
			for (ConstraintViolation<Usuarios> constraintViolation : constraintViolations) {
				log.error(constraintViolation.getPropertyPath().toString());
				log.error(constraintViolation.getMessage());
				stringBuilder.append(constraintViolation.getPropertyPath().toString());
				stringBuilder.append("-");
				stringBuilder.append(constraintViolation.getMessage());
				stringBuilder.append(",");
			}
			throw new Exception(stringBuilder.toString()); 
		}
		
		TiposUsuarios tiposUsuarios = tipoUsuarioDAO.consultarPorId(usuarios.getTiposUsuarios().getTusuCodigo());
		if (tiposUsuarios == null) {
			throw new Exception("El tipo de usuario no existe");
		}
		
		usuarios.setTiposUsuarios(tiposUsuarios);
		
		usuarioDAO.grabar(usuarios);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void modificar(Usuarios usuarios) throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		
		Set<ConstraintViolation<Usuarios>> constraintViolations = validator.validate(usuarios);
		if (constraintViolations.size() > 0) {
			for (ConstraintViolation<Usuarios> constraintViolation : constraintViolations) {
				log.error(constraintViolation.getPropertyPath().toString());
				log.error(constraintViolation.getMessage());
				stringBuilder.append(constraintViolation.getPropertyPath().toString());
				stringBuilder.append("-");
				stringBuilder.append(constraintViolation.getMessage());
				stringBuilder.append(",");
			}
			throw new Exception(stringBuilder.toString()); 
		}
		
		if (usuarios.getTiposUsuarios() == null) {
			throw new Exception("El tipo de usuario es obligatorio");
		}
		
		TiposUsuarios tiposUsuarios = tipoUsuarioDAO.consultarPorId(usuarios.getTiposUsuarios().getTusuCodigo());
		if (tiposUsuarios == null) {
			throw new Exception("El tipo de usuario no existe");
		}
		
		usuarios.setTiposUsuarios(tiposUsuarios);
		
		usuarioDAO.modificar(usuarios);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void borrar(Usuarios usuarios) throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		
		Set<ConstraintViolation<Usuarios>> constraintViolations = validator.validate(usuarios);
		if (constraintViolations.size() > 0) {
			for (ConstraintViolation<Usuarios> constraintViolation : constraintViolations) {
				log.error(constraintViolation.getPropertyPath().toString());
				log.error(constraintViolation.getMessage());
				stringBuilder.append(constraintViolation.getPropertyPath().toString());
				stringBuilder.append("-");
				stringBuilder.append(constraintViolation.getMessage());
				stringBuilder.append(",");
			}
			throw new Exception(stringBuilder.toString()); 
		}
		
		Usuarios entity = usuarioDAO.consultarPorId(usuarios.getUsuCedula());
		
		
		if (entity == null) {
			throw new Exception("El usuario que desea eliminar no existe");
		}
		
		usuarioDAO.borrar(usuarios);
	}

	@Override
	@Transactional(readOnly=true)
	public Usuarios consultarPorId(long id) throws Exception {
		return usuarioDAO.consultarPorId(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Usuarios> consultarTodos() throws Exception {
		return usuarioDAO.consultarTodos();
	}	
	
}
