package co.edu.usbcali.demo.logica.test;

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

import co.edu.usbcali.demo.logica.ITiposDocumentosLogica;
import co.edu.usbcali.demo.logica.TiposUsuariosLogica;
import co.edu.usbcali.demo.modelo.TiposDocumentos;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class TipoDocumentoLogicaTest {

	private static final Logger log = LoggerFactory.getLogger(TiposUsuariosLogica.class);

	@Autowired
	private ITiposDocumentosLogica tipoDocumentoLogica;

	private long tdocCodigo = 99L;

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void aTest() throws Exception {
		TiposDocumentos tiposDocumentos = new TiposDocumentos();
		tiposDocumentos.setTdocCodigo(tdocCodigo);
		tiposDocumentos.setTdocNombre("Tipo Usuario Prueba");

		tipoDocumentoLogica.grabar(tiposDocumentos);
	}

	@Test
	@Transactional(readOnly = false)
	public void bTest() throws Exception {
		TiposDocumentos tiposDocumentos = tipoDocumentoLogica.consultarPorId(tdocCodigo);
		assertNotNull("El tipo de usuario no existe", tiposDocumentos);
		log.info(tiposDocumentos.getTdocCodigo() + " - " + tiposDocumentos.getTdocNombre());
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void cTest() throws Exception {
		TiposDocumentos tiposDocumentos = tipoDocumentoLogica.consultarPorId(tdocCodigo);
		assertNotNull("El tipo de usuario no existe", tiposDocumentos);
		tiposDocumentos.setTdocNombre("Tipo Usuario Editado");
		tipoDocumentoLogica.modificar(tiposDocumentos);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void dTest() throws Exception {
		TiposDocumentos tiposDocumentos = tipoDocumentoLogica.consultarPorId(tdocCodigo);
		assertNotNull("El tipo de usuario no existe", tiposDocumentos);

		tipoDocumentoLogica.borrar(tiposDocumentos);
	}

	@Test
	@Transactional(readOnly = false)
	public void eTest() throws Exception {
		List<TiposDocumentos> losTipoUsuario = tipoDocumentoLogica.consultarTodos();
		for (TiposDocumentos tiposDocumentos : losTipoUsuario) {
			log.info(tiposDocumentos.getTdocCodigo() + " - " + tiposDocumentos.getTdocNombre());
		}
	}

}
