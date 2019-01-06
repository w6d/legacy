package swingmenu;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class MenuEditarTest extends TestCase {
	public MenuEditarTest(String testName) {
		super(testName);
	}
	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(MenuEditarTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		try {
			JFrame jFrame = new JFrame();
			JMenuBar menuBar = new JMenuBar();
			MenuEditar menuEditar = new MenuEditar();
			menuBar.add(menuEditar);
			jFrame.setJMenuBar(menuBar);
			jFrame.setSize(400,300);
			jFrame.setVisible(true);
			assertNotNull(menuEditar.getRecortar());
			assertNotNull(menuEditar.getCopiar());
			assertNotNull(menuEditar.getColar());
			assertNotNull(menuEditar.getDesfazer());
			assertNotNull(menuEditar.getRefazer());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
