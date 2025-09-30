plugins {
	java
	id("org.springframework.boot") version "3.5.5"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "github.muhametshindenis.bitshop"
version = "0.0.1-SNAPSHOT"
description = "core"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// Spring Boot Starters
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-actuator")

	// Redis
	implementation("org.springframework.session:spring-session-data-redis")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	
	// Graphql and Webflux
	implementation("org.springframework.boot:spring-boot-starter-graphql")
	testImplementation("org.springframework.graphql:spring-graphql-test")
	testImplementation("org.springframework:spring-webflux")

	// Database driver
	runtimeOnly("org.postgresql:postgresql")

	// Database migrations
	implementation("org.flywaydb:flyway-core:11.13.1")
	runtimeOnly("org.flywaydb:flyway-database-postgresql:11.13.1")

	// SpringDoc OpenAPI
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.5")

	// Aws SDK for Java 2.x
	implementation(platform("software.amazon.awssdk:bom:2.20.56"))
	implementation("software.amazon.awssdk:s3")
	implementation("software.amazon.awssdk:sso")
	implementation("software.amazon.awssdk:ssooidc")

	// Lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testCompileOnly("org.projectlombok:lombok:1.18.28")
	testAnnotationProcessor("org.projectlombok:lombok:1.18.28")

	// Stripe
	implementation("com.stripe:stripe-java:29.5.0")

	// Dev tools
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	// Testing
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testImplementation("org.mockito:mockito-inline:5.2.0")
}


tasks.withType<Test> {
	useJUnitPlatform()
	jvmArgs("-XX:+EnableDynamicAgentLoading")
}
