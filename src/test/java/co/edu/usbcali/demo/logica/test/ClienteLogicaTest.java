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

import co.edu.usbcali.demo.dao.ITipoDocumentosDAO;
import co.edu.usbcali.demo.logica.IClienteLogica;
import co.edu.usbcali.demo.modelo.Clientes;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class ClienteLogicaTest {
	
	private static final Logger log = LoggerFactory.getLogger(ClienteLogicaTest.class);
	
	@Autowired
	private IClienteLogica clienteLogica;
	
	@Autowired
	private ITipoDocumentosDAO tipoDocumentoDAO;
	
	private Long cliId = 851234L;
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void aTest() throws Exception {
		Clientes clientes = new Clientes();
		clientes.setCliId(cliId);
		clientes.setCliDireccion("La Umbr�a");
		clientes.setCliMail("a@b.com");
		clientes.setCliNombre("Lu");
		clientes.setCliTelefono("1234567");
		clientes.setTiposDocumentos(tipoDocumentoDAO.consultarPorId(10L));
		
		clienteLogica.grabar(clientes);
		
	}
	
	@Test
	@Transactional(readOnly=true)
	public void bTest() throws Exception {
		Clientes clientes = clienteLogica.consultarPorId(cliId);
		assertNotNull("El cliente no existe", clientes);
		log.info(clientes.getCliNombre());
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void cTest() throws Exception {
		Clientes clientes = clienteLogica.consultarPorId(cliId);
		assertNotNull("El cliente no existe", clientes);
		clientes.setCliNombre("Modified Client Name");
		clienteLogica.modificar(clientes);
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void dTest() throws Exception {
		Clientes clientes = clienteLogica.consultarPorId(cliId);
		assertNotNull("El cliente no existe", clientes);
		
		clienteLogica.borrar(clientes);
	}
	
	@Test
	@Transactional(readOnly=true)
	public void eTest() throws Exception {
		List<Clientes> losClientes = clienteLogica.consultarTodos();
		for (Clientes clientes : losClientes) {
			log.info(clientes.getCliNombre() + " - " + clientes.getCliMail());
		}
	}

}
