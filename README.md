## 概要
Spring bootを使用せず、Spring MVCを利用したプロジェクトのテンプレートです。
- APサーバーはTomcatを想定しています。
- IDEはIntelliJ IDEAを想定しています。

## バージョン
| | |
|------|----------|
| java | 21（Amazon Correto） |
| spring-webmvc | 6.2.3 |
| thymeleaf-spring | 6:3.1.3 |
| thymeleaf | 3.1.3 |

## 構成（重要な所だけ）
```
SpringMVCTemplate/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           ├── config/
│   │   │           │   └── WebConfig.java
│   │   │           ├── controller/
│   ├── resources/
│   │   ├── templates/
│   │   │   └── hello.html
│   │   └── application.properties
│   └── webapp/
│       └── WEB-INF/
│           └── web.xml
├── build.gradle
└── settings.gradle
```


