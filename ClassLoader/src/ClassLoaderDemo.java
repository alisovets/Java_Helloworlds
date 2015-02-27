import java.io.File;
import java.io.IOException;

import module.TestModule;

public class ClassLoaderDemo {
	private final static String SRC_FILE_PATH = "src/module/TestModule.java";
	private final static String BIN_FILE_PATH = "bin/module/TestModule.class";

	public static void main(String[] args) {

		Thread demoThread = new Thread() {

			public void run() {
				TestModule module = new TestModule();
				System.out.println(module);
				while (true) {
					File srcFile = new File(SRC_FILE_PATH);
					File binFile = new File(BIN_FILE_PATH);

					// every half-second check to change the source file
					if (srcFile.lastModified() <= binFile.lastModified()) {
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						continue;
					}
					
					System.out.println("The src file is changed");

					// after the change to load the new version with the
					// MyClassLoader
					MyClassLoader classLoader = new MyClassLoader();

					Class<?> classObject = null;
					try {
						classObject = classLoader.loadClass("module.TestModule");
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}

					Object testModul = null;
					try {
						testModul = classObject.newInstance();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}

					// print new version
					System.out.println(testModul);

				}
			}
		};
		demoThread.setDaemon(true);
		demoThread.start();

		System.out.println("Press Entrer for finish.");
		// wait for enter
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
