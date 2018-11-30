package com.dayon.common.log4j;

import java.io.PrintStream;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public final class Log4jSystemLogDriver {
	private Log4jSystemLogDriver() {
	}

	static {
		final Logger logger = (Logger) LogManager.getLogger();
		System.setOut(new PrintStream(System.out) {
			private final Level level = Level.DEBUG;
			private final String FQCN = this.getClass().getName();

			private void log(Object message) {
				logger.logIfEnabled(FQCN, level, null, message, null);
			}

			@Override
			public void println(boolean x) {
				log(x);
			}

			@Override
			public void println(char x) {
				log(x);
			}

			@Override
			public void println(char[] x) {
				log(x);
			}

			@Override
			public void println(double x) {
				log(x);
			}

			@Override
			public void println(float x) {
				log(x);
			}

			@Override
			public void println(int x) {
				log(x);
			}

			@Override
			public void println(long x) {
				log(x);
			}

			@Override
			public void println(Object x) {
				log(x);
			}

			@Override
			public void println(String x) {
				log(x);
			}

			@Override
			public void println() {
				log("");
			}

			@Override
			public void print(boolean x) {
				log(x);
			}

			@Override
			public void print(char x) {
				log(x);
			}

			@Override
			public void print(char[] x) {
				log(new String(x));
			}

			@Override
			public void print(double x) {
				log(x);
			}

			@Override
			public void print(float x) {
				log(x);
			}

			@Override
			public void print(int x) {
				log(x);
			}

			@Override
			public void print(long x) {
				log(x);
			}

			@Override
			public void print(Object x) {
				log(x);
			}

			@Override
			public void print(String x) {
				log(x);
			}
		});
		System.setErr(new PrintStream(System.err) {
			private final Level level = Level.ERROR;
			private final String FQCN = this.getClass().getName();

			private void log(Object message) {
				logger.logIfEnabled(FQCN, level, null, message, null);
			}

			@Override
			public void println(boolean x) {
				log(x);
			}

			@Override
			public void println(char x) {
				log(x);
			}

			@Override
			public void println(char[] x) {
				log(x);
			}

			@Override
			public void println(double x) {
				log(x);
			}

			@Override
			public void println(float x) {
				log(x);
			}

			@Override
			public void println(int x) {
				log(x);
			}

			@Override
			public void println(long x) {
				log(x);
			}

			@Override
			public void println(Object x) {
				log(x);
			}

			@Override
			public void println(String x) {
				log(x);
			}

			@Override
			public void println() {
				log("");
			}

			@Override
			public void print(boolean x) {
				log(x);
			}

			@Override
			public void print(char x) {
				log(x);
			}

			@Override
			public void print(char[] x) {
				log(new String(x));
			}

			@Override
			public void print(double x) {
				log(x);
			}

			@Override
			public void print(float x) {
				log(x);
			}

			@Override
			public void print(int x) {
				log(x);
			}

			@Override
			public void print(long x) {
				log(x);
			}

			@Override
			public void print(Object x) {
				log(x);
			}

			@Override
			public void print(String x) {
				log(x);
			}
		});

	}
}
