package com.usuario.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.service.entity.Usuario;
import com.usuario.service.models.Carro;
import com.usuario.service.models.Moto;
import com.usuario.service.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarios(){
		List<Usuario> usuarios = usuarioService.getAll();
		if(usuarios.isEmpty()){
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(usuarios);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") int id){
		Usuario usuario = usuarioService.getUsuarioById(id);
		if(usuario == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
		Usuario nuevoUsuario = usuarioService.save(usuario);
		return ResponseEntity.ok(nuevoUsuario);
	}
	
	//Método con RestTemplate
	@GetMapping("/carros/{usuarioId}")
	public ResponseEntity<List<Carro>> listarCarros(@PathVariable("usuarioId") int usuarioId){
		
		Usuario usuario = usuarioService.getUsuarioById(usuarioId);
		if(usuario == null) {
			return ResponseEntity.noContent().build();
		}
		List<Carro> carros = usuarioService.getCarros(usuarioId);
		return ResponseEntity.ok(carros);
	}
	//Método con RestTemplate
	@GetMapping("/motos/{usuarioId}")
	public ResponseEntity<List<Moto>> listarMotos(@PathVariable("usuarioId") int usuarioId){
		
		Usuario usuario = usuarioService.getUsuarioById(usuarioId);
		if(usuario == null) {
			return ResponseEntity.noContent().build();
		}
		List<Moto> motos = usuarioService.getMotos(usuarioId);
		return ResponseEntity.ok(motos);
	}
	
	//Metodo con OpenFeign
	@PostMapping("/carro/{usuarioId}")
	public ResponseEntity<Carro> guardarCarro(@PathVariable("usuarioId") int usuarioId, @RequestBody Carro carro){
		Carro nuevoCarro = usuarioService.saveCarro(usuarioId, carro);
		return ResponseEntity.ok(nuevoCarro);
	}
	
	//Metodo con OpenFeign
	 @PostMapping("/moto/{idUsuario}")
	 public ResponseEntity<Moto> saveMoto(@PathVariable("idUsuario") int idUsuario, @RequestBody Moto moto){
		 Moto nuevaMoto = usuarioService.saveMoto(idUsuario, moto);
		 return ResponseEntity.ok(moto);
	 }
	 
	 //Obtención de vehiculos por OpenFeign
	 @GetMapping("/vehiculos/{usuarioId}")
	 public ResponseEntity<Map<String, Object>> getVehiculos(@PathVariable("usuarioId") int usuarioId){
		 Map<String, Object> listarVehiculos = usuarioService.getUSuarioAndVehiculos(usuarioId);
		 
		 return ResponseEntity.ok(listarVehiculos);
	 }
	
}
