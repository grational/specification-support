package it.italiaonline.rnd.specification

import spock.lang.Specification

/**
 * This Spock specification was auto generated by 'gigawatt'
 * @author d7392
 * @date 02-08-2018 07.39
 */
class EnvironmentSpec extends Specification {
	def "someEnvironmentMethod returns true"() {
		setup:
			Environment lib = new Environment()
		when:
			Boolean result = lib.someEnvironmentMethod()
		then:
			result == true
	}
}
