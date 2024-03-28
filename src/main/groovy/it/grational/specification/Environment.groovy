package it.grational.specification

import java.lang.reflect.Field

class Environment {
	private final Map variables

	Environment(Map vars = [:]) {
		this.variables = Objects.requireNonNull(vars)
	}

	void insert() {
		Map mutableEnv = this.mutableEnv()
		this.variables.each { String k, String v ->
			mutableEnv.put(k, v)
		}
	}

	void insert(String k, String v) {
		this.variables.put(k,v)
		Map mutableEnv = this.mutableEnv()
		mutableEnv.put(k, v)
	}

	void remove(String k) {
		Map mutableEnv = this.mutableEnv()
		mutableEnv.remove(k)
	}

	void clean() {
		this.variables.each { String k, String v ->
			this.remove(k)
		}
	}

	private Map mutableEnv() {
		Map immutableEnv = System.getenv()
		Class<?> cl      = immutableEnv.getClass()
		Field field      = cl.getDeclaredField("m")
		field.accessible = true
		Map mutableEnv   = field.get(immutableEnv)
		return mutableEnv
	}
}
