package lf3.plp.functional3.expression;

import static lf3.plp.functional3.util.AmbienteUtil.getMainThreadContext;

import lf3.plp.expressions1.util.Tipo;
import lf3.plp.expressions1.util.TipoPrimitivo;
import lf3.plp.expressions2.expression.ExpUnaria;
import lf3.plp.expressions2.expression.Expressao;
import lf3.plp.expressions2.expression.Valor;
import lf3.plp.expressions2.expression.ValorInteiro;
import lf3.plp.expressions2.expression.ValorString;
import lf3.plp.expressions2.memory.AmbienteCompilacao;
import lf3.plp.expressions2.memory.AmbienteExecucao;
import lf3.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf3.plp.expressions2.memory.VariavelNaoDeclaradaException;;

public class ExpReceive extends ExpUnaria {

	public ExpReceive(Expressao exp) {
		super(exp, "receive");
	}

	@Override
	public boolean checaTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		Expressao timeoutInSeconds = getExp();

		return timeoutInSeconds.getTipo(amb).eInteiro();
	}

	@Override
	public Valor avaliar(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		Expressao timeoutInSeconds = getExp();

		Integer integerTimeoutInSeconds = ((ValorInteiro) timeoutInSeconds.avaliar(amb)).valor();

		String processId = amb.getThreadName();
		AmbienteExecucao mainThreadContext = getMainThreadContext(amb);

		String messageReceived = mainThreadContext.takeMessageFromQueue(processId, integerTimeoutInSeconds);
		System.out.println("Message received: " + messageReceived);

		return new ValorString(messageReceived);
	}

	@Override
	public Tipo getTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		return TipoPrimitivo.STRING;
	}

	@Override
	public Expressao reduzir(AmbienteExecucao ambiente) {

		exp.reduzir(ambiente);
		return this;
	}

	@Override
	public ExpUnaria clone() {

		return new ExpReceive(exp.clone());
	}

	@Override
	protected boolean checaTipoElementoTerminal(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		return exp.getTipo(amb).eString();
	}

}
