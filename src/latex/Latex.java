package latex;

import java.util.Arrays;

public class Latex  {

	private StringBuilder latex = new StringBuilder();
	
	public Latex() {
		
	}

	public void text(String string, Object... args) {
		latex.append(format(string, args));
	}
	
	public void textln(String string, Object... args) {
		text(string, args);
		latex.append('\n');
	}

	public void append(Object obj) {
		latex.append(obj);
	}
	

	public static StringBuilder format(String str, Object... args) {
		StringBuilder formated = new StringBuilder();
		int ai = 0;
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '@') {
				formated.append(ai >= args.length ? "@" : (args[ai] == null) ? "null" : args[ai].toString());
				ai++;
				continue;
			}
			formated.append(str.charAt(i));
		}
		return formated;
	}
	
	public static String toString(Object object) {
		return object == null ? "null" : object.toString();
	}
	
	@Override
	public String toString() {
		return latex.toString();
	}

	public Latex table(Cons<LArray> cons) {
		var arr = new LArray(null);
		cons.get(arr);
		append(arr);
		return this;
	}
	
	public Latex table(int cols, Cons<LArray> cons) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cols; i++) {
			sb.append('c');
		}
		var arr = new LArray(sb);
		cons.get(arr);
		append(arr);
		return this;
	}
	

	public Latex table(String init, Cons<LArray> cons) {
		var arr = new LArray(init);
		cons.get(arr);
		append(arr);
		return this;
	}

	public static StringBuilder reverse(Object obj) {
		String str = obj.toString();
		StringBuilder reversed = new StringBuilder(str.length());
		for (int i = str.length()-1; i >= 0; i--) {
			reversed.append(str.charAt(i));
		}
		return reversed;
	}

	 
}
