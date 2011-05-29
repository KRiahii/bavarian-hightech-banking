package Proxy;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import bavaria.hightech.banking.BankImpl;

public class DebugHandler implements InvocationHandler {

	private BankImpl bank;
	private Logger loggi = Logger.getLogger(DebugHandler.class.getName());

	public DebugHandler(BankImpl b) throws SecurityException, IOException {
		this.bank = b;
		FileHandler handler = new FileHandler("Bank.log");
		loggi.addHandler(handler);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {

		System.out.print("* calling method " + method + " with params ");
		loggi.log(Level.FINE, "* calling method " + method + " with params ");

		if (args != null) {
			for (int i = 0; i < args.length; i++)
				System.out.print(" " + args[i]);
		}

		System.out.println();

		Object result = null;
		try {
			result = method.invoke(bank, args);
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InvocationTargetException ex) {
			System.out.println("* exception:" + ex.getTargetException());
			loggi.log(Level.SEVERE, "* exception:" + ex.getTargetException());
			throw ex.getTargetException();
		}

		System.out.println("* result:" + result);
		return result;
	}

}