package co.edu.usbcali.demo.dao.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;
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

import co.edu.usbcali.demo.dao.IConsignacionesDAO;
import co.edu.usbcali.demo.dao.ICuentasDAO;
import co.edu.usbcali.demo.dao.IUsuariosDAO;
import co.edu.usbcali.demo.modelo.Consignaciones;
import co.edu.usbcali.demo.modelo.ConsignacionesId;
import co.edu.usbcali.demo.modelo.Cuentas;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class ConsignacionesDAOTest {

	private static final Logger log = LoggerFactory.getLogger(ConsignacionesDAOTest.class);
	
	@Autowired
	private IConsignacionesDAO consignacionesDAO;
	
	@Autowired
	private ICuentasDAO cuentasDAO;
	
	@Autowired
	private IUsuariosDAO usuarioDAO;
	
	private Long idCon = 9999L;
	private String idCuenta = "4008-5305-0010";
	private Long idUser = 10L;
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void aTest() {
		Consignaciones consignaciones = new Consignaciones();
		consignaciones.setConDescripcion("Consignaci�n de prueba");
		consignaciones.setConFecha(new Date());
		consignaciones.setConValor(new BigDecimal(99999));
		
		Cuentas cuentas = cuentasDAO.consultarPorId(idCuenta);
		assertNotNull("La cuenta no existe para la consignaci�n", cuentas);
		
		consignaciones.setCuentas(cuentas);
		consignaciones.setUsuarios(usuarioDAO.consultarPorId(idUser));

		ConsignacionesId consignacionesId = new ConsignacionesId(this.idCon, this.idCuenta);
		consignaciones.setId(consignacionesId);
		
		consignacionesDAO.grabar(consignaciones);
	}
	
	@Test
	@Transactional(readOnly=true)
	public void bTest() {
		ConsignacionesId consignacionesId = new ConsignacionesId(this.idCon, this.idCuenta);
		Consignaciones consignaciones = consignacionesDAO.consultarPorId(consignacionesId);
		assertNotNull("La consignacion no existe", consignaciones);
		log.info(consignaciones.getConDescripcion() + " - " + consignaciones.getConValor());
	}
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void cTest() {
		ConsignacionesId consignacionesId = new ConsignacionesId(this.idCon, this.idCuenta);
		Consignaciones consignaciones = consignacionesDAO.consultarPorId(consignacionesId);
		assertNotNull("La consignacion no existe", consignaciones);
		consignaciones.setConValor(new BigDecimal(11111));
		consignacionesDAO.modificar(consignaciones);
	}
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void dTest() {
		ConsignacionesId consignacionesId = new ConsignacionesId(this.idCon, this.idCuenta);
		Consignaciones consignaciones = consignacionesDAO.consultarPorId(consignacionesId);
		assertNotNull("La consignacion no existe", consignaciones);
		consignacionesDAO.borrar(consignaciones);
	}
	
	@Test
	@Transactional(readOnly=true)
	public void eTest() {
		List<Consignaciones> lasConsignaciones = consignacionesDAO.consultarTodos();
		for (Consignaciones consignaciones : lasConsignaciones) {
			log.info(consignaciones.getId().getConCodigo() + " - " + consignaciones.getConDescripcion() + " - " + consignaciones.getConDescripcion());
		}
	}


}