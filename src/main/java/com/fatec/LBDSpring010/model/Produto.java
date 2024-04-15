package com.fatec.LBDSpring010.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Produto 
{
	int codigo;
	String nome;
	float valor;
	int qtdEstoque;
	
}
