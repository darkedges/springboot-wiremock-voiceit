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
		urlPath(value(consumer(regex('/voiceit/getUser/[\\w]{5,32}')), producer('/voiceit/getUser/nirving'))) 
		headers {
			header("voiceit-password", "Abcd1234")
		}
	}
	response {
		status 200
		body("""
			{
				"Result": "Success",
				"Exists": true,
				"ResponseCode": "SUC",
				"TimeTaken": "1.02s"
			}
			""")
		headers {
			header('Content-Type': value(
                   producer(regex('application/json.*')),
                   consumer('application/json')
           ))
		}
	}
}