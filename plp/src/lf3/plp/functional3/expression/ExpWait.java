package lf3.plp.functional3.expression;

import lf3.plp.expressions1.util.Tipo;
import lf3.plp.expressions1.util.TipoPrimitivo;
import lf3.plp.expressions2.expression.ExpUnaria;
import lf3.plp.expressions2.expression.Expressao;
import lf3.plp.expressions2.expression.Valor;
import lf3.plp.expressions2.expression.ValorInteiro;
import lf3.plp.expressions2.memory.AmbienteCompilacao;
import lf3.plp.expressions2.memory.AmbienteExecucao;
import lf3.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf3.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf3.plp.functional3.exception.TipoParametrosException;

public class ExpWait extends ExpUnaria {

	//	public class ExpWait extends ExpExcutor {
	//  }
	//	public ExpWait(Expressao... exp) {
	//		super("wait", exp);
	//	}
	public ExpWait(Expressao exp) {
		super(exp, "wait");
	}

	//	@Override
	//	void executa(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
	//
	//		Expressao[] expressions = getExp();
	//
	//		try {
	//			Expressao expressao = expressions[0];
	//
	//			Integer valor = ((ValorInteiro) expressao.avaliar(amb)).valor();
	//
	//			Thread.sleep(valor * 1000);
	//
	//		} catch (ArrayIndexOutOfBoundsException e) {
	//			throw new TipoParametrosException(TipoPrimitivo.INTEIRO);
	//		} catch (InterruptedException e) {
	//			e.printStackTrace();
	//		}
	//	}

	//	@Override
	//	boolean checaTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
	//
	//		Expressao[] expressions = getExp();
	//
	//		try {
	//
	//			Expressao expressao = expressions[0];
	//
	//			return expressao.getTipo(amb).eInteiro();
	//
	//		} catch (ArrayIndexOutOfBoundsException e) {
	//			throw new TipoParametrosException(TipoPrimitivo.INTEIRO);
	//		}
	//	}

	@Override
	public Valor avaliar(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		try {
			Expressao expressao = exp;

			Integer valor = ((ValorInteiro) expressao.avaliar(amb)).valor();

			Thread.sleep(valor * 1000);

			return new ValorInteiro(valor);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new TipoParametrosException(TipoPrimitivo.INTEIRO);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Tipo getTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		return TipoPrimitivo.INTEIRO;
	}

	@Override
	protected boolean checaTipoElementoTerminal(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		return exp.getTipo(amb).eInteiro();
	}

	@Override
	public ExpUnaria clone() {

		return new ExpWait(exp.clone());
	}
}
