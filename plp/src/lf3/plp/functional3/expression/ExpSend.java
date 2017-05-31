package lf3.plp.functional3.expression;

import static lf3.plp.functional3.util.AmbienteUtil.getMainThreadContext;

import lf3.plp.expressions1.util.Tipo;
import lf3.plp.expressions1.util.TipoPrimitivo;
import lf3.plp.expressions2.expression.Expressao;
import lf3.plp.expressions2.expression.Valor;
import lf3.plp.expressions2.expression.ValorString;
import lf3.plp.expressions2.memory.AmbienteCompilacao;
import lf3.plp.expressions2.memory.AmbienteExecucao;
import lf3.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf3.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf3.plp.functional3.exception.TipoParametrosException;;

public class ExpSend extends ExpExecutor {

	public ExpSend(Expressao... exp) {
		super("send", exp);
	}

	@Override
	public boolean checaTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		Expressao[] expressions = getExp();

		try {
			Expressao processId = expressions[0];
			Expressao message = expressions[1];

			return processId.getTipo(amb).eString() && message.getTipo(amb).eString();
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new TipoParametrosException(TipoPrimitivo.STRING);
		}
	}

	@Override
	public Tipo getTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		return TipoPrimitivo.STRING;
	}

	@Override
	public Valor avaliar(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		Expressao[] expressions = getExp();

		try {
			Expressao processId = expressions[0];
			Expressao message = expressions[1];

			String valorProcessId = ((ValorString) processId.avaliar(amb)).valor();
			String valorMessageProcess = ((ValorString) message.avaliar(amb)).valor();

			AmbienteExecucao mainThreadContext = getMainThreadContext(amb);
			mainThreadContext.putMessageInQueue(valorProcessId, valorMessageProcess);
			System.out.println("Send msg: " + valorMessageProcess);
			System.out.println("To: " + valorProcessId);

			return new ValorString(valorMessageProcess);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new TipoParametrosException(TipoPrimitivo.STRING);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ExpExecutor clone() {

		return new ExpSend(exp.clone());
	}

	@Override
	public Expressao reduzir(AmbienteExecucao ambiente) {

		for (Expressao expressao : exp) {
			expressao.reduzir(ambiente);
		}

		return this;
	}

}
