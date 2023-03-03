package com.usuario.service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usuario.service.entity.Usuario;
import com.usuario.service.feignclient.CarroFeignClient;
import com.usuario.service.feignclient.MotoFeignClient;
import com.usuario.service.models.Carro;
import com.usuario.service.models.Moto;
import com.usuario.service.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CarroFeignClient carroFeignClient;

	@Autowired
	private MotoFeignClient motoFeignClient;

	// Metodo con RestTemplate
	public List<Carro> getCarros(int usuarioId) {
		@SuppressWarnings("unchecked")
		List<Carro> carros = restTemplate.getForObject("http://localhost:8002/carro/usuario/" + usuarioId, List.class);
		return carros;
	}

	// Metodo con RestTemplate
	public List<Moto> getMotos(int usuarioId) {
		@SuppressWarnings("unchecked")
		List<Moto> motos = restTemplate.getForObject("http://localhost:8003/moto/usuario/" + usuarioId, List.class);
		return motos;
	}

	// Método con OpenFeign
	public Carro saveCarro(int usuarioId, Carro carro) {

		carro.setUsuarioId(usuarioId);
		Carro nuevoCarro = carroFeignClient.save(carro);
		return nuevoCarro;

	}

	// Método con OpenFeign
	public Moto saveMoto(int idUsuario, Moto moto) {
		moto.setUsuarioId(idUsuario);
		Moto nuevaMoto = motoFeignClient.save(moto);
		return nuevaMoto;
	}

	// Obtención de vehiculos mediante OpenFeign
	public Map<String, Object> getUSuarioAndVehiculos(int usuarioId) {
		Map<String, Object> resultado = new HashMap<>();
		Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
		if (usuario == null) {
			resultado.put("Mensaje", "El usuario no existe");
			return resultado;
		}

		resultado.put("usuario", usuario);
		List<Carro> carros = carroFeignClient.getCarros(usuario.getId());
		if (carros == null) {
			resultado.put("Carros", "El usuario no tiene carros");
		} else {
			resultado.put("Carros", carros);
		}

		List<Moto> motos = motoFeignClient.getMotos(usuarioId);
		if (motos == null) {
			resultado.put("Motos", "El usuario no tiene motos");
		} else {
			resultado.put("Motos", motos);
		}
		return resultado;
	}

	public List<Usuario> getAll() {
		return usuarioRepository.findAll();
	}

	public Usuario getUsuarioById(int id) {
		return usuarioRepository.findById(id).orElse(null);
	}

	public Usuario save(Usuario usuario) {
		Usuario nuevoUsuario = usuarioRepository.save(usuario);
		return nuevoUsuario;
	}
}
