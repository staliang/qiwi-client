# qiwi-client

Client's implementation [QIWI API](https://developer.qiwi.com/ru/qiwi-wallet-personal/index.html) for Java

### How add this to my maven project:

- add repository
```xml
<repositories>
    <repository>
        <id>qiwi-client-mvn-repo</id>
        <url>https://raw.github.com/staliang/qiwi-client/mvn-repo/</url>
        <snapshots>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
        </snapshots>
    </repository>
</repositories>
```
- add dependency
```xml
<dependencies>
    <dependency>
        <groupId>com.staliang</groupId>
        <artifactId>qiwi-client</artifactId>
        <version>1.1</version>
    </dependency>
</dependencies>
```
