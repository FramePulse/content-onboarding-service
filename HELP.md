# Read Me First
The following was discovered as part of building this project:

* The original package name 'com.framepulse.content-onboarding-service' is invalid and this project uses 'com.framepulse.content_onboarding_service' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.0/gradle-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.4.0/gradle-plugin/packaging-oci-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.4.0/reference/web/servlet.html)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/3.4.0/reference/using/devtools.html)
* [Eureka Discovery Client](https://docs.spring.io/spring-cloud-netflix/reference/spring-cloud-netflix.html#_service_discovery_eureka_clients)
* [Config Client](https://docs.spring.io/spring-cloud-config/reference/client.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Service Registration and Discovery with Eureka and Spring Cloud](https://spring.io/guides/gs/service-registration-and-discovery/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)


---- 
Minio setup
----
> docker pull minio/minio
> docker run -p 9000:9000 -p 9001:9001 --name minio -e "MINIO_ROOT_USER=admin"   -e "MINIO_ROOT_PASSWORD=password123" -v C:\work\framepulse\storage\minio:/data -v C:\work\framepulse\storage\minio:/root/.minio minio/minio server /data --console-address ":9001"

create new bucket videos 
apply below custome policy
`{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Principal": "*",
            "Action": "s3:GetObject",
            "Resource": "arn:aws:s3:::videos/*"
        },
        {
            "Effect": "Deny",
            "Principal": "*",
            "Action": ["s3:PutObject", "s3:DeleteObject"],
            "Resource": "arn:aws:s3:::videos/*"
        }
    ]
}`

create access key same as given in properties file


----
cassandra setup
----
> docker pull cassandra:latest
> docker run --name cass_cluster cassandra:latest

>docker run --name cass_cluster -p 9042:9042 cassandra:latest

-- to persist data
docker run --name cass_cluster -p 9042:9042 -v ~/cassandra_data:/var/lib/cassandra cassandra:latest

Then edit /etc/cassandra/cassandra.yaml

authenticator: PasswordAuthenticator
authorizer: CassandraAuthorizer

CREATE KEYSPACE IF NOT EXISTS framepulse
WITH REPLICATION = {
'class':'SimpleStrategy',
'replication_factor':1
};

select data_center from system.local; // datacenter1 is default

jdbc:cassandra://127.0.0.1:9042/framepulse?localdatacenter=datacenter1

DESCRIBE keyspaces;

DROP TABLE IF EXISTS framepulse.content_onboarding;

CREATE TABLE IF NOT EXISTS
framepulse.content_onboarding
(
    id TEXT PRIMARY KEY,
    contentid TEXT,
    status TEXT,
    title TEXT,
    description TEXT,
    tags TEXT,
    storageurl TEXT,
    createdby TEXT,
    createdon TEXT
);

---
kafka
---
> confluent local kafka start

