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

import co.edu.usbcali.demo.dao.ITipoDocumentosDAO;
import co.edu.usbcali.demo.modelo.TiposDocumentos;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)

public class TipoDocumentosDAOTest {

	private static final Logger log = LoggerFactory.getLogger(TipoDocumentosDAOTest.class);
	
	@Autowired
	private ITipoDocumentosDAO tipoDocumentoDAO;
	
	private Long id = 40L;
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void aTest() {
			TiposDocumentos tiposDocumentos = new TiposDocumentos();
			tiposDocumentos.setTdocCodigo(40);
			tiposDocumentos.setTdocNombre("CREDENCIAL VIP");
			
			tipoDocumentoDAO.grabar(tiposDocumentos);
	}
	
	@Test
	@Transactional(readOnly=true)
	public void bTest() {
			TiposDocumentos tiposDocumentos = tipoDocumentoDAO.consultarPorId(id);
			assertNotNull("El tipo documento no existe", tiposDocumentos);
			log.info(tiposDocumentos.getTdocNombre());
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void cTest() {
		TiposDocumentos tiposDocumentos = tipoDocumentoDAO.consultarPorId(id);
		assertNotNull("El tipo de documento no existe", tiposDocumentos);
		tiposDocumentos.setTdocNombre("VIP CARD");
		tipoDocumentoDAO.modificar(tiposDocumentos);
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void dTest() {
		TiposDocumentos tiposDocumentos = tipoDocumentoDAO.consultarPorId(id);
		assertNotNull("El tipo de documento no existe", tiposDocumentos);

		tipoDocumentoDAO.borrar(tiposDocumentos);
	}
	
	@Test
	@Transactional(readOnly=true)
	public void eTest() {
		List<TiposDocumentos> losTiposDocumentos = tipoDocumentoDAO.consultarTodos();
		for (TiposDocumentos td : losTiposDocumentos) {
			log.info(td.getTdocCodigo() + " - " + td.getTdocNombre());
		}
	}
}