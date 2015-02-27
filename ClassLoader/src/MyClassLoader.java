import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {
	private final static String SRC_PATH = "src/";
	private final static String BIN_PATH = "bin/";

	private byte[] getBytes(File file) throws IOException {

		long len = file.length();
		byte raw[] = new byte[(int) len];

		FileInputStream inputStream = new FileInputStream(file);
		try {
			int r = inputStream.read(raw);
			if (r != len) {
				throw new IOException("Can't read all, " + r + " != " + len);
			}
		} finally {
			inputStream.close();
		}
		return raw;
	}

	// compile the java source code file
	// specified in the 'javaFile' parameter. Return a true if
	// the compilation worked, false otherwise
	private boolean compile(String javaFileName) throws IOException {

		Process p = Runtime.getRuntime().exec("javac -d " + BIN_PATH + " " + javaFileName);
		try {
			p.waitFor();
		} catch (InterruptedException ie) {
			throw new RuntimeException(ie);
		}

		int ret = p.exitValue();
		return ret == 0;
	}

	@Override
	public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {

		if (!name.equals("module.TestModule")) {
			return super.loadClass(name, resolve);
		}

		Class<?> aClass = findLoadedClass(name);

		// form the source and binary files objects
		String fileStub = name.replace('.', '/');
		String srcFileName = SRC_PATH + fileStub + ".java";
		File srcFile = new File(srcFileName);
		File binFile = new File(BIN_PATH + fileStub + ".class");

		if (!srcFile.exists()) {
			throw new ClassNotFoundException(name);
		}

		// compile java-file if it is newer than the class-file
		if (!binFile.exists() || (binFile.lastModified() < srcFile.lastModified())) {
			try {
				if (!compile(srcFileName) || !binFile.exists()) {
					throw new ClassNotFoundException("Compile failed: " + binFile);
				}
			} catch (IOException ie) {
				throw new ClassNotFoundException(ie.toString());
			}

		}

		// load class from the class file
		byte[] raw = null;
		try {
			raw = getBytes(binFile);
			aClass = defineClass(null, raw, 0, raw.length);
		} catch (IOException e) {
			throw new ClassNotFoundException();
		}

		if (resolve && aClass != null) {
			resolveClass(aClass);
		}

		if (aClass == null) {
			throw new ClassNotFoundException(name);
		}

		return aClass;
	}

}
