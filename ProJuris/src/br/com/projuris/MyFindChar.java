package br.com.projuris;

public class MyFindChar implements FindCharacter {

	public static void main(String[] args) {
		MyFindChar mfc = new MyFindChar();
		System.out.println(mfc.findChar("stress"));
		System.out.println(mfc.findChar("reembolsar"));
		System.out.println(mfc.findChar("osso"));
	}
	
	@Override
	public char findChar(String word) {
		
		int[] flags = new int[256]; 

	    for (int count = 0; count < word.length(); count++) {
	        flags[(int)word.charAt(count)]++ ;
	    }

	    for (int count = 0; count < word.length(); count++) {
	        if(flags[(int)word.charAt(count)] == 1)
	            return word.charAt(count);
	    }

	    return " ".charAt(0);
		
	}

}
