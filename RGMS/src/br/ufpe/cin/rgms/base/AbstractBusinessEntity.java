package br.ufpe.cin.rgms.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import br.ufpe.cin.rgms.publicacao.modelo.Publicacao;

@MappedSuperclass
public abstract class AbstractBusinessEntity implements BusinessEntity, Serializable {
	private static final long serialVersionUID = -8839932833719886409L;

	protected int id;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.getId());
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean equals = false;

		if (obj instanceof Publicacao) {
			Publicacao publicacao = (Publicacao) obj;

			if(this.id == publicacao.id){
				equals = true;
			}
		}

		return equals;
	}
	
}
