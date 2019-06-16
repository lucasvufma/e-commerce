package ecommerce.application.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ecommerce.application.domain.Cliente;
import ecommerce.application.repositories.ClienteRepository;

@Service
public class AuthService {
	private Random random = new Random();
	@Autowired
	private EmailService emailService;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private ClienteRepository clienteRepository;
	
	public void sendNewPassword(String email) {
		Cliente cliente = clienteRepository.findByEmail(email);
		if(cliente==null) {
			throw new RuntimeException();
		}
		
		String newPass = newPassword();
		cliente.setSenha(encoder.encode(newPass));
		clienteRepository.save(cliente);
		emailService.sendNewPasswordEmail(cliente,newPass);
	}

	private String newPassword() {
		char[] vetor = new char[14];
		for(int i=0;i<14;i++) {
			vetor[i]=randomChar();
		}
		return null;
	}

	private char randomChar() {
		int opt = random.nextInt(2);
		if(opt==0) {
			return (char)(random.nextInt(10)+48);
		}
		if(opt==1) {
			return (char)(random.nextInt(26)+97);
		}
		// TODO Auto-generated method stub
		return 0;
	}

}
