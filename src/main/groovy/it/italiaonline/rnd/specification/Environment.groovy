package it.italiaonline.rnd.specification

import java.lang.reflect.Field

class Environment {
	private final Map variables

	Environment(Map vars) {
		this.variables = Objects.requireNonNull(vars)
	}

	void insert() {
		Map mutableEnv = this.mutableEnv()
		mutableEnv << this.variables
	}

	void clean() {
		Map mutableEnv = this.mutableEnv()
		this.variables.each { k, v ->
			mutableEnv.remove(k)
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
