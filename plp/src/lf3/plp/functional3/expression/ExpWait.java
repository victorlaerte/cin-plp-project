package lf3.plp.functional3.expression;

import java.util.ArrayList;
import java.util.List;

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
import lf3.plp.functional3.exception.TipoParametrosException;

public class ExpWait extends ExpUnaria {

	private List<Expressao> callbacks = new ArrayList<Expressao>();

	public ExpWait(Expressao exp, Expressao[] array) {
		super(exp, "wait");

		if (array.length > 0) {
			for (Expressao callback : array) {
				callbacks.add(callback);
			}
		}
	}

	@Override
	public Valor avaliar(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		try {
			Expressao expressao = exp;

			Integer valor = ((ValorInteiro) expressao.avaliar(amb)).valor();

			Thread.sleep(valor * 1000);

			for (Expressao callback : callbacks) {
				callback.avaliar(amb);
			}

			return new ValorInteiro(valor);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new TipoParametrosException(TipoPrimitivo.INTEIRO);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Tipo getTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		return TipoPrimitivo.INTEIRO;
	}

	@Override
	protected boolean checaTipoElementoTerminal(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		return exp.getTipo(amb).eInteiro();
	}

	@Override
	public ExpUnaria clone() {

		List<Expressao> callbackClone = new ArrayList<>(callbacks.size());

		for (Expressao callback : callbacks) {
			callbackClone.add(callback.clone());
		}
		return new ExpWait(exp.clone(), callbacks.toArray(new Expressao[callbacks.size()]));
	}
}
