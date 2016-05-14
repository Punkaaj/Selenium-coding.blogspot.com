package blogsScripts;

import java.io.IOException;

public class RunningPythonScript {
	public static void main(String[] args){

		  try {
			Process p = Runtime.getRuntime().exec("ver");
			int exitValue = p.exitValue();
			if(exitValue == 0){
				System.out.println("Python program run successfully");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}



