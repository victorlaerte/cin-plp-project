package lf3.plp.functional3.expression;

import lf3.plp.expressions1.util.TipoPrimitivo;
import lf3.plp.expressions2.expression.Expressao;
import lf3.plp.expressions2.expression.ValorString;
import lf3.plp.expressions2.memory.AmbienteCompilacao;
import lf3.plp.expressions2.memory.AmbienteExecucao;
import lf3.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf3.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf3.plp.functional3.exception.TipoParametrosException;

public class ExpSend extends ExpExecutor {

	public ExpSend(Expressao... exp) {
		super("send", exp);
	}

	@Override
	void executa(AmbienteExecucao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException, TipoParametrosException {
		Expressao[] expressions = getExp();

		try {
			Expressao processId = expressions[0];
			Expressao message = expressions[1];
			
			String stringProcessId = ((ValorString) processId.avaliar(amb)).valor();
			String stringMessageProcess = ((ValorString) message.avaliar(amb)).valor();
			
			amb.putMessageInQueue(stringProcessId,stringMessageProcess);
			
		} catch (ArrayIndexOutOfBoundsException | InterruptedException e) {
			throw new TipoParametrosException(TipoPrimitivo.STRING);
		}

	}

	@Override
	boolean checaTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		Expressao[] expressions = getExp();

		try {
			Expressao processId = expressions[0];
			Expressao message = expressions[1];

			return processId.getTipo(amb).eString() && message.getTipo(amb).eString();
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new TipoParametrosException(TipoPrimitivo.STRING);
		}
	}

}