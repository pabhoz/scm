package co.edu.usbcali.demo.controlador.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import co.edu.usbcali.demo.dto.ClienteDTO;

public class ClienteRestTest {
	
	private static final Logger log=LoggerFactory.getLogger(ClienteRestTest.class);

	private Long cliId=142020L;
	
	@Test
	public void atest() {
		ClienteDTO clienteDTO=new ClienteDTO();
		clienteDTO.setCliDireccion("Vaenida 100");
		clienteDTO.setCliId(cliId);
		clienteDTO.setCliMail("dgomez@vortexbird.com");
		clienteDTO.setCliNombre("Diego Gomez");
		clienteDTO.setCliTelefono("5544454");
		clienteDTO.setTdocCodigo(10L);
		
		RestTemplate restTemplate=new RestTemplate();
		
		restTemplate.postForLocation("http://localhost:8080/demoWeb/controlador/cliente/grabar", clienteDTO);

	}
	
	@Test
	public void btest() {
	
		RestTemplate restTemplate=new RestTemplate();
		
		ClienteDTO clienteDTO=restTemplate.getForObject("http://localhost:8080/demoWeb/controlador/cliente/consultarClientePorId/"+cliId, ClienteDTO.class);
		
		log.info(clienteDTO.getCliNombre());

	}
	
	@Test
	public void ctest() {
	
		RestTemplate restTemplate=new RestTemplate();
		
		ClienteDTO clienteDTO=restTemplate.getForObject("http://localhost:8080/demoWeb/controlador/cliente/consultarClientePorId/"+cliId, ClienteDTO.class);
		
		clienteDTO.setCliNombre("Carlos Matos");
		
		restTemplate.put("http://localhost:8080/demoWeb/controlador/cliente/modificar", clienteDTO);
		

	}
	
	
	@Test
	public void dtest() {
	
		RestTemplate restTemplate=new RestTemplate();
		
		ClienteDTO clienteDTO=restTemplate.getForObject("http://localhost:8080/demoWeb/controlador/cliente/consultarClientePorId/"+cliId, ClienteDTO.class);
		
		restTemplate.delete("http://localhost:8080/demoWeb/controlador/cliente/borrar/"+clienteDTO.getCliId());
		

	}
	
	

}
