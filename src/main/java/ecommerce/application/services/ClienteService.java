package ecommerce.application.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ecommerce.application.domain.Cliente;
import ecommerce.application.domain.Endereco;
import ecommerce.application.dto.ClienteNewDTO;
import ecommerce.application.enums.Perfil;
import ecommerce.application.repositories.ClienteRepository;
import ecommerce.application.security.UserSecurity;
import ecommerce.application.services.exceptions.AuthorizationException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	@Value("${img.profile.size}")
	private Integer size;
	
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Autowired
	private S3Service S3Service;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private BCryptPasswordEncoder pe;

	
	public List<Cliente> findAll(){
		return repository.findAll();
	}
	public Optional<Cliente> find(Integer id) {
		UserSecurity user = UserService.authenticated();
		if(user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getCod_usuario())) {
			throw new AuthorizationException("Acesso Negado!");
		}
		return repository.findById(id);
	}
	public Cliente post(ClienteNewDTO objectDTO) {
		Cliente object = this.fromDTO(objectDTO);
		object.setCod_cliente(null);
		object.getEndereco().setCliente(object);
		return repository.save(object);
	}
	public void deleteById(Integer id) {
		find(id);
		repository.deleteById(id);
	}
	public Cliente put(Cliente obj) {
		Optional<Cliente> newObj = find(obj.getCod_cliente());
		updateData(newObj.get(),obj);
		return repository.save(newObj.get());
}
	private void updateData(Cliente newObj, Cliente obj) {
		if(obj.getNome()!=null) {
			newObj.setNome(obj.getNome());
		}
		if (obj.getEmail()!=null) {
			newObj.setEmail(obj.getEmail());
		}
		if (obj.getEndereco()!=null) {
			newObj.getEndereco().updateEnd(obj.getEndereco());
		}
	}
	
	
	public URI uploadProfilePicture(MultipartFile file) {
		UserSecurity user = UserService.authenticated();
		if(user==null) {
			throw new RuntimeException("Acesso negado!");
		}
		
		BufferedImage jpgImage= imageService.getJpgImageFromFile(file);
		String fileName = prefix+user.getCod_usuario()+".jpg";
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		return S3Service.uploadFile(imageService.getInputStream(jpgImage,"jpg"),fileName,"image");
	
	}

	public  Page<Cliente> findPage(Integer page, Integer linesPerPage,String direction, String orderBy){
		return repository.findAll(PageRequest.of(page,linesPerPage, Direction.valueOf(direction),orderBy));
	}
	
	public Cliente findByEmail(String email) {
		UserSecurity user = UserService.authenticated();
		if(user==null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso Negado!");
		}
		Cliente cliente = repository.findByEmail(email);
		if (cliente == null) {
			throw new RuntimeException ("Erro inesperado!");
		}
		return cliente;
	}
	
	public Cliente fromDTO(ClienteNewDTO clienteDTO) {
		Cliente cliente = new Cliente();
		Endereco endereco = new Endereco();
		cliente.setSenha(pe.encode(clienteDTO.getSenha()));
		cliente.setNome(clienteDTO.getNome());
		cliente.setEmail(clienteDTO.getEmail());
		cliente.setTelefone1(clienteDTO.getTelefone1());
		if(clienteDTO.getTelefone2()!=null) {
			cliente.setTelefone2(clienteDTO.getTelefone2());
		}
		cliente.setCpf(clienteDTO.getCpf());
		// para o endereço
		endereco.setBairro(clienteDTO.getBairro());
		endereco.setCep(clienteDTO.getCep());
		endereco.setCidade(clienteDTO.getCidade());
		endereco.setEstado(clienteDTO.getEstado());
		endereco.setLogradouro(clienteDTO.getLogradouro());
		endereco.setNumero(clienteDTO.getNumero());
		endereco.setComplemento(clienteDTO.getComplemento());
		cliente.setEndereco(endereco);
		return cliente;
		
	}
		
}