package com.prolagos.sispcbackend.dto;


import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class CredenciaisDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter @Setter private String email;
	@Getter @Setter private String senha;
	
}
