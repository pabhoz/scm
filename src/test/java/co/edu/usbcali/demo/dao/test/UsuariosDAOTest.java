package co.edu.usbcali.demo.dao.test;

import static org.junit.Assert.assertNotNull;

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

import co.edu.usbcali.demo.dao.IUsuariosDAO;
import co.edu.usbcali.demo.dao.ITipoUsuariosDAO;
import co.edu.usbcali.demo.modelo.Usuarios;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class UsuariosDAOTest {

private static final Logger log = LoggerFactory.getLogger(UsuariosDAOTest.class);
	
	@Autowired
	private IUsuariosDAO usuarioDAO;
	
	@Autowired
	private ITipoUsuariosDAO tipoUsuariosDAO;
	
	private Long id = 30L;
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void aTest() throws Exception {
		Usuarios usuarios = new Usuarios();
		usuarios.setUsuCedula(id);
		usuarios.setUsuClave("1234");
		usuarios.setUsuLogin("test");
		usuarios.setUsuNombre("Jhon");
		usuarios.setTiposUsuarios(tipoUsuariosDAO.consultarPorId(10L));
		
		usuarioDAO.grabar(usuarios);
		
	}
	
	@Test
	@Transactional(readOnly=true)
	public void bTest() throws Exception {
		Usuarios usuarios = usuarioDAO.consultarPorId(id);
		assertNotNull("El usuario no existe", usuarios);
		log.info(usuarios.getUsuNombre());
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void cTest() throws Exception {
		Usuarios usuarios = usuarioDAO.consultarPorId(id);
		assertNotNull("El usuario no existe", usuarios);
		usuarios.setUsuNombre("MODIFIED USER HERE");
		usuarioDAO.modificar(usuarios);
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void dTest() throws Exception {
		Usuarios usuarios = usuarioDAO.consultarPorId(id);
		assertNotNull("El usuario no existe", usuarios);
		
		usuarioDAO.borrar(usuarios);
	}
	
	@Test
	@Transactional(readOnly=true)
	public void eTest() throws Exception {
		List<Usuarios> losUsuarios = usuarioDAO.consultarTodos();
		for (Usuarios usuarios : losUsuarios) {
			log.info(usuarios.getUsuNombre() + " - " + usuarios.getUsuLogin());
		}
	}
}
