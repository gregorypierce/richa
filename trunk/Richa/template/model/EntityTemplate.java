package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="@modelObjectName@")
public class @modelObjectName@ implements Serializable
{
	@Id
	@GeneratedValue
	@Column(name="@modelObjectName@_ID")
	private Long 			id;
	
	public Long getId()
	{
		return id;
	}

	private void setid(Long id)
	{
		this.id = id;
	}
}