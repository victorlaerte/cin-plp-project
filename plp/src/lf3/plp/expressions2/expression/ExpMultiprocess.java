package lf3.plp.expressions2.expression;

import java.util.concurrent.CompletionService;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lf3.plp.expressions1.util.Tipo;
import lf3.plp.expressions1.util.TipoPrimitivo;
import lf3.plp.expressions2.memory.AmbienteCompilacao;
import lf3.plp.expressions2.memory.AmbienteExecucao;
import lf3.plp.expressions2.memory.ContextoExecucao;
import lf3.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf3.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf3.plp.functional3.declaration.DecProcesso;

public class ExpMultiprocess implements Expressao {

	private DecProcesso[] decProcessoArray;

	public ExpMultiprocess(DecProcesso... decProcesso) {
		this.decProcessoArray = decProcesso;
	}

	@Override
	public Valor avaliar(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		Executor executor = Executors.newFixedThreadPool(100);
		CompletionService<Valor> completionService = new ExecutorCompletionService<Valor>(executor);

		for (DecProcesso decProcesso : decProcessoArray) {
			AmbienteExecucao ambExec = new ContextoExecucao(decProcesso.getId().toString(), amb);
			completionService.submit(decProcesso.avaliar(ambExec));
		}
		int received = 0;
		boolean errors = false;

		while (received < decProcessoArray.length && !errors) {
			try {
				Future<Valor> resultFuture = completionService.take();
				resultFuture.get();
				received++;
			} catch (Exception e) {
				e.printStackTrace();
				errors = true;
			}
		}

		return null;
	}

	@Override
	public boolean checaTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		amb.incrementa();
		amb.restaura();
		return true;
	}

	@Override
	public Tipo getTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		amb.incrementa();
		amb.restaura();
		return TipoPrimitivo.STRING;
	}

	@Override
	public Expressao reduzir(AmbienteExecucao ambiente) {

		return null;
	}

	@Override
	public Expressao clone() {

		return null;
	}

}
