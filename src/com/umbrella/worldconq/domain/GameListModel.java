package com.umbrella.worldconq.domain;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import domain.GameInfo;

public class GameListModel extends AbstractTableModel {

	private static final long serialVersionUID = 7356294494185290462L;

	private static final String[] colTitles = {
			"Nombre",
			"Descripción",
			"N° Jugadores",
			"N° Territorios Libres"
	};

	private final ArrayList<GameInfo> mGameList;

	public GameListModel() {
		super();
		mGameList = new ArrayList<GameInfo>();
	}

	public void setData(ArrayList<GameInfo> data) {
		mGameList.clear();
		if (data != null) {
			mGameList.addAll(data);
			this.fireTableDataChanged();
		}
	}

	@Override
	public String getColumnName(int col) {
		return colTitles[col];
	}

	@Override
	public int getColumnCount() {
		return colTitles.length;
	}

	@Override
	public int getRowCount() {
		return mGameList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		final GameInfo game = mGameList.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return game.getName();
		case 1:
			return game.getDescription();
		case 2:
			return new Integer(game.getPlayers().size());
		case 3:
			return new Integer(game.getnFreeTerritories());
		default:
			throw new IndexOutOfBoundsException("Index: " + columnIndex
					+ ", Size: " + this.getColumnCount());
		}
	}

	public GameInfo getGameAt(int gameSelected) {
		return mGameList.get(gameSelected);
	}

}
