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
import co.edu.usbcali.demo.modelo.TiposUsuarios;

@Service
@Scope("singleton")
public class TiposUsuariosLogica implements ITiposUsuariosLogica {
	
	private static final Logger log = LoggerFactory.getLogger(TiposUsuariosLogica.class);
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private ITipoUsuariosDAO tipoUsuarioDAO;

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void grabar(TiposUsuarios tiposUsuarios) throws Exception {
		validar(tiposUsuarios);
		
		tipoUsuarioDAO.grabar(tiposUsuarios);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void modificar(TiposUsuarios tiposUsuarios) throws Exception {
		validar(tiposUsuarios);
		
		tipoUsuarioDAO.modificar(tiposUsuarios);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void borrar(TiposUsuarios tiposUsuarios) throws Exception {
		validar(tiposUsuarios);
		
		tipoUsuarioDAO.borrar(tiposUsuarios);
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
	
	private void validar(TiposUsuarios entity) throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		
		Set<ConstraintViolation<TiposUsuarios>> constraintViolations = validator.validate(entity);
		if (constraintViolations.size() > 0) {
			for (ConstraintViolation<TiposUsuarios> constraintViolation : constraintViolations) {
				log.error(constraintViolation.getPropertyPath().toString());
				log.error(constraintViolation.getMessage());
				stringBuilder.append(constraintViolation.getPropertyPath().toString());
				stringBuilder.append("-");
				stringBuilder.append(constraintViolation.getMessage());
				stringBuilder.append(",");
			}
			throw new Exception(stringBuilder.toString()); 
		}
	}

}