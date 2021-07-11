Ability: Test Resources

	A test resource is any data that can be represented by a URL that can later be converted into a Test Specification. These are commonly text files, but they can also be arbitrary resources on the internet, Java Archives or anything else suppored by a URI encoding scheme.

	A test resource typically requires its own Test Specification Factory to understand it and ultimately convert it into an appropriate test specification. The "TestSpecificationFactory" interface can be used to design one if the pre-existing factories do not meet your needs.

	Rule: Any valid URL is technically a valid test resource. Some corresponding test specification factory will attempt to open the resource and will raise an error in the event of a problem (file doesn't exist, internet offline, etc).

Scenario: A simple file-based Test Resource
	Given the test resource "file:/home/someUser/somefile.txt"
	Then the test resource is valid

Scenario: A simple HTTPS resource
	Given the test resource "https://en.wikipedia.org/wiki/HTTPS"
	Then the test resource is valid

Scenario: Invalid Input- URL missing scheme
	Given the test resource "/home/someUser/somefile.txt"
	Then the test resource is NOT valid

	Given the test resource "en.wikipedia.org/wiki/HTTPS"
	Then the test resource is NOT valid
