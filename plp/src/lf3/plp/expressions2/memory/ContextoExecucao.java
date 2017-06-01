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
	private Map<String, BlockingQueue<String>> messageMap = new HashMap<>();
	private AmbienteExecucao parent;
	private String threadName;

	public ContextoExecucao(String threadName) {
		this.threadName = threadName;
	}

	public ContextoExecucao(String threadName, AmbienteExecucao parent) {
		this.threadName = threadName;
		this.parent = parent;
	}

	@Override
	public ContextoExecucao clone() {

		ContextoExecucaoFuncional retorno = new ContextoExecucaoFuncional(threadName);

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

		if (messageMap.containsKey(processId)) {
			BlockingQueue<String> blockingQueue = messageMap.get(processId);
			blockingQueue.put(message);

		} else {
			BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(DEFAULT_QUEUE_SIZE);
			blockingQueue.put(message);
			messageMap.put(processId, blockingQueue);
		}
	}

	@Override
	public String takeMessageFromQueue(String processId, int timeoutInSeconds) {

		BlockingQueue<String> blockingQueue = messageMap.get(processId);

		long startTime = System.currentTimeMillis();
		long waitTime = timeoutInSeconds * 1000;
		long endTime = startTime + waitTime;

		while (blockingQueue == null && System.currentTimeMillis() < endTime) {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			blockingQueue = messageMap.get(processId);
		}

		String message = "";

		if (blockingQueue != null) {
			try {
				message = blockingQueue.poll(timeoutInSeconds, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				throw new RuntimeException();
			}
		}

		return message;
	}

	@Override
	public AmbienteExecucao getParent() {

		return parent;
	}

	@Override
	public String getThreadName() {

		return threadName;
	}
}
