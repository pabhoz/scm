package co.edu.usbcali.demo.vista;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.demo.delegado.IDelegadoDeNegocio;
import co.edu.usbcali.demo.modelo.TiposUsuarios;
import co.edu.usbcali.demo.modelo.Usuarios;

@ViewScoped
@ManagedBean
public class UsuarioVista {

	private final static Logger log = LoggerFactory.getLogger(UsuarioVista.class);

	@ManagedProperty(value = "#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;

	private List<Usuarios> losUsuarios;

	private List<SelectItem> losTiposUsuariosItems;

	private InputText txtIdentificacion;
	private InputText txtCodigo;
	private InputText txtNombre;
	private InputText txtLogin;
	private Password txtClave;

	private SelectOneMenu somTiposUsuarios;

	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnBorrar;
	private CommandButton btnLimpiar;

	public String crearAction() {
		log.info("Ingreso a crear");

		try {

			Usuarios usuarios=new Usuarios();
			usuarios.setUsuCedula(Long.parseLong(txtIdentificacion.getValue().toString().trim()));
			usuarios.setUsuNombre(txtNombre.getValue().toString().trim());
			usuarios.setUsuLogin(txtLogin.getValue().toString().trim());
			usuarios.setUsuClave(txtClave.getValue().toString().trim());
			TiposUsuarios tiposUsuarios=delegadoDeNegocio.consultarTiposUsuariosPorId(Long.parseLong(somTiposUsuarios.getValue().toString()));
			usuarios.setTiposUsuarios(tiposUsuarios);
			
			delegadoDeNegocio.grabarUsuarios(usuarios);
			
			try {
				losUsuarios = delegadoDeNegocio.consultarTodosUsuarios();
				this.setLosUsuarios(losUsuarios);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			this.limpiarAction();
			
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "El usuario se creó con exito", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return "";
	}

	public String modificarAction() {
		log.info("Ingreso a modificar");
		try {
			
			Usuarios usuarios=new Usuarios();
			usuarios.setUsuCedula(Long.parseLong(txtIdentificacion.getValue().toString().trim()));
			usuarios.setUsuNombre(txtNombre.getValue().toString().trim());
			usuarios.setUsuLogin(txtLogin.getValue().toString().trim());
			usuarios.setUsuClave(txtClave.getValue().toString().trim());
			TiposUsuarios tiposUsuarios=delegadoDeNegocio.consultarTiposUsuariosPorId(Long.parseLong(somTiposUsuarios.getValue().toString()));
			usuarios.setTiposUsuarios(tiposUsuarios);
			
			delegadoDeNegocio.modificarUsuarios(usuarios);
			
			try {
				losUsuarios = delegadoDeNegocio.consultarTodosUsuarios();
				this.setLosUsuarios(losUsuarios);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			this.limpiarAction();
			
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "El usuario se modifico con exito", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return "";
	}

	public String borrarAction() {
		log.info("Ingreso a borrar");
		try {
			
			Usuarios usuarios=new Usuarios();
			
			usuarios.setUsuCedula(Long.parseLong(txtIdentificacion.getValue().toString().trim()));
			
			delegadoDeNegocio.borrarUsuarios(usuarios);
			
			this.limpiarAction();
			
			try {
				losUsuarios = delegadoDeNegocio.consultarTodosUsuarios();
				this.setLosUsuarios(losUsuarios);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			this.limpiarAction();
			
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "El Usuario se elimino con exito", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return "";
	}

	public String limpiarAction() {
		
		  log.info("Ingreso a limpiar");
		  txtLogin.resetValue();
		  txtClave.resetValue(); 
		  txtNombre.resetValue();
		  somTiposUsuarios.setValue("-1");
		  txtIdentificacion.resetValue();
		  
		  btnBorrar.setDisabled(true); 
		  btnCrear.setDisabled(true);
		  btnModificar.setDisabled(true);
		 
		return "";
	}

	public void txtIdentificacionListener() {
		Usuarios entity = null;

		try {
			Long id = Long.parseLong(txtIdentificacion.getValue().toString().trim());
			entity = delegadoDeNegocio.consultarUsuariosPorId(id);
		} catch (Exception e) {
		}

		if (entity == null) {
			txtLogin.resetValue();
			txtNombre.resetValue();
			txtClave.resetValue();
			somTiposUsuarios.setValue("-1");

			btnBorrar.setDisabled(true);
			btnCrear.setDisabled(false);
			btnModificar.setDisabled(true);
		} else {

			txtLogin.setValue(entity.getUsuLogin());
			txtNombre.setValue(entity.getUsuNombre());
			txtClave.setValue(entity.getUsuClave());
			
			somTiposUsuarios.setValue(entity.getTiposUsuarios().getTusuCodigo());

			btnBorrar.setDisabled(false);
			btnCrear.setDisabled(true);
			btnModificar.setDisabled(false);

		}

	}

	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}

	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}

	public List<Usuarios> getLosUsuarios() {
		if (losUsuarios == null) {
			try {
				losUsuarios = delegadoDeNegocio.consultarTodosUsuarios();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return losUsuarios;
	}

	public void setLosUsuarios(List<Usuarios> losUsuarios) {
		this.losUsuarios = losUsuarios;
	}

	public List<SelectItem> getLosTiposUsuariosItems() {
		try {
			if (losTiposUsuariosItems == null) {
				losTiposUsuariosItems = new ArrayList<SelectItem>();
				List<TiposUsuarios> losEntity = delegadoDeNegocio.consultarTodosTiposUsuarios();
				for (TiposUsuarios tiposDocumentos : losEntity) {
					losTiposUsuariosItems
							.add(new SelectItem(tiposDocumentos.getTusuCodigo(), tiposDocumentos.getTusuNombre()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return losTiposUsuariosItems;
	}

	public void setLosTiposUsuariosItems(List<SelectItem> losTiposUsuariosItems) {
		this.losTiposUsuariosItems = losTiposUsuariosItems;
	}

	public InputText getTxtIdentificacion() {
		return txtIdentificacion;
	}

	public void setTxtIdentificacion(InputText txtIdentificacion) {
		this.txtIdentificacion = txtIdentificacion;
	}

	public InputText getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(InputText txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public InputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public InputText getTxtLogin() {
		return txtLogin;
	}

	public void setTxtLogin(InputText txtLogin) {
		this.txtLogin = txtLogin;
	}

	public Password getTxtClave() {
		return txtClave;
	}

	public void setTxtClave(Password txtClave) {
		this.txtClave = txtClave;
	}

	public SelectOneMenu getSomTiposUsuarios() {
		return somTiposUsuarios;
	}

	public void setSomTiposUsuarios(SelectOneMenu somTiposUsuarios) {
		this.somTiposUsuarios = somTiposUsuarios;
	}

	public CommandButton getBtnCrear() {
		return btnCrear;
	}

	public void setBtnCrear(CommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}

	public CommandButton getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(CommandButton btnModificar) {
		this.btnModificar = btnModificar;
	}

	public CommandButton getBtnBorrar() {
		return btnBorrar;
	}

	public void setBtnBorrar(CommandButton btnBorrar) {
		this.btnBorrar = btnBorrar;
	}

	public CommandButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public void setBtnLimpiar(CommandButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}

}
