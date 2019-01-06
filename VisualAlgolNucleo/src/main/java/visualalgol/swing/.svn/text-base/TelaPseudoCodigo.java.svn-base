package visualalgol.swing;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.Gutter;
import org.fife.ui.rtextarea.RTextScrollPane;

public class TelaPseudoCodigo extends RSyntaxTextArea implements HyperlinkListener {
	private static final long serialVersionUID = 1L;
	private RTextScrollPane scrollPane;
	public TelaPseudoCodigo(){
		//this.setFont(new Font(Font.MONOSPACED,Font.PLAIN, 12));
		//this.setTabSize(4);
		//super(25, 70);
		setCaretPosition(0);
		this.addHyperlinkListener(this);
		requestFocusInWindow();
		this.setMarkOccurrences(true);
		this.setTextAntiAliasHint("VALUE_TEXT_ANTIALIAS_ON");
		this.setSyntaxEditingStyle(SYNTAX_STYLE_JAVA);
		this.setTabSize(4);
		scrollPane = new RTextScrollPane(this, true);
		Gutter gutter = scrollPane.getGutter();
		gutter.setBookmarkingEnabled(true);
		URL url = getClass().getClassLoader().getResource("bookmark.png");
		gutter.setBookmarkIcon(new ImageIcon(url));
	}
	
	public void setText(String t) {
		super.setText(t);
	}
	
	public void setTabSize(int size) {
		super.setTabSize(size);
	}
	
	public void setCaretPosition(int position) {
		super.setCaretPosition(position);
	}
	
	public boolean requestFocusInWindow() {
		return super.requestFocusInWindow();
	}
	
	public void append(String str) {
		super.append(str);
	}
	
	/**
	 * Called when a hyperlink is clicked in the text area.
	 * 
	 * @param e
	 *            The event.
	 */
	public void hyperlinkUpdate(HyperlinkEvent e) {
		if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			URL url = e.getURL();
			if (url == null) {
				UIManager.getLookAndFeel().provideErrorFeedback(null);
			} else {
				JOptionPane.showMessageDialog(this, "URL clicked:\n" + url.toString());
			}
		}
	}
	
	public RTextScrollPane getScrollPane() {
		return scrollPane;
	}
	
	public void setLinguagemStyle(String linguagemStyle) {
		setSyntaxEditingStyle(linguagemStyle);
	}
}
