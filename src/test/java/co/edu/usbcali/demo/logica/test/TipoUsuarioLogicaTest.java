package co.edu.usbcali.demo.logica.test;

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

import co.edu.usbcali.demo.logica.ITiposUsuariosLogica;
import co.edu.usbcali.demo.modelo.TiposUsuarios;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class TipoUsuarioLogicaTest {

	private static final Logger log = LoggerFactory.getLogger(TipoUsuarioLogicaTest.class);

	@Autowired
	private ITiposUsuariosLogica tipoUsuarioLogica;

	private long tusuCodigo = 99L;

	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void aTest() throws Exception {
		TiposUsuarios tiposUsuarios = new TiposUsuarios();
		tiposUsuarios.setTusuCodigo(tusuCodigo);
		tiposUsuarios.setTusuNombre("Tipo Usuario Prueba");

		tipoUsuarioLogica.grabar(tiposUsuarios);
	}

	@Test
	@Transactional(readOnly = false)
	public void bTest() throws Exception {
		TiposUsuarios tiposUsuarios = tipoUsuarioLogica.consultarPorId(tusuCodigo);
		assertNotNull("El tipo de usuario no existe", tiposUsuarios);
		log.info(tiposUsuarios.getTusuCodigo() + " - " + tiposUsuarios.getTusuNombre());
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void cTest() throws Exception {
		TiposUsuarios tiposUsuarios = tipoUsuarioLogica.consultarPorId(tusuCodigo);
		assertNotNull("El tipo de usuario no existe", tiposUsuarios);
		tiposUsuarios.setTusuNombre("Tipo Usuario Editado");
		tipoUsuarioLogica.modificar(tiposUsuarios);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void dTest() throws Exception {
		TiposUsuarios tiposUsuarios = tipoUsuarioLogica.consultarPorId(tusuCodigo);
		assertNotNull("El tipo de usuario no existe", tiposUsuarios);

		tipoUsuarioLogica.borrar(tiposUsuarios);
	}

	@Test
	@Transactional(readOnly = false)
	public void eTest() throws Exception {
		List<TiposUsuarios> losTipoUsuario = tipoUsuarioLogica.consultarTodos();
		for (TiposUsuarios tiposUsuarios : losTipoUsuario) {
			log.info(tiposUsuarios.getTusuCodigo() + " - " + tiposUsuarios.getTusuNombre());
		}
	}

}
