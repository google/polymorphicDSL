# Polymorphic Domain Specific Language Framework

An incredibly powerful framework for scaling testing across multiple
applications and platforms.

## Getting Started

Start with [`the manual`](documentation/polymorphic_dsl_manual.adoc).

The documentation directory of this project has a "Getting Started" guide as well as other information we expect you'll find helpful!


## Contributing

### Legal 

See [`CONTRIBUTING.md`](CONTRIBUTING.md).

### License

Apache 2.0; see [`LICENSE`](LICENSE) for details.

### Running

Many ANTLR4 .g4 files are used to  generate code used at runtime. There is separate code for both the Gherkin Parser used in the library and the grammars used for the tests. To make this all work an additional maven *test* profile is used in the project.

`mvn clean install` will probably fail during the test phase because it is expecting this code to be generated. This can be solved by first running
`mvn clean antlr4:antlr4 -P test`
to generate the test classes.


Running `mvn clean` at this point will destroy all of the generated code, so to *install* do the following operations:

```
mvn clean antlr4:antlr4 -P test
mvn antlr4:antlr4 install
```

At this point you can use `mvn anltr4:antlr4 <some lifecycle>` up until you run `mvn clean` again. If you do that you will need to regenerate the source code using the `-P test` profile as shown above.

### Deployment

#### Deploying to Remote Repository

Google has a nexus repository. Only Google employees are able to access it.

A release can be staged at the repository using the following command:

`mvn clean antlr4:antlr4 && mvn antlr4:antlr4 -Ptest && mvn antlr4:antlr4 deploy -Prelease`

This command generates the Gherkin parser, then the parsers needed for testing and finally runs the deploy (which is also when the tests will execute).

Deploying to staging will require removing the `-SNAPSHOT` postfix from the POM as well as having a cryptographic signature available to sign the application.

#### Making a local JAR

A fat JAR with all the dependencies is made with the shade plugin, but currently it won't work unless the `shade:shade` lifecycle is explicitly called:

`mvn clean antlr4:antlr4 -P test && mvn antlr4:antlr4 package shade:shade`

#### Deploy to Google's Nexus Repository


## Disclaimer

This project is not an official Google project. It is not supported by
Google and Google specifically disclaims all warranties as to its quality,
merchantability, or fitness for a particular purpose.
