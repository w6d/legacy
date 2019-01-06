package visualalgo.casosdeuso;

import junit.framework.TestCase;
import visualalgol.casosdeuso.InterpretarFluxograma;

public class InterpretarFluxogramaTest extends TestCase{
	public void testTratarStringDeCondicao(){
		InterpretarFluxograma.tratarStringDeCondicao(null);
		String res = InterpretarFluxograma.tratarStringDeCondicao("a = \"teste = teste \"");
		assertEquals("a == \"teste = teste \"", res);
		res = InterpretarFluxograma.tratarStringDeCondicao("a = \"teste \\\" = teste \"");
		assertEquals("a == \"teste \\\" = teste \"", res);
		res = InterpretarFluxograma.tratarStringDeCondicao("a == \"teste \\\" = teste \"");
		assertEquals("a == \"teste \\\" = teste \"", res);
		res = InterpretarFluxograma.tratarStringDeCondicao("\"teste \\\" = teste \" = a");
		assertEquals("\"teste \\\" = teste \" == a", res);
		res = InterpretarFluxograma.tratarStringDeCondicao("\"teste \\\" = teste \" == a");
		assertEquals("\"teste \\\" = teste \" == a", res);
		res = InterpretarFluxograma.tratarStringDeCondicao("\"teste \\\" = teste \" == a && a=\"=\"");
		assertEquals("\"teste \\\" = teste \" == a && a==\"=\"", res);
		res = InterpretarFluxograma.tratarStringDeCondicao("1<=2");
		assertEquals("1<=2", res);
		res = InterpretarFluxograma.tratarStringDeCondicao("1>=2");
		assertEquals("1>=2", res);
		res = InterpretarFluxograma.tratarStringDeCondicao("1 <= 2");
		assertEquals("1 <= 2", res);
		res = InterpretarFluxograma.tratarStringDeCondicao("1 >= 2");
		assertEquals("1 >= 2", res);
		res = InterpretarFluxograma.tratarStringDeCondicao("1 >= 2 e 1<3");
		assertEquals("1 >= 2 && 1<3", res);
		res = InterpretarFluxograma.tratarStringDeCondicao("1 >= 2 ou 1<3");
		assertEquals("1 >= 2 || 1<3", res);
	}
}
