package co.edu.usbcali.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.demo.delegado.IDelegadoDeNegocio;
import co.edu.usbcali.demo.dto.ClienteDTO;
import co.edu.usbcali.demo.modelo.Clientes;

@RestController
@RequestMapping("/cliente")
public class ClienteRest {
	
	@Autowired
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	
	@RequestMapping(value="/consultarClientePorId/{cliId}",method=RequestMethod.GET)
	public ClienteDTO consultarClientePorId(@PathVariable("cliId")long cliId)throws Exception{
		
		Clientes clientes=delegadoDeNegocio.consultarClinetePorId(cliId);
		if(clientes==null){
			throw new Exception("El cliente no existe");
		}
		
		ClienteDTO clienteDTO=new ClienteDTO();
		clienteDTO.setCliDireccion(clientes.getCliDireccion());
		clienteDTO.setCliId(clientes.getCliId());
		clienteDTO.setCliMail(clientes.getCliMail());
		clienteDTO.setCliNombre(clientes.getCliNombre());
		clienteDTO.setCliTelefono(clientes.getCliTelefono());
		clienteDTO.setTdocCodigo(clientes.getTiposDocumentos().getTdocCodigo());
		
		
		return clienteDTO;
	}
	
	@RequestMapping(value="/consularTodos/",method=RequestMethod.GET)
	public List<ClienteDTO> consultarTodos()throws Exception{
		
		List<Clientes> losClientes=delegadoDeNegocio.consultarTodosClientes();
				
		List<ClienteDTO> losClientesDTO = null;
		
		for (Clientes clientes : losClientes) {
			
			ClienteDTO clienteDTO=new ClienteDTO();
			clienteDTO.setCliDireccion(clientes.getCliDireccion());
			clienteDTO.setCliId(clientes.getCliId());
			clienteDTO.setCliMail(clientes.getCliMail());
			clienteDTO.setCliNombre(clientes.getCliNombre());
			clienteDTO.setCliTelefono(clientes.getCliTelefono());
			clienteDTO.setTdocCodigo(clientes.getTiposDocumentos().getTdocCodigo());
			
			losClientesDTO.add(clienteDTO);
		}
		
		return losClientesDTO;
	}
	
	//public void grabar(Clientes clientes)throws Exception;
	//public void modificar(Clientes clientes)throws Exception;
	//public void borrar(Clientes clientes)throws Exception;
	
	//public List<Clientes> consultarTodos()throws Exception;
	
	

}
