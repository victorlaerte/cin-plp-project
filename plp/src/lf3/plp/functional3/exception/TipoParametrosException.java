package lf3.plp.functional3.exception;

import lf3.plp.expressions1.util.TipoPrimitivo;
import lf3.plp.expressions2.expression.Id;

public class TipoParametrosException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private static String formatStr = "Tipo de parâmetros incorretos para a função %s";
	private static String formatStr2 = "Tipo de parâmetro incorreto para a função. Tipo esperado %s";

	public TipoParametrosException(Id id) {
		super(String.format(formatStr, id.getIdName()));
	}

	public TipoParametrosException(TipoPrimitivo tipo) {
		super(String.format(formatStr2, tipo.getNome()));
	}

}
