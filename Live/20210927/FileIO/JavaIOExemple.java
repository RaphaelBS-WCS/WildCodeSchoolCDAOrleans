import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class JavaIOExemple {
	public static void main(String[] args) throws IOException {
		// L'objet file représente le fichier, on n'a pas encore
		// demandé de l'ouvrir
		File exemple1 = new File("exemple1");
		System.out.println("exemple1.exists() => " + exemple1.exists());

		writeInBinary(exemple1);
		writeInText(exemple1);
		writeInTextSimplerAndGood(exemple1);
		writeToStringBuffer();
		goodWay(exemple1);
		
		exemple1.delete();
	}

	private static void writeInBinary(File exemple1) throws IOException {
		FileOutputStream fos = new FileOutputStream(exemple1);
		ByteBuffer bb = StandardCharsets.UTF_8.encode("Hello world !");
		fos.write(bb.array());
		// This is bad practices ! Don't do that
		fos.close();

		FileInputStream fis = new FileInputStream(exemple1);
		System.out.println(
				StandardCharsets.UTF_8.decode(
						ByteBuffer.wrap(fis.readAllBytes())
						)
				);
		// This is bad practices ! Don't do that
		fis.close();
	}

	private static void writeInText(File exemple1) throws IOException {
		FileWriter fw = new FileWriter(exemple1);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("Hello world in text !\nLine 2");
		// Not necessary here, but it is important you know about it
		bw.flush();
		// This is bad practices ! Don't do that
		bw.close();

		FileReader fr = new FileReader(exemple1);
		BufferedReader br = new BufferedReader(fr);
		String line;
		while((line = br.readLine()) != null) {
			System.out.println(line);
		}
		// This is bad practices ! Don't do that
		fr.close();
	}

	private static void writeInTextSimplerAndGood(File exemple1) throws IOException {
		Files.writeString(exemple1.toPath(), 
			"Hello world simpler !\nLine 3"
		);

		Files.lines(exemple1.toPath()).forEach(
			// Ceci s'appelle une lambda expression
			l -> System.out.println(l)
		);
	}

	private static void writeToStringBuffer() throws IOException {
		StringWriter sw = new StringWriter();
		BufferedWriter bw = new BufferedWriter(sw);
		bw.write("Hello world in string !\nLine 4");
		// Not necessary here, but it is important you know about it
		bw.flush();
		bw.close();

		StringReader sr = new StringReader(sw.toString());
		BufferedReader br = new BufferedReader(sr);
		String line;
		while((line = br.readLine()) != null) {
			System.out.println(line);
		}
		sr.close();
	}
	
	private static void goodWay(File exemple1) {
		try (BufferedWriter writer = Files.newBufferedWriter(
				exemple1.toPath(), 
				StandardCharsets.UTF_8)
		) {
		    writer.write("Hello 4");
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		
		try (BufferedReader reader = Files.newBufferedReader(
				exemple1.toPath(), 
				StandardCharsets.UTF_8)
		) {
		    System.out.println(reader.readLine());
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}
}
