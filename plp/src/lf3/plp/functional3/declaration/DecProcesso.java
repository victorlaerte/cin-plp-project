package lf3.plp.functional3.declaration;

import java.util.concurrent.Callable;

import lf3.plp.expressions2.expression.Id;
import lf3.plp.expressions2.expression.Valor;
import lf3.plp.expressions2.memory.AmbienteExecucao;
import lf3.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf3.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf3.plp.functional2.expression.ExpDeclaracao;

public class DecProcesso {

	private Id id;
	private ExpDeclaracao expDeclaracao;

	public DecProcesso(Id id, ExpDeclaracao expDeclaracao) {
		this.id = id;
		this.expDeclaracao = expDeclaracao;
	}

	public Id getId() {

		return id;
	}

	public void setId(Id id) {

		this.id = id;
	}

	public ExpDeclaracao getExpDeclaracao() {

		return expDeclaracao;
	}

	public void setExpDeclaracao(ExpDeclaracao expDeclaracao) {

		this.expDeclaracao = expDeclaracao;
	}

	public Callable<Valor> avaliar(AmbienteExecucao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		Callable<Valor> callable = new Callable<Valor>() {

			@Override
			public Valor call() throws Exception {

				return expDeclaracao.avaliar(ambiente);
			}
		};

		return callable;
	}
}
