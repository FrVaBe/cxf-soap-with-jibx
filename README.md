cxf-soap-with-jibx
==================

This is an [Apache CXF](http://cxf.apache.org/) SOAP service example with [JiBX](http://jibx.sourceforge.net/) databinding. The approach is quite similar to the [maven-jibx-plugin Webservice example](http://jibx.sourceforge.net/maven-jibx-plugin/webservice.html) (except from the OSGi usage) but it also uses a class-decorator (`SerializableDecorator`) to [extend](http://jibx.sourceforge.net/fromschema/codegen-extends.html#extend) the generated classes with a `java.io.Serializable` interface.

The class-decorator usage is defined in the `/src/main/config/custom-jibx-codegen.xml` file.

The project is build using maven and the following plugins:
* [cxf-codegen-plugin](http://cxf.apache.org/docs/maven-cxf-codegen-plugin-wsdl-to-java.html)
* [jibx-maven-plugin](http://jibx.sourceforge.net/maven-jibx-plugin/index.html)

The cxf-codegen-plugin provides the possibility to choose JiBX as databinding but it does not provide the possibility to define the code generation customization (class-decorator). So the code generation of the xsd types is excluded from the cxf-codegen-plugin section and a `schema-codegen` goal is added in the jibx-maven-plugin section (what would normally not be necessary).

If you run the project (`de.frvabe.sample.calculator.Main.class`) the wsdl of the service will be accessible at

    http://localhost:8080/calculator?wsdl

The service provides a simple calculator operation ignoring most mathematical rules ;-)

So if you send the following request:

    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:typ="http://calculator.sample.frvabe.de/types">
        <soapenv:Header/>
        <soapenv:Body>
            <typ:term>
                <part>
                    <value>1</value>
                </part>
                <part>
                    <operator>ADD</operator>
                    <value>2</value>
                </part>
                <part>
                    <operator>MULTIPLY</operator>
                    <value>3</value>
                </part>
                <part>
                    <operator>DIVIDE</operator>
                    <value>4</value>
                </part>
                <part>
                    <operator>SUBTRACT</operator>
                    <value>5</value>
                </part>
            </typ:term>
        </soapenv:Body>
    </soapenv:Envelope>

You will get the result

    <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
        <soap:Body>
            <types:result xmlns:types="http://calculator.sample.frvabe.de/types">
                <value>-2.75</value>
            </types:result>
        </soap:Body>
    </soap:Envelope>

___

By the way - the project was compiled and run successful using the lates [OpenJDK 8 build b84 SNAPSHOT](http://jdk8.java.net/download.html).
