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
public class ProdutoInfoServlet 
{
	
	
	
	@RequestMapping(name = "produto_info", value = "/produto_info", method = RequestMethod.GET)
	public ModelAndView produtoInfoGet(ModelMap model)
	{
		int qtdEstoque = 0;
		
		model.addAttribute("qtdEstoque", qtdEstoque);
		
		return new ModelAndView("produto_info");
	}
	
	@RequestMapping(name = "produto_info", value = "/produto_info", method = RequestMethod.POST)
	public ModelAndView produtoInfoPost(@RequestParam Map<String, String> allRequestParam, ModelMap model)
	{
		String cmd = allRequestParam.get("botao");
		String valor = allRequestParam.get("valor");
		
		//saida
		String saida = "";
		String erro = "";
		List<Produto> produtos = new ArrayList<>();
		int qtdEstoque = 0;
		ProdutoController pControl = new ProdutoController();
		
		
		
		try {
			if (pControl.validar(valor))
			{
				saida = "Valor inválido!";
				return new ModelAndView("produto_info");
			}
			
			if(cmd.contains("qtdEstoque"))
			{
				qtdEstoque = pControl.qtdEstoque(Integer.parseInt(valor));
			}
			if(cmd.contains("qtdProduto"))
			{
				produtos = pControl.qtdProduto(Integer.parseInt(valor));
			}
		} catch (ClassNotFoundException | SQLException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("saida", saida);
			model.addAttribute("erro", erro);
			model.addAttribute("qtdEstoque", qtdEstoque);
			model.addAttribute("qtdProduto", produtos);
		}
		
		return new ModelAndView("produto_info");
	}
	
	
	
}
