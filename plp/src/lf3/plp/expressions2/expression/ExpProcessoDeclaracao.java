package lf3.plp.expressions2.expression;

import lf3.plp.expressions1.util.Tipo;
import lf3.plp.expressions1.util.TipoPrimitivo;
import lf3.plp.expressions2.memory.AmbienteCompilacao;
import lf3.plp.expressions2.memory.AmbienteExecucao;
import lf3.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf3.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf3.plp.functional3.declaration.DecProcesso;

public class ExpProcessoDeclaracao implements Expressao {

	private DecProcesso[] decProcessoArray;

	public ExpProcessoDeclaracao(DecProcesso... decProcesso) {
		this.decProcessoArray = decProcesso;
	}

	@Override
	public Valor avaliar(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		amb.incrementa();
		for (DecProcesso decProcesso : decProcessoArray) {
			decProcesso.avaliar(amb);
		}
		return null;
	}

	@Override
	public boolean checaTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		amb.incrementa();
		amb.restaura();
		return true;
	}

	@Override
	public Tipo getTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		amb.incrementa();
		amb.restaura();
		return TipoPrimitivo.STRING;
	}

	@Override
	public Expressao reduzir(AmbienteExecucao ambiente) {

		return null;
	}

	@Override
	public Expressao clone() {

		return null;
	}

}
