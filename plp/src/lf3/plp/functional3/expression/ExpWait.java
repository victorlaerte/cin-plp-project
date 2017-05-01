package lf3.plp.functional3.expression;

import lf3.plp.expressions1.util.Tipo;
import lf3.plp.expressions1.util.TipoPrimitivo;
import lf3.plp.expressions2.expression.ExpUnaria;
import lf3.plp.expressions2.expression.Expressao;
import lf3.plp.expressions2.expression.Valor;
import lf3.plp.expressions2.expression.ValorInteiro;
import lf3.plp.expressions2.memory.AmbienteCompilacao;
import lf3.plp.expressions2.memory.AmbienteExecucao;
import lf3.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf3.plp.expressions2.memory.VariavelNaoDeclaradaException;

public class ExpWait extends ExpUnaria {

	public ExpWait(Expressao exp) {
		super(exp, "wait");
	}

	@Override
	public Valor avaliar(AmbienteExecucao amb) throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException {

		Integer valor = ((ValorInteiro) getExp().avaliar(amb)).valor();

		try {
			Thread.sleep(valor * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new ValorInteiro(valor);
	}

	@Override
	protected boolean checaTipoElementoTerminal(AmbienteCompilacao amb)
			throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException {

		return (getExp().getTipo(amb).eInteiro());
	}

	@Override
	public Tipo getTipo(AmbienteCompilacao amb) {

		return TipoPrimitivo.INTEIRO;
	}

	@Override
	public ExpUnaria clone() {

		return new ExpWait(this.exp.clone());
	}
}
