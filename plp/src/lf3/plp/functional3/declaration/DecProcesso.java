package lf3.plp.functional3.declaration;

import lf3.plp.expressions2.expression.Id;
import lf3.plp.expressions2.expression.Valor;
import lf3.plp.expressions2.memory.AmbienteExecucao;
import lf3.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf3.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf3.plp.functional1.declaration.DeclaracaoFuncional;
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

	public void avaliar(AmbienteExecucao ambiente) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		new Thread(new Runnable() {

			@Override
			public void run() {

				/*
				 * TODO provavelmente teremos de adicionar o processo no ambiente de execucao 
				 * ou algo do tipo para recuperar depois
				 */

				Valor result = expDeclaracao.avaliar(ambiente);

				System.out.println(result.toString());

				ambiente.restaura();
			}
		}).start();

	}

	@Override
	public DeclaracaoFuncional clone() {

		// TODO Auto-generated method stub
		return null;
	}

}
