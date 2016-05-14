package co.edu.usbcali.demo.vista;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.demo.delegado.IDelegadoDeNegocio;
import co.edu.usbcali.demo.modelo.Consignaciones;
import co.edu.usbcali.demo.modelo.ConsignacionesId;
import co.edu.usbcali.demo.modelo.Cuentas;
import co.edu.usbcali.demo.modelo.Retiros;
import co.edu.usbcali.demo.modelo.RetirosId;
import co.edu.usbcali.demo.modelo.Usuarios;

@ViewScoped
@ManagedBean
public class transaccionesVista {

	private final static Logger log = LoggerFactory.getLogger(transaccionesVista.class);

	@ManagedProperty(value = "#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;

	private List<Usuarios> losUsuarios;

	private List<SelectItem> losTiposUsuariosItems;

	private InputText txtCantidad;
	private InputText txtLogin;
	private InputText txtCuenta;
	private InputText txtCedula;
	private InputText txtDisponible;
	private InputText txtTipoUsuario;

	private SelectOneMenu somTiposUsuarios;

	private CommandButton btnRetirar;
	private CommandButton btnConsignar;
	private CommandButton btnLimpiar;

	public String retirarAction() {
		log.info("Ingreso a retirar");

		try {
			Retiros retiro = new Retiros();

			log.info("RetCod: " + delegadoDeNegocio.consultarUltimoRetiro());
			Long retCod = delegadoDeNegocio.consultarUltimoRetiro() + 1L;
			RetirosId id = new RetirosId(retCod, txtCuenta.getValue().toString());
			retiro.setId(id);

			retiro.setRetFecha(new Date());

			Cuentas cuenta = delegadoDeNegocio.consultarCuentasPorId(txtCuenta.getValue().toString());
			retiro.setCuentas(cuenta);

			BigDecimal valorRetiro = new BigDecimal(txtCantidad.getValue().toString());
			retiro.setRetValor(valorRetiro);

			retiro.setRetDescripcion("Retiro Web");

			Usuarios usuario = delegadoDeNegocio
					.consultarUsuariosPorId(Long.parseLong(txtCedula.getValue().toString()));
			retiro.setUsuarios(usuario);

			delegadoDeNegocio.grabarRetiros(retiro);

			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Retiro efectuado con exito", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return "";
	}

	public String consignarAction() {
		log.info("Ingreso a cosnignar");
		try {
			Consignaciones consignacion = new Consignaciones();
			
			Long conCod = delegadoDeNegocio.consultarUltimaConsignacion() + 1L;
			ConsignacionesId id = new ConsignacionesId(conCod,txtCuenta.getValue().toString());
			consignacion.setId(id);
			consignacion.setConFecha(new Date());
			consignacion.setConValor(new BigDecimal(txtCantidad.getValue().toString()));
			
			Cuentas cuentas = delegadoDeNegocio.consultarCuentasPorId(txtCuenta.getValue().toString());
			consignacion.setCuentas(cuentas);
			
			Usuarios usuarios = delegadoDeNegocio.consultarUsuariosPorId(Long.parseLong(txtCedula.getValue().toString()));
			consignacion.setUsuarios(usuarios);
			
			consignacion.setConDescripcion("Consignación web");
			
			delegadoDeNegocio.grabarConsignaciones(consignacion);
			
			
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Consignación realizada con exito", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return "";
	}

	public String limpiarAction() {

		log.info("Ingreso a limpiar");
		txtLogin.resetValue();
		somTiposUsuarios.setValue("-1");

		return "";
	}

	public void txtCuentaListener() {

		Cuentas entity = null;

		try {
			String numero = txtCuenta.getValue().toString().trim();
			entity = delegadoDeNegocio.consultarCuentasPorId(numero);
		} catch (Exception e) {
		}

		if (entity == null) {
			txtCuenta.resetValue();
			txtDisponible.resetValue();
		} else {
			txtDisponible.setValue(entity.getCueSaldo());
		}

	}

	public void txtCedulaListener() {

		Usuarios entity = null;

		try {
			Long id = Long.parseLong(txtCedula.getValue().toString().trim());
			entity = delegadoDeNegocio.consultarUsuariosPorId(id);
		} catch (Exception e) {
		}

		if (entity == null) {
			txtCedula.resetValue();
			txtTipoUsuario.resetValue();
		} else {
			btnConsignar.setDisabled(false);
			try {
				String tipo = delegadoDeNegocio.consultarTiposUsuariosPorId(entity.getTiposUsuarios().getTusuCodigo())
						.getTusuNombre();
				txtTipoUsuario.setValue(tipo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void txtCantidadListener() {

		if (Double.parseDouble(txtDisponible.getValue().toString()) >= Double
				.parseDouble(txtCantidad.getValue().toString()) && txtTipoUsuario.getValue().toString() != null) {
			btnRetirar.setDisabled(false);
		} else {
			btnRetirar.setDisabled(true);
		}

	}

	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}

	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}

	public List<Usuarios> getLosUsuarios() {
		return losUsuarios;
	}

	public void setLosUsuarios(List<Usuarios> losUsuarios) {
		this.losUsuarios = losUsuarios;
	}

	public List<SelectItem> getLosTiposUsuariosItems() {
		return losTiposUsuariosItems;
	}

	public void setLosTiposUsuariosItems(List<SelectItem> losTiposUsuariosItems) {
		this.losTiposUsuariosItems = losTiposUsuariosItems;
	}

	public InputText getTxtLogin() {
		return txtLogin;
	}

	public void setTxtLogin(InputText txtLogin) {
		this.txtLogin = txtLogin;
	}

	public InputText getTxtCuenta() {
		return txtCuenta;
	}

	public void setTxtCuenta(InputText txtCuenta) {
		this.txtCuenta = txtCuenta;
	}

	public InputText getTxtCedula() {
		return txtCedula;
	}

	public void setTxtCedula(InputText txtCedula) {
		this.txtCedula = txtCedula;
	}

	public SelectOneMenu getSomTiposUsuarios() {
		return somTiposUsuarios;
	}

	public void setSomTiposUsuarios(SelectOneMenu somTiposUsuarios) {
		this.somTiposUsuarios = somTiposUsuarios;
	}

	public CommandButton getBtnRetirar() {
		return btnRetirar;
	}

	public void setBtnRetirar(CommandButton btnRetirar) {
		this.btnRetirar = btnRetirar;
	}

	public CommandButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public void setBtnLimpiar(CommandButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}

	public InputText getTxtCantidad() {
		return txtCantidad;
	}

	public void setTxtCantidad(InputText txtCantidad) {
		this.txtCantidad = txtCantidad;
	}

	public InputText getTxtDisponible() {
		return txtDisponible;
	}

	public void setTxtDisponible(InputText txtDisponible) {
		this.txtDisponible = txtDisponible;
	}

	public InputText getTxtTipoUsuario() {
		return txtTipoUsuario;
	}

	public void setTxtTipoUsuario(InputText txtTipoUsuario) {
		this.txtTipoUsuario = txtTipoUsuario;
	}

	public CommandButton getBtnConsignar() {
		return btnConsignar;
	}

	public void setBtnConsignar(CommandButton btnConsignar) {
		this.btnConsignar = btnConsignar;
	}
}
