package visualalgol.ferramenta;

import java.awt.Color;
import java.awt.event.MouseEvent;

import visualalgol.entidades.CondicaoFim;

public class CondicaoFimFerramenta extends Ferramenta{
	@Override
	public void mouseClicked(MouseEvent e) {
		if (getInstrucaoEm(e.getX(), e.getY()) == null) {
			removerTodosOsFocos();
			CondicaoFim condicaoFim = new CondicaoFim();
			condicaoFim.setX(e.getX());
			condicaoFim.setY(e.getY());
			condicaoFim.setW(10);
			condicaoFim.setH(10);
			condicaoFim.setCor(new Color(0xff, 0xf0, 0xf0).getRGB());
			getAlgoritmo().getListComando().add(condicaoFim);
			condicaoFim.setAlgoritmo(getAlgoritmo());
			setArrastando(condicaoFim);
		}
	}
}
