package common;

public class Util {
	
	public String cutStirng(String inputString, int cut) {
		String outputString ="";
		if(inputString == null || inputString =="" )
		{
			return outputString;
		}
		else if (inputString.length() < cut) {
			
			return inputString;
			
		}else {
			outputString = inputString.substring(0,cut) + "..." ;
		}
		
		return outputString;
	}
}
