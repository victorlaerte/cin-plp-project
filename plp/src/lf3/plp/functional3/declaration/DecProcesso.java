package lf3.plp.functional3.declaration;

import java.util.Map;

import lf3.plp.expressions1.util.Tipo;
import lf3.plp.expressions2.expression.Id;
import lf3.plp.expressions2.expression.Valor;
import lf3.plp.expressions2.memory.AmbienteCompilacao;
import lf3.plp.expressions2.memory.AmbienteExecucao;
import lf3.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf3.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf3.plp.functional1.declaration.DeclaracaoFuncional;
import lf3.plp.functional2.expression.ExpDeclaracao;
import lf3.plp.functional2.expression.ValorFuncao;

public class DecProcesso implements DeclaracaoFuncional {
	private ExpDeclaracao expDeclaracao;
	

	public DecProcesso(ExpDeclaracao expDeclaracao) {
		this.expDeclaracao = expDeclaracao;
	}

	@Override
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return (expDeclaracao.checaTipo(ambiente));
	}

	@Override
	public void elabora(AmbienteExecucao amb, Map<Id, Valor> declaracoes, Map<Id, ValorFuncao> declaracoesFuncoes)
			throws VariavelJaDeclaradaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void elabora(AmbienteCompilacao amb, Map<Id, Tipo> tipos) throws VariavelJaDeclaradaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void incluir(AmbienteExecucao amb, Map<Id, Valor> declaracoes, Map<Id, ValorFuncao> declaracoesFuncoes)
			throws VariavelJaDeclaradaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void incluir(AmbienteCompilacao amb, Map<Id, Tipo> tipos, boolean incluirCuringa)
			throws VariavelJaDeclaradaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reduzir(AmbienteExecucao amb) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DeclaracaoFuncional clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
