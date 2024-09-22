package latex;

import java.util.ArrayList;

public class LArray {

	CharSequence init;
	ArrayList<ArrayList<CharSequence>> rows = new ArrayList<>();
	private String emptyString = "";
	
	
	public LArray(CharSequence init) {
		this.init = init;
	}
	public void line(Object obj) {
		ArrayList<CharSequence> cs = new ArrayList<CharSequence>();
		cs.add(obj.toString());
		rows.add(cs);
	}

	public void line(String string, Object... args) {
		ArrayList<CharSequence> cs = new ArrayList<CharSequence>();
		cs.add(Latex.format(string, args));
		rows.add(cs);
	}

	public void line() {
		rows.add(new ArrayList<CharSequence>());
	}
	

	public void set(int x, int y, String string, Object... args) {
		for (int i = rows.size(); i <= y; i++) {
			line();
		}
		var row = rows.get(y);
		for (int i = row.size(); i <= x; i++) {
			row.add(emptyString);
		}
		row.set(x, Latex.format(string, args));
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(init == null) {
			int max = 0;
			for (var row : rows) max = Math.max(max, row.size());
			sb.append("\\begin{array}{");
			for (int i = 0; i < max; i++) {
				sb.append('c');
			}
			sb.append("}\n");
		} else {
			sb.append(Latex.format("\\begin{array}{@}\n", init));
		}
		
		for (var row : rows) {
			sb.append('\t');
			boolean first = true;
			boolean needln = true;
			for (var cell : row) {
				if(!first) sb.append('&');
				sb.append(cell);
				if(cell.toString().equals(Symbol.hline)) needln = false;
				first = false;
			}
			if(needln) sb.append("\\\\");
			sb.append('\n');
		}
		sb.append("\\end{array}\n");
		return sb.toString();
	}

	

	public static StringBuilder parray(Object center, int[] array) {
		return array('(', center, ')', array);
	}

	public static StringBuilder array(Object start, Object center, Object end, int[] array) {
		StringBuilder sb = new StringBuilder();
		sb.append(Latex.toString(start));
		for (int i = 0; i < array.length; i++) {
			if(i != 0) sb.append(Latex.toString(center));
			sb.append(Latex.toString(array[i]));
		}
		sb.append(Latex.toString(end));
		return sb;
	}
	
	public void emptyString(String emptyString) {
		this.emptyString = emptyString;
	}
}
