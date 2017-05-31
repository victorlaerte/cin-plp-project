package lf3.plp.expressions2.memory;

import lf3.plp.expressions2.expression.Valor;

public interface AmbienteExecucao extends Ambiente<Valor> {

	public AmbienteExecucao clone();

	public AmbienteExecucao getParent();

	public void putMessageInQueue(String processId, String message) throws InterruptedException;

	public String takeMessageFromQueue(String processId, int timeoutInSeconds);

}
