package co.edu.usbcali.demo.dao.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.dao.ITipoUsuariosDAO;
import co.edu.usbcali.demo.modelo.TiposUsuarios;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)

public class TipoUsuariosDAOTest {

	private static final Logger log = LoggerFactory.getLogger(TipoUsuariosDAOTest.class);
	
	@Autowired
	private ITipoUsuariosDAO tipoUsuarioDAO;
	
	private Long id = 40L;
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void aTest() {
			TiposUsuarios tiposUsuarios = new TiposUsuarios();
			tiposUsuarios.setTusuCodigo(id);
			tiposUsuarios.setTusuNombre("SUPER VILLIAN");
			
			tipoUsuarioDAO.grabar(tiposUsuarios);
	}
	
	@Test
	@Transactional(readOnly=true)
	public void bTest() {
			TiposUsuarios tiposUsuarios = tipoUsuarioDAO.consultarPorId(id);
			assertNotNull("El tipo usuario no existe", tiposUsuarios);
			log.info(tiposUsuarios.getTusuNombre());
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void cTest() {
		TiposUsuarios tiposUsuarios = tipoUsuarioDAO.consultarPorId(id);
		assertNotNull("El tipo de usuario no existe", tiposUsuarios);
		tiposUsuarios.setTusuNombre("SUPER HERO");
		tipoUsuarioDAO.modificar(tiposUsuarios);
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void dTest() {
		TiposUsuarios tiposUsuarios = tipoUsuarioDAO.consultarPorId(id);
		assertNotNull("El tipo de usuario no existe", tiposUsuarios);

		tipoUsuarioDAO.borrar(tiposUsuarios);
	}
	
	@Test
	@Transactional(readOnly=true)
	public void eTest() {
		List<TiposUsuarios> losTiposUsuarios = tipoUsuarioDAO.consultarTodos();
		for (TiposUsuarios td : losTiposUsuarios) {
			log.info(td.getTusuCodigo() + " - " + td.getTusuNombre());
		}
	}
}