package lf3.plp.functional3;

import lf3.plp.expressions2.expression.Expressao;
import lf3.plp.expressions2.expression.Valor;
import lf3.plp.expressions2.memory.AmbienteCompilacao;
import lf3.plp.expressions2.memory.AmbienteExecucao;
import lf3.plp.expressions2.memory.ContextoCompilacao;
import lf3.plp.expressions2.memory.ContextoExecucao;
import lf3.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf3.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf3.plp.functional3.util.Constantes;

public class Programa {

	private Expressao exp;

	public Programa(Expressao exp) {
		this.exp = exp;
	}

	public Valor executar() throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException {

		AmbienteExecucao ambExec = new ContextoExecucao(Constantes.MAIN_THREAD_NAME);
		return exp.avaliar(ambExec);
	}

	public boolean checaTipo() throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException {

		AmbienteCompilacao ambComp = new ContextoCompilacao();
		return exp.checaTipo(ambComp);
	}

	public Expressao getExpressao() {

		return exp;
	}

}
