package co.edu.usbcali.demo.logica.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.edu.usbcali.demo.logica.ITipoUsuarioLogica;
import co.edu.usbcali.demo.modelo.TiposUsuarios;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class TipoUsuarioLogicaTest {
	
private static final Logger log = LoggerFactory.getLogger(TipoUsuarioLogicaTest.class);
	
	@Autowired
	private ITipoUsuarioLogica tipoUsuarioLogica;
	
	private Long tipoDocumentoId = 10L;

	@Test
	
	public void dTest() throws Exception {
		TiposUsuarios tiposUsuarios = tipoUsuarioLogica.consultarPorId(tipoDocumentoId);
		assertNotNull("El Tipo de usuario no existe" , tiposUsuarios);
		log.info(tiposUsuarios.getTusuNombre());
	}
	
}
