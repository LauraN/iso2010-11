package com.umbrella.worldconq.ui;

import javax.swing.JEditorPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.umbrella.worldconq.domain.PlayerListModel;

public class PlayerView extends JEditorPane implements TableModelListener {

	private static final long serialVersionUID = -3895756012036141349L;

	private final PlayerListModel plm;

	public PlayerView(PlayerListModel plm) {
		super();
		this.setEditable(false);
		this.plm = plm;
		this.plm.addTableModelListener(this);
		//llamamos a tableChanged para que pinte la primera vez
		this.tableChanged(null);
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		String list = "<html>\n<P Align=\"left\">Usted es "
				+ plm.getSelfPlayer().getName() + "<BR>Dispone de "
				+ plm.getSelfPlayer().getMoney() + " gallifantes.</P>";
		list += "<P ALIGN=\"center\">----------";
		list += "<BR><BIG>" + "Jugadores"
				+ "</BIG><BR></P>\n<HR>" + "<TABLE BORDER=0>";
		for (int i = 0; i < plm.getRowCount(); i++) {
			list += "<TR><TD Align=\"left\">" + "<IMG SRC=\"";
			if ((Boolean) plm.getValueAt(i, 2)) {
				if ((Boolean) plm.getValueAt(i, 1)) {
					list += ClassLoader.getSystemResource("image/turn.png");
				} else {
					list += ClassLoader.getSystemResource("image/online.png");
				}
			} else {
				list += ClassLoader.getSystemResource("image/offline.png");
			}
			list += "\"><TD Align=\"left\">" + plm.getValueAt(i, 0);
		}
		list += "</TABLE>\n</P>";
		this.setContentType("text/html");
		this.setText(list);
	}
}
