package com.usuario.service.models;

public class Moto {

	private String marca;
	private String modelo;
	private int usuarioId;

	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * @return the usuarioId
	 */
	public int getUsuarioId() {
		return usuarioId;
	}

	/**
	 * @param usuarioId the usuarioId to set
	 */
	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	/**
	 * 
	 */
	public Moto() {
		super();
	}

}
