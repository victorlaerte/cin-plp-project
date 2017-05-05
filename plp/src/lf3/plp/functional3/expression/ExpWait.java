package lf3.plp.functional3.expression;

import lf3.plp.expressions1.util.TipoPrimitivo;
import lf3.plp.expressions2.expression.Expressao;
import lf3.plp.expressions2.expression.ValorInteiro;
import lf3.plp.expressions2.memory.AmbienteCompilacao;
import lf3.plp.expressions2.memory.AmbienteExecucao;
import lf3.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf3.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf3.plp.functional3.exception.TipoParametrosException;

public class ExpWait extends ExpExecutor {

	public ExpWait(Expressao... exp) {
		super("wait", exp);
	}

	@Override
	void executa(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		Expressao[] expressions = getExp();

		try {
			Expressao expressao = expressions[0];

			Integer valor = ((ValorInteiro) expressao.avaliar(amb)).valor();

			Thread.sleep(valor * 1000);

		} catch (ArrayIndexOutOfBoundsException e) {
			throw new TipoParametrosException(TipoPrimitivo.INTEIRO);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	boolean checaTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		Expressao[] expressions = getExp();

		try {

			Expressao expressao = expressions[0];

			return expressao.getTipo(amb).eInteiro();

		} catch (ArrayIndexOutOfBoundsException e) {
			throw new TipoParametrosException(TipoPrimitivo.INTEIRO);
		}
	}
}
