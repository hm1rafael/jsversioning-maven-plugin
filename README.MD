JAVASCRIPT VERSIONING
=====================

You have something like this defined in your webapps files:

		<script language="javascript" type="text/JavaScript" src="script.js"></script>

This plugin will change to:

		<script language="javascript" type="text/JavaScript" src="script.js?n="version""></script>

The version will be user defined, and if its not defined the plugin will generate a random UUID.

USAGE
=====

	<build>
		<plugins>
			...
			<plugin>
				<groupId>com.github.hm1rafael</groupId>
				<artifactId>jsversioning</artifactId>
				<version>0.0.1</version>
				<executions>
					<execution>
						<id>jsVersioning</id>
						<goals>
							<goal>versioning</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
			...
		</plugins>
	</build>
