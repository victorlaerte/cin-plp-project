package lf3.plp.expressions2.expression;

import java.util.Map;

import lf3.plp.expressions1.util.Tipo;
import lf3.plp.expressions2.memory.AmbienteCompilacao;
import lf3.plp.expressions2.memory.AmbienteExecucao;
import lf3.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf3.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf3.plp.functional3.declaration.DecProcesso;

public class ExpProcessoDeclaracao implements Expressao {
	private DecProcesso decProcesso;
	
	public ExpProcessoDeclaracao(DecProcesso decProcesso) {
		this.decProcesso = decProcesso;
	}

	@Override
	public Valor avaliar(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		amb.incrementa();
		Valor result = decProcesso.getExpDeclaracao().avaliar(amb);
		amb.restaura();
		return result;
	}

	@Override
	public boolean checaTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		amb.incrementa();
		boolean result = decProcesso.getExpDeclaracao().checaTipo(amb);
		amb.restaura();
		return result;
	}

	@Override
	public Tipo getTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		amb.incrementa();
		Tipo result = decProcesso.getExpDeclaracao().getTipo(amb);
		amb.restaura();
		return result;
	}

	@Override
	public Expressao reduzir(AmbienteExecucao ambiente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expressao clone() {
		return new ExpProcessoDeclaracao((DecProcesso) decProcesso.clone());
	}

}
