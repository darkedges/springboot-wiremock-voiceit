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
		method 'POST'
		urlPath(value(consumer(regex('/voiceit/authentication/[\\w]{5,32}')), producer('/voiceit/authentication/nirving'))) 
		headers {
			header("voiceit-password", "Abcd1234")
			contentType('multipart/form-data;boundary=AaB03x')
		}
		multipart(
				file: named(
						// name of the file
						name: $(c(regex(nonEmpty())), p('filename.csv')),
						// content of the file
						content: $(c(regex(nonEmpty())), p('file content')))
		)
	}
	response {
		status 200
		body("""
			{
				"Result": "Authentication successful. 89.0%",
				"Confidence": "89.0",
				"DetectedVoiceprintText": "Never forget tomorrow is a new day",
				"DetectedTextConfidence": "97.567433",
				"EnrollmentID": "449867",
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