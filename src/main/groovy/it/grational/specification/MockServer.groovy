package it.grational.specification

import groovy.lang.Delegate
import groovy.transform.ToString
import com.github.tomakehurst.wiremock.WireMockServer
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options

class MockServer {
	String  protocol
	String  host
	Integer port
	String  authority
	String  origin
	String  path
	String  body
	URL     url

	private final Map ok = [
		code: 200,
		body: '{"status":"OK"}'
	]

	@Delegate WireMockServer wms

	MockServer(Map params) {
		this.protocol = (params?.protocol ==~ 'https?') ? params.protocol : 'http'
		this.host     = params?.host ?: 'localhost'
		this.port     = params?.port ?: 1234
		this.path     = params?.path ?: '/appropriate/path'
		this.body     = params?.body ?: this.ok.body

		this.authority = "${host}:${port}"
		this.origin    = "${protocol}://${authority}"
		this.url       = "${origin}${path}".toURL()
		this.wms = (this.protocol == 'http')
		         ? new WireMockServer(options().port(this.port))
		         : new WireMockServer(options().httpDisabled(true).httpsPort(this.port))
	}

}
