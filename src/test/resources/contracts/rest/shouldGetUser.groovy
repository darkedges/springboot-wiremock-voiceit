package contracts.voiceIt.rest

import org.springframework.cloud.contract.spec.Contract

Contract.make {
	request {
		description("""
Represents a successful scenario of getting a beer
```
given:
	client is old enough
when:
	he applies for a beer
then:
	we'll grant him the beer
```
""")
		method 'GET'
		url '/voiceit/getUser/nirving'
		headers {
			contentType(applicationJson())
			header("voiceit-password", "Abcd1234")
		}
	}
	response {
		status 200
		body("""
			{
				"status": "ok",
				"name": "nirving"
			}
			""")
		headers {
			contentType(applicationJson())
		}
	}
}