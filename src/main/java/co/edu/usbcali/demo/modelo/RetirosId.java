package co.edu.usbcali.demo.modelo;
// Generated May 4, 2016 10:21:41 PM by Hibernate Tools 4.0.0

/**
 * RetirosId generated by hbm2java
 */
public class RetirosId implements java.io.Serializable {

	private long retCodigo;
	private String cueNumero;

	public RetirosId() {
	}

	public RetirosId(long retCodigo, String cueNumero) {
		this.retCodigo = retCodigo;
		this.cueNumero = cueNumero;
	}

	public long getRetCodigo() {
		return this.retCodigo;
	}

	public void setRetCodigo(long retCodigo) {
		this.retCodigo = retCodigo;
	}

	public String getCueNumero() {
		return this.cueNumero;
	}

	public void setCueNumero(String cueNumero) {
		this.cueNumero = cueNumero;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RetirosId))
			return false;
		RetirosId castOther = (RetirosId) other;

		return (this.getRetCodigo() == castOther.getRetCodigo())
				&& ((this.getCueNumero() == castOther.getCueNumero()) || (this.getCueNumero() != null
						&& castOther.getCueNumero() != null && this.getCueNumero().equals(castOther.getCueNumero())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getRetCodigo();
		result = 37 * result + (getCueNumero() == null ? 0 : this.getCueNumero().hashCode());
		return result;
	}

}
