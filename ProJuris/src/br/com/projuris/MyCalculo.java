package br.com.projuris;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.TreeMap;

public class MyCalculo implements Calculo {
	
	public static void main(String[] args) {
		Funcionario funcionario1 = new Funcionario("Assistente", "Administrativo", new BigDecimal(1000.0));
		Funcionario funcionario2 = new Funcionario("Gerente", "Administrativo", new BigDecimal(7000.70));
		Funcionario funcionario3 = new Funcionario("Diretor", "Administrativo", new BigDecimal(10000.45));
		Funcionario funcionario4 = new Funcionario("Assistente", "Financeiro", new BigDecimal(1300.9));
		Funcionario funcionario5 = new Funcionario("Gerente", "Financeiro", new BigDecimal(7500));
		Funcionario funcionario6 = new Funcionario("Diretor", "Financeiro", new BigDecimal(11000.0));
		Funcionario funcionario7 = new Funcionario("Estagiário", "Jurídico", new BigDecimal(700.4));
		Funcionario funcionario8 = new Funcionario("Assistente", "Jurídico", new BigDecimal(1800.90));
		Funcionario funcionario9 = new Funcionario("Gerente", "Jurídico", new BigDecimal(9500.50));
		Funcionario funcionario10 = new Funcionario("Diretor", "Jurídico", new BigDecimal(13000.0));
		List<Funcionario> listaFuncionario = new ArrayList<>();
		listaFuncionario.add(funcionario1);
		listaFuncionario.add(funcionario2);
		listaFuncionario.add(funcionario3);
		listaFuncionario.add(funcionario4);
		listaFuncionario.add(funcionario5);
		listaFuncionario.add(funcionario6);
		listaFuncionario.add(funcionario7);
		listaFuncionario.add(funcionario8);
		listaFuncionario.add(funcionario9);
		listaFuncionario.add(funcionario10);
		
		MyCalculo mc = new MyCalculo();
		
		List<CustoCargo> custoCargo = mc.custoPorCargo(listaFuncionario);
		for (CustoCargo cc : custoCargo) {
			System.out.println(cc.getCargo() + " -> " + cc.getCusto().setScale(2, RoundingMode.HALF_UP));
		}
		
		System.out.println("--------------");
		
		List<CustoDepartamento> custoDepartamento = mc.custoPorDepartamento(listaFuncionario);
		for (CustoDepartamento cd : custoDepartamento) {
			System.out.println(cd.getDepartamento()+ " -> " + cd.getCusto().setScale(2, RoundingMode.HALF_UP));
		}
	}
	
	@Override
	public List<CustoCargo> custoPorCargo(List<Funcionario> funcionarios) {
		
		TreeMap<String, CalculoSalarios> custoCargoMap = new TreeMap<>();
		
		for (Funcionario funcionario : funcionarios) {
			
			CalculoSalarios calculoCargo = null;
			if (custoCargoMap.containsKey(funcionario.getCargo())) {
				calculoCargo = custoCargoMap.get(funcionario.getCargo());
			} else {
				calculoCargo = new CalculoSalarios();
			}
			calculoCargo.addSalario(funcionario.getSalario());
			custoCargoMap.put(funcionario.getCargo(), calculoCargo);			
		}
		
		Collection<String> collection = custoCargoMap.keySet();
		List<CustoCargo> custoCargoList = new ArrayList<>();
		for (String string : collection) {
			CustoCargo cc = new CustoCargo();
			cc.setCargo(string);
			
			CalculoSalarios calculoCargo = custoCargoMap.get(string); 
			cc.setCusto(calculoCargo.getTotalSalario());
			custoCargoList.add(cc);
			
		}
		
		return custoCargoList;
	}
	
	@Override
	public List<CustoDepartamento> custoPorDepartamento(List<Funcionario> funcionarios) {
		TreeMap<String, CalculoSalarios> custoDepMap = new TreeMap<>();
		
		for (Funcionario funcionario : funcionarios) {
			
			CalculoSalarios calculoCargo = null;
			if (custoDepMap.containsKey(funcionario.getDepartamento())) {
				calculoCargo = custoDepMap.get(funcionario.getDepartamento());
			} else {
				calculoCargo = new CalculoSalarios();
			}
			calculoCargo.addSalario(funcionario.getSalario());
			custoDepMap.put(funcionario.getDepartamento(), calculoCargo);			
		}
		
		Collection<String> collection = custoDepMap.keySet();
		List<CustoDepartamento> custoCargoList = new ArrayList<>();
		for (String string : collection) {
			CustoDepartamento cc = new CustoDepartamento();
			cc.setDepartamento(string);
			
			CalculoSalarios calculoCargo = custoDepMap.get(string); 
			cc.setCusto(calculoCargo.getTotalSalario());
			custoCargoList.add(cc);
			
		}
		
		return custoCargoList;
	}

}

class CalculoSalarios {
	
	private BigDecimal totalSalario = new BigDecimal(0);
	
	public BigDecimal getTotalSalario() {
		return this.totalSalario;
	}
	
	public void addSalario(BigDecimal salario) {
		totalSalario = totalSalario.add(salario);
	}
}