package br.com.vini.projetointegrador.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.vini.projetointegrador.security.UserSS;

public class UserService {

	public  static  UserSS authenticated() {
		try {
		return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch(Exception e) {
			return null;
		}
		}
	
}
