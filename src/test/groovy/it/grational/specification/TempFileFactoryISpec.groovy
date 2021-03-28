package it.grational.specification

import spock.lang.*

class TempFileFactoryISpec extends Specification {

	@Unroll
	def "Should be capable of creating a temporary file with a given content"() {
		given:
			TempFileFactory tff = new TempFileFactory()
		when:
			def tempFile = tff.create(content)
		then:
			tempFile.text == content
		where:
			content << [
				'first content',
				'second content'
			]
	}

	@Unroll
	def "Should create temp file with specific prefix and suffix"() {
		given:
			TempFileFactory tff = new TempFileFactory (
				prefix: prefix,
				suffix: suffix
			)
		when:
			def tempFile = tff.create('content')
		then:
			tempFile.name ==~ /${prefix}.*${suffix}/
		where:
			prefix | suffix
			'pre'  | '.ext'
			'temp' | '.tmp'

	}


}
