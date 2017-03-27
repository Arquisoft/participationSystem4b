package es.uniovi.asw.model;

import es.uniovi.asw.model.exception.CitizenException;
import es.uniovi.asw.model.util.EncryptMD5;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TCITIZENS")
public class Citizen {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private String nombre;
	@NotNull
	private String apellidos;
	@NotNull
	private String email;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;

	@NotNull
	private String residencia;

	@NotNull
	private String nacionalidad;

	@NotNull
	private String dni;

	@NotNull
	private String password;

	private boolean isAdmin;

	private Calendar calendar = GregorianCalendar.getInstance();

	@OneToMany(mappedBy = "citizen", fetch = FetchType.EAGER)
	private Set<Comentario> comentarios = new HashSet<Comentario>();

	Citizen() {
	}

	public Citizen(String nombre, String apellidos, String email,
			Date fechaNacimiento, String residencia, String nacionalidad,
			String dni) throws NoSuchAlgorithmException, CitizenException {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		setFechaNacimiento(fechaNacimiento);
		this.residencia = residencia;
		this.nacionalidad = nacionalidad;
		this.dni = dni;
		this.password = generarPassword();
		this.isAdmin = false;
	}

	public long getId() {
		return id;
	}

	void setId(long id) throws CitizenException {
		if (id > 0)
			this.id = id;
		else
			throw new CitizenException("El ID es menor que 0");
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento)
			throws CitizenException {
		try {
			if (fechaNacimiento.before(calendar.getTime()))
				this.fechaNacimiento = fechaNacimiento;
			else
				throw new CitizenException(
						"La fecha de nacimiento es posterior al dia actual.");
		} catch (NullPointerException e) {
			throw new CitizenException(
					"La fecha de nacimiento no puede ser null.");
		}
	}

	public String getResidencia() {
		return residencia;
	}

	public void setResidencia(String residencia) {
		this.residencia = residencia;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws NoSuchAlgorithmException,
			CitizenException {
		this.password = new EncryptMD5().encrypting(password);
	}

	private String generarPassword() throws NoSuchAlgorithmException,
			CitizenException {
		String password = "";
		Random r = new Random(calendar.getTimeInMillis());
		int i = 0;

		while (i < 10) {
			char c = (char) r.nextInt(255);
			if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')
					|| (c >= 'a' && c <= 'z')) {
				password += c;
				i++;
			}
		}

		return new EncryptMD5().encrypting(password);
	}

	public Set<Comentario> _getComentarios() {
		return comentarios;
	}

	public void setComentarios(Set<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "Citizen [id=" + id + ", nombre=" + nombre + ", apellidos="
				+ apellidos + ", email=" + email + ", fechaNacimiento="
				+ fechaNacimiento + ", residencia=" + residencia
				+ ", nacionalidad=" + nacionalidad + ", dni=" + dni
				+ ", password=" + password + ", calendar=" + calendar
				+ ", comentarios=" + comentarios + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Citizen other = (Citizen) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
