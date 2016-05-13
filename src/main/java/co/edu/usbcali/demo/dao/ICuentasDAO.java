package co.edu.usbcali.demo.dao;

import java.util.List;

import co.edu.usbcali.demo.modelo.Cuentas;

public interface ICuentasDAO {
	public void grabar(Cuentas cuentas);
	public void modificar(Cuentas cuentas);
	public void borrar(Cuentas cuentas);
	public Cuentas consultarPorId(String id);
	public Cuentas consultarPorNumero(String numero);
	public List<Cuentas> consultarTodos();
	public List<Cuentas> consultarPorCliente(Long cliId);
}