package lf3.plp.expressions2.memory;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import lf3.plp.expressions2.expression.Id;
import lf3.plp.expressions2.expression.Valor;
import lf3.plp.functional1.memory.ContextoExecucaoFuncional;

public class ContextoExecucao extends Contexto<Valor> implements AmbienteExecucao {

	public static final int DEFAULT_QUEUE_SIZE = 1000;
	public Map<String, BlockingQueue<String>> map = new HashMap<>();

	@Override
	public ContextoExecucao clone() {

		ContextoExecucaoFuncional retorno = new ContextoExecucaoFuncional();

		Stack<HashMap<Id, Valor>> novaPilha = new Stack<HashMap<Id, Valor>>();

		HashMap<Id, Valor> novoMap = new HashMap<Id, Valor>();
		novaPilha.add(novoMap);

		for (HashMap<Id, Valor> map : this.pilha) {
			for (Entry<Id, Valor> entry : map.entrySet()) {
				novoMap.put(entry.getKey(), entry.getValue());
			}
		}

		retorno.setPilha(novaPilha);

		return retorno;
	}

	@Override
	public void putMessageInQueue(String processId, String message) throws InterruptedException {

		if (map.containsKey(processId)) {
			BlockingQueue<String> blockingQueue = map.get(processId);
			blockingQueue.put(message);

		} else {
			BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(DEFAULT_QUEUE_SIZE);
			map.put(processId, blockingQueue);
		}
	}

	@Override
	public String takeMessageFromQueue(String processId, int timeoutInSeconds) {

		BlockingQueue<String> blockingQueue = map.get(processId);

		String message = "";

		if (blockingQueue == null) {
			//TODO: Lançar Exception
		} else {
			try {
				message = blockingQueue.poll(timeoutInSeconds, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				//TODO: decidir o comportamento
			}
		}

		return message;
	}
}
