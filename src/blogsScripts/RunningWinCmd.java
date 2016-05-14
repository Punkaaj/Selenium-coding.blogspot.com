package blogsScripts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Date;

public class RunningWinCmd {

	public static void main(String[] args) {

		String[] cmd = { "cmd.exe", "/c", "dir" }, cmd1 = { "hostname" };
		Process p, p1;
		try {
			// output file to write output of commands
			File outFile = new File("CmdOutput.txt");
			FileWriter fw = new FileWriter(outFile, true); // "true" makes
															// contents to get
															// appended to
															// output file
			BufferedWriter bw = new BufferedWriter(fw);

			// run cmd
			p = Runtime.getRuntime().exec(cmd);
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));

			// calling class's static methods to write to output file
			writeDateToOutFile(bw);
			writeCmdToFile(cmd, bw);
			formatOutFile(bw);
			writeToOutFile(input, bw);
			formatOutFile(bw);

			input.close();

			p1 = Runtime.getRuntime().exec(cmd1);
			BufferedReader in = new BufferedReader(new InputStreamReader(p1.getInputStream()));

			writeCmdToFile(cmd1, bw);
			formatOutFile(bw);
			writeToOutFile(in, bw);
			formatOutFile(bw);

			bw.flush();
			bw.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void writeToOutFile(BufferedReader inObj, BufferedWriter outObj) {
		String temp = "";
		try {
			while ((temp = inObj.readLine()) != null) {
				outObj.write(temp);
				outObj.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void formatOutFile(BufferedWriter outObj) {
		try {
			outObj.newLine();
			outObj.write("===========<cmd output>==============");
			outObj.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeDateToOutFile(BufferedWriter outObj) {
		Date date = new Date();
		try {
			outObj.newLine();
			outObj.write(date.toString());
			outObj.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeCmdToFile(String[] cmd, BufferedWriter outObj) {
		try {
			outObj.newLine();
			outObj.write(Arrays.toString(cmd)); // writes name of command to
												// outFile
			outObj.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
