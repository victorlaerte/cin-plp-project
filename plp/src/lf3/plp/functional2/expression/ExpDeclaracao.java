package lf3.plp.functional2.expression;

import java.util.HashMap;
import java.util.Map;

import lf3.plp.expressions1.util.Tipo;
import lf3.plp.expressions2.expression.Expressao;
import lf3.plp.expressions2.expression.Id;
import lf3.plp.expressions2.expression.Valor;
import lf3.plp.expressions2.memory.AmbienteCompilacao;
import lf3.plp.expressions2.memory.AmbienteExecucao;
import lf3.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf3.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf3.plp.functional1.declaration.DeclaracaoFuncional;

public class ExpDeclaracao implements Expressao {

	protected DeclaracaoFuncional declaracao;
	protected Expressao expressao;

	public ExpDeclaracao(DeclaracaoFuncional declaracao, Expressao expressaoArg) {
		this.declaracao = declaracao;
		expressao = expressaoArg;
	}

	/**
	 * Retorna uma representacao String desta expressao. Util para depuracao.
	 * 
	 * @return uma representacao String desta expressao.
	 */
	//	@Override
	//	public String toString() {
	//		return String.format("let %s in %s",
	//				listToString(seqdecFuncional, ","), expressao);
	//	}

	@Override
	public Valor avaliar(AmbienteExecucao ambiente) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		ambiente.incrementa();

		// Como declaracoes feitas neste nivel nao devem ter influencia
		// mutua, armazenamos os valores em uma tabela auxiliar, para depois
		// fazer o mapeamento.
		Map<Id, Valor> auxIdValor = new HashMap<Id, Valor>();
		Map<Id, ValorFuncao> auxIdValorFuncao = new HashMap<Id, ValorFuncao>();

		declaracao.elabora(ambiente, auxIdValor, auxIdValorFuncao);
		declaracao.incluir(ambiente, auxIdValor, auxIdValorFuncao);

		Valor vresult = expressao.avaliar(ambiente);

		if (vresult instanceof ValorFuncao)
			vresult.reduzir(ambiente);

		ambiente.restaura();
		return vresult;
	}

	/**
	 * Realiza a verificacao de tipos desta expressao.
	 * 
	 * @param amb
	 *            o ambiente de compila��o.
	 * @return <code>true</code> se os tipos da expressao sao validos;
	 *         <code>false</code> caso contrario.
	 * @exception VariavelNaoDeclaradaException
	 *                se existir um identificador nao declarado no ambiente.
	 * @exception VariavelNaoDeclaradaException
	 *                se existir um identificador declarado mais de uma vez no
	 *                mesmo bloco do ambiente.
	 */
	@Override
	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		ambiente.incrementa();

		boolean result = false;
		try {
			result = declaracao.checaTipo(ambiente);
			if (result) {
				Map<Id, Tipo> tipos = new HashMap<Id, Tipo>();
				declaracao.elabora(ambiente, tipos);
				declaracao.incluir(ambiente, tipos, true);
				result = expressao.checaTipo(ambiente);
			}
		} finally {
			ambiente.restaura();
		}
		return result;
	}

	/**
	 * Retorna os tipos possiveis desta expressao.
	 * 
	 * @param amb
	 *            o ambiente de compila��o.
	 * @return os tipos possiveis desta expressao.
	 * @exception VariavelNaoDeclaradaException
	 *                se existir um identificador nao declarado no ambiente.
	 * @exception VariavelNaoDeclaradaException
	 *                se existir um identificador declarado mais de uma vez no
	 *                mesmo bloco do ambiente.
	 * @precondition this.checaTipo();
	 */
	@Override
	public Tipo getTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		ambiente.incrementa();

		Tipo vresult = null;

		Map<Id, Tipo> tipos = new HashMap<Id, Tipo>();
		declaracao.elabora(ambiente, tipos);
		declaracao.incluir(ambiente, tipos, false);
		vresult = expressao.getTipo(ambiente);
		ambiente.restaura();
		return vresult;
	}

	/**
	 * Returns the expressao.
	 * 
	 * @return Expressao
	 */
	public Expressao getExpressao() {

		return expressao;
	}

	@Override
	public Expressao reduzir(AmbienteExecucao ambiente) {

		ambiente.incrementa();

		declaracao.reduzir(ambiente);

		//Comentado, pois fazia com que uma recurs�o de lista entrasse em loop.
		//this.expressao = expressao.reduzir(ambiente);

		ambiente.restaura();

		return this;
	}

	@Override
	public ExpDeclaracao clone() {

		ExpDeclaracao retorno;
		retorno = new ExpDeclaracao(declaracao.clone(), this.expressao.clone());
		return retorno;
	}

}
