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
│   │   │           │   ├── MyWebApplicationInitializer.java
│   │   │           │   └── WebConfig.java
│   │   │           ├── controller/
│   ├── resources/
│   │   ├── templates/
│   │   │   └── hello.html
│   │   └── application.properties
│   └── webapp/
│       └── WEB-INF/
│           └── web.xml（2025.03.22 ※不要）
├── build.gradle
└── settings.gradle
```

## やった事
IntelliJでWebアプリをTomcatにデプロイして起動・デバッグまでにやった事
- Amazon Corretoのダウンロード
- IntelliJ Ideaのダウンロード+日本語化
- IntelliJ Ideaから、Javaプロジェクトの作成（Gradle指定）
- Javaプロジェクトを、上記Webプロジェクトの構成に変更
- build.gradleで依存関係や、warコマンド利用の為のプラグイン設定を記載
- IntelliJ IdeaにSmartTomcatプラグインをインストール
- Tomcatダウンロード
- 環境変数設定
  - ユーザー環境変数に「CATARINA_HOME」追加
  - ユーザー環境変数「PATH」に「%CATALINA_HOME%\bin」
  - システム環境変数に「JAVA_HOME」追加
- IntelliJ Ideaの実行構成に「SmartTomcat」が追加されているので、Tomcatを指定し起動

## メモ（重要な部分）
MyWebApplicationInitializer.java
```java
public class MyWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // Springのアプリケーションコンテキストを作成
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebConfig.class); // Java Configクラスを登録

        // DispatcherServletを登録
        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher", dispatcherServlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/"); // URLパターンを指定
    }
}
```
- 従来はweb.xml（デプロイメン記述子）が必要。
- 現在はWebApplicationInitializerを使用する事で、dispatcherを登録する事が出来るので無くせる。

WebConfig.java
```java
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.example")
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public ClassLoaderTemplateResolver templateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/"); // テンプレートの場所
        templateResolver.setSuffix(".html");      // テンプレートの拡張子
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        // 優先順位を設定
        viewResolver.setOrder(1);
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
}
```
- @Configuration: 明示的に設定を記述するために使用されるアノテーション。@Beanで定義されたBeanを管理。
- @ComponentScan: DI対象コンポーネントをスキャン。
- MyWebApplicationInitializerで、このJava Configクラスを登録する事で、他クラスでBean定義し、DI管理対象となる。
- @EnableWebMvc：Spring MVCを有効化するアノテーション。
- WebMvcConfigurer：カスタマイズ可能な設定を提供するインターフェース。

## TO DO
- [x] propertieファイル読み込み（環境毎）
- [x] log
- [ ] 一般的な構成（controller、service、model）
- [ ] DB接続
- [ ] bootstrapを使用したcrud画面の作成
- [ ] バリデーション処理
- [ ] セキュリティ関連
  - [ ] ログイン機能
  - [ ] ダブルサブミット
  - [ ] CSRF対策

