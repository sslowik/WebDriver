
public class Methods {
	static Boolean ValidatePageSource(String source, String url) {
		if (source.toLowerCase().contains(url.toLowerCase()))
			return true;
		else 
			return false; 
	}
}
