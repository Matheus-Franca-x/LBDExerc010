package com.fatec.LBDSpring010.servlet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fatec.LBDSpring010.controller.ProdutoController;
import com.fatec.LBDSpring010.model.Produto;

@Controller
public class ProdutoServlet 
{
	
	
	
	@RequestMapping(name = "produto", value = "/produto", method = RequestMethod.GET)
	public ModelAndView produtoGet(ModelMap model)
	{
		
		List<Produto> produtos = new ArrayList<>();
		String erro = "";
		ProdutoController pControl = new ProdutoController();
		
		try {
			produtos = pControl.listar();
		} catch (SQLException | ClassNotFoundException e)
		{
			erro = e.getMessage();
			e.printStackTrace();
		} finally {
			model.addAttribute("produtos", produtos);
			model.addAttribute("erro", erro);
		}
		return new ModelAndView("produto");
	}
	
	@RequestMapping(name = "produto", value = "/produto", method = RequestMethod.POST)
	public ModelAndView produtoPost(@RequestParam Map<String, String> allRequestParam, ModelMap model)
	{
		String cmd = allRequestParam.get("botao");
		String codigo = allRequestParam.get("codigo");
		String nome = allRequestParam.get("nome");
		String valor = allRequestParam.get("valor");
		String qtdEstoque = allRequestParam.get("qtdEstoque");
		
		//saida
		String saida = "";
		String erro = "";
		Produto p = new Produto();
		List<Produto> produtos = new ArrayList<>();
		ProdutoController pControl = new ProdutoController();
		
		
		
		try {
			if(cmd.contains("Buscar") || cmd.contains("Excluir") || cmd.contains("Alterar"))
			{
				if (pControl.validar(codigo))
				{
					saida = "Codigo inválido!";
					return new ModelAndView("produto");
				}
				p.setCodigo(Integer.parseInt(codigo));
			}
			
			if (cmd.contains("Cadastrar") || cmd.contains("Alterar"))
			{
				if (pControl.validar(valor))
				{
					saida = "Valor inválido!";
					return new ModelAndView("produto");
				}
				if (pControl.validar(qtdEstoque))
				{
					saida = "Quantiadade de estoque inválido!";
					return new ModelAndView("produto");
				}
				p.setNome(nome);
				p.setValor(Float.parseFloat(valor));
				p.setQtdEstoque(Integer.parseInt(qtdEstoque));
			}
			
			if(cmd.contains("Cadastrar"))
			{
				saida = pControl.cadastrar(p);
				p = null;
			}
			if(cmd.contains("Alterar"))
			{
				saida = pControl.alterar(p);
				p = null;
			}
			if(cmd.contains("Excluir"))
			{
				saida = pControl.excluir(p);
				p = null;
			}
			if(cmd.contains("Buscar"))
			{
				p = pControl.buscar(p);
			}
		} catch (ClassNotFoundException | SQLException e) {
			erro = e.getMessage();
		} finally {
			try {
				produtos = pControl.listar();
			} catch (ClassNotFoundException | SQLException e) {
				erro = e.getMessage();
			}
			model.addAttribute("saida", saida);
			model.addAttribute("erro", erro);
			model.addAttribute("produto", p);
			model.addAttribute("produtos", produtos);
		}
		
		return new ModelAndView("produto");
	}
	
	
	
}
