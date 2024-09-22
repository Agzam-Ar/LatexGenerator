package latex;

public class LMath {

	public static StringBuilder mod(Object a, Object b) {
		return Latex.format("@\\;mod\\;@", a, b);
	}

	public static String sign(int i) {
		return i < 0 ? "-" : "+";
	}
}
