package it.grational.specification

class TempFileFactory {
	String prefix = 'temp'
	String suffix = '.tmp'

	File create(String content) {
		File.createTempFile(this.prefix,this.suffix).tap {
			deleteOnExit()
			write content
		}
	}
}
