package es.uniovi.asw.model.types.keys;

import java.io.Serializable;

public class ComentarioKey implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long citizen;
	private Long propuesta;

	ComentarioKey() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((citizen == null) ? 0 : citizen.hashCode());
		result = prime * result
				+ ((propuesta == null) ? 0 : propuesta.hashCode());
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
		ComentarioKey other = (ComentarioKey) obj;
		if (citizen == null) {
			if (other.citizen != null)
				return false;
		} else if (!citizen.equals(other.citizen))
			return false;
		if (propuesta == null) {
			if (other.propuesta != null)
				return false;
		} else if (!propuesta.equals(other.propuesta))
			return false;
		return true;
	}

}
