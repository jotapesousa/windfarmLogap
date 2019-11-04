package br.com.logap.windfarm.model;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Entidade implements Serializable {

	public Entidade() {
    }

    public abstract Integer getId();

    public abstract void setId(Integer id);

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            Entidade other = (Entidade)obj;
            if (this.getId() == null) {
                if (other.getId() != null) {
                    return false;
                }
            } else if (!this.getId().equals(other.getId())) {
                return false;
            }

            return true;
        }
    }
}

