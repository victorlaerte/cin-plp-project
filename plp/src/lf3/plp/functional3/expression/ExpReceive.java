package lf3.plp.functional3.expression;

import lf3.plp.expressions2.expression.Expressao;
import lf3.plp.expressions2.expression.Valor;
import lf3.plp.expressions2.expression.ValorInteiro;
import lf3.plp.expressions2.expression.ValorString;
import lf3.plp.expressions2.memory.AmbienteCompilacao;
import lf3.plp.expressions2.memory.AmbienteExecucao;
import lf3.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf3.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf3.plp.functional3.exception.TipoParametrosException;

public class ExpReceive extends ExpPromise {

	public ExpReceive(Expressao... exp) {
		super("receive", exp);
	}

	@Override
	boolean checaTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		Expressao[] expressions = getExp();

		try {
			Expressao processId = expressions[0];
			Expressao timeoutInSeconds = expressions[1];

			return processId.getTipo(amb).eString() && timeoutInSeconds.getTipo(amb).eInteiro();
		} catch (ArrayIndexOutOfBoundsException e) {
			//TODO: decidir o comportamento
		}
		return false;
	}

	@Override
	public Valor avalia(AmbienteExecucao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException, TipoParametrosException {

		Expressao[] expressions = getExp();

		try {
			Expressao processId = expressions[0];
			Expressao timeoutInSeconds = expressions[1];

			String stringProcessId = ((ValorString) processId.avaliar(amb)).valor();
			Integer integerTimeoutInSeconds = ((ValorInteiro) timeoutInSeconds.avaliar(amb)).valor();

			return new ValorString(amb.takeMessageFromQueue(stringProcessId, integerTimeoutInSeconds));

		} catch (ArrayIndexOutOfBoundsException e) {
			//TODO: decidir o comportamento
		}

		return null;
	}

}
