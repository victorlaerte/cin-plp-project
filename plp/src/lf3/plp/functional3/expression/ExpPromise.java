package lf3.plp.functional3.expression;

import lf3.plp.expressions2.expression.Expressao;
import lf3.plp.expressions2.memory.AmbienteCompilacao;
import lf3.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf3.plp.expressions2.memory.VariavelNaoDeclaradaException;

public abstract class ExpPromise implements Expressao {

	/**
	 * Expressao contida pela expressao unaria
	 */
	protected Expressao[] exp;

	/**
	 * Representacao do operador desta expressao unaria.
	 */
	private String operador;

	/**
	 * Construtor da classe.
	 * 
	 * @param exp
	 *            expressao contida pela expressao unaria.
	 */
	public ExpPromise(String operador, Expressao... exp) {
		this.exp = exp;
		this.operador = operador;
	}

	/**
	 * Retorna a expressao contida pela expressao unaria
	 * 
	 * @return a expressao contida pela expressao unaria
	 */
	public Expressao[] getExp() {

		return exp;
	}

	@Override
	public String toString() {

		return String.format("%s %s", operador, exp);
	}

	/**
	 * Retorna a representacao do operador desta expressao unaria.
	 * 
	 * @return a representacao do operador desta expressao unaria.
	 */
	public String getOperador() {

		return operador;
	}

	/**
	 * Realiza a verificacao de tipos desta expressao.
	 *
	 * @param amb o ambiente que contem o mapeamento entre identificadores
	 *            e tipos.
	 * @return <code>true</code> se os tipos da expressao sao validos;
	 *         <code>false</code> caso contrario.
	 */
	@Override
	public abstract boolean checaTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException;

	@Override
	public Expressao clone() {

		return null;
	}
}
