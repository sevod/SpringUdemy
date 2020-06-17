# SpringUdemy конспект
11.06.2020
IoC Injection of Controll

Commit 16
Базовый каркас, продемонстрировано внедрение интерфейса
git checkout -b newbrench

B17
Commit 17
Создали новый класс с интерфейсом

B18
Commit 18 (не коммитил)
3 варианты конфигурирования Spring
1. XML
2. Java Annotations
3. Java Soutce Code

B19
Commit 19

Создали файл applicationContext.xml
В нем создаем новые бины
    <bean id="myCoach"
    	class="org.sevod.springdemo.TrackCoach">
    </bean>
	
Для подключения к нашей конфигурации и создания "контекста" используем
ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
Извлечение бина
Coach theCoach = context.getBean("myCoach", Coach.class);
Обязательно закрываем контекст после окончания работы
context.close();

B20 Ничего особенного

12.06.2020
B21 (не коммител)
-Spring Dependency injection (DI)
--Constructor injection
--Setter injection

B22

B23

B24 (не коммител)
Реализуем DI через конструктор
Создаем конструктор в нужном классе и меняем файл applicationContext.xml
    <bean id="myCoach"
    	class="org.sevod.springdemo.BaseballCoach">
    	
    	<!-- set up constructor injection -->
    	<constructor-arg ref="myFortune"></constructor-arg>
    </bean>
	
B25
В HelloSpringApp.java вызываем System.out.println(theCoach.getDailyFortune());
-------------------------------------------------------------------------------------
13.06.2020

B26
-Setter injection

B27

B28

Для Setter injection обязательно создаем конструктор без аргументов. Он будет вызываться spring ом.
Создаем set-ер "setFortuneService" в нужном нам классе, в файл applicationContext.xml добавляем код 
    <bean id="myCricketCoath"
    	class="org.sevod.springdemo.CricketCoach">
   		
   		<!-- set up setter injection -->
   		<property name="fortuneService" ref="myFortune"></property>
    </bean>
"fortuneService" соответсвует названию set-ера "setFortuneService". Разница подставляется автоматически. ref="myFortune" это аргументы сетера. Берутся из нашего xml.
Для примера с сетером создадим класс springdemo.

------------------------------------------------------------------------------
B29

Injection Literal Values

B30

1) Создаем set методы
2) Конфигурируем the injection in Spring config file applicationContext.xml
в ранее существовавший bean добавляем поля property
    <bean id="myCricketCoath"
    	class="org.sevod.springdemo.CricketCoach">
   		
   		<!-- set up setter injection -->
   		<property name="fortuneService" ref="myFortune"></property>
   		
   		<!-- inject literal values -->
   		<property name="emailAddress" value="thebestcoach@sevod.com"></property>
   		<property name="team" value="Sunriser Hyderabad"></property>
    </bean>
name задает имена наших set методов. value значение аргументов сетера.
В SetterDemoApp.java запускаем тест

-----------------------------------------------------------------------------
B31

Injection Values frome Properties File

1) создаем Properties File
2) поключаем его в Spring config file applicationContext.xml
3) прописываем Properties в этот файл

B32

1) Создаем sport.properties
2) поключаем
	<context:property-placeholder location="classpath:sport.properties"/>
3) 	меняем поле value
   		<!-- inject literal values -->
   		<property name="emailAddress" value="${foo.email}"></property>
   		<property name="team" value="${foo.team}"></property>
		
------------------------------------------------------------------------------
B33

Bean Scopes:

Default Scope: Singleton
-singleton
-prototype
-request
-session
-global-session

B34
создадим для теста новый beanScope-applicationContext.xml и BeanScopeDemoApp

B35
протестируем для scope = prototype, изменив конфигурационный файл Спринга
    <bean id="myCoach"
    	class="org.sevod.springdemo.TrackCoach"
    	scope="prototype">
    	
    	
    	<!-- set up constructor injection -->
    	<constructor-arg ref="myFortune"></constructor-arg>
    </bean>
----------------------------------------------------------------------------------

14.06.2020

B36

-Bean Lifecycle
--Init Method
--Destroy Method

B37

В TrackCoach создадим новые методы 
Скопируем для теста и создадим beanScope-applicationContext.xml

В нужный нам бин добавляем

init-method="doMyStartupStuff"
    	destroy-method="doMyCleanupStuff"> 
		
Для теста копируем и создаем BeanLifeCycleDemoApp
-----------------------------------------------------------------------------------------

Все что выше в другом репозитории

------------------------
Spring-demo-annotations
-------------------------
B38

Java Annotations

Создаем проект в котором будем использовать java анотации

B39

B40

Переписываем конфиг файл, прописываем расположение файлов спринга. 

	<context:component-scan base-package="org.sevod.springdemo"></context:component-scan>

Теперь Спринг будет сканировать это место при компиляции.

B41
@Component анотация

В классе TennisCoach создаем анотацию @Component("thatSillyCoach")

Создаем класс для тестов AnnotationDemodApp. 
Создаем контекст
ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
получаем наш бин
Coach theCoach = context.getBean("thatSillyCoach", Coach.class);
и теперь можем использовать theCoach.

---------------------------
15.06.2020

B42

@Components - Default Names
по умолчанию имена бинов, одинаковы с названием классов но начинаютс маленькой буквы

B43

Изменим TennisCoach класс.
В строке @Component("thatSillyCoach") оставляем только анотацию @Component

меняем код для вызова этого бина
		Coach theCoach = context.getBean("tennisCoach", Coach.class);

----------------------------------

B44

@Autowired
Имеет три типа Injections

-Constructor Injection
-Setter Injection
-Field Injections

B45

Constructor Injection @Autowired

B46

------------------------------------

B47

Setter Injection @Autowired

B48

В TennisCoach меняем Constructor injection на setter injection
Обязательно создаем пустой конструктор.
создаем сетер и анотацию над ним
	@Autowired
	public void setFortuneService(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}
	
---------------------------------------

B49

Methods Injection
Инжектить можно любые методы
@Autowired

В TennisCoach меняем setter метод, на произвольный

----------------------------------------

B50

Field injection

@Autowired используем прямо над нужным полем

B51

В TennisCoach делаем injection прямо над полем
@Autowired
private FortuneService fortuneService;

---------------------------------------------

B52

---------------------------------------------

B53

Qualifiers for DI

Если возникает непоределенность можно использовать анотацию @Qualifier
@Autowired
@Qualifier("someBean")
и название внутри нее - "someBean", четко указывая какой бин использовать

B54

Для создания непоределенности добавляем классов FortuneService, теперь их 4. При запуске приложения получаем ошибку, что у нас их 4 и не понятно который использовать.

Меняем код. Добавляем @Qualifier("happyFortuneService"). Ошибки нет, используется happyFortuneService

@Autowired
@Qualifier("happyFortuneService")
private FortuneService fortuneService;

B55

небольшая практика с rnd в randomFortuneService

------------------------------------------------

B56

@Scope 

Default singleton
@Scope("prototype")

B57

@Component
@Scope("prototype")

--------------------------------------------------

B58

Bean Lifecycle

Определяем методы для бина
@PostConstruct - после создания
@PreDestroy - перед удалением

B59

-----------------------------------------------------

B60

Spring Configuration with Java Code (no xml)

@Configuration
тут java класс
@ComponentScan("org.sevod.springdemo") (опционально) адрес пакета где все искать

AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
а так мы создаем контекст

B61

Создаем класс SportConfig, все настраиваем в нем
Добавляем класс JavaConfigDemoApp

--------------------------------------------------

16.06.2020

B62

Define Bean whith Java Code

@Bean

B63

создаем SwimCoach

B64

работаем в SportConfig.java

	// define bean for our sad fortune service
	@Bean
	public FortuneService sadFortuneService() {
		return new SadFortuneService();
	}
	
	// define bean for our swim coach AND inject dependency
	
	@Bean
	public Coach swimCoach() {
		return new SwimCoach(sadFortuneService());
	}
	
sadFortuneService явлется как назавнием метода, так и названием бина!

Создаем SwimJavaConfigDemoApp

В файле SportConfig.java коментируем строку //@ComponentScan("org.sevod.springdemo") посколько все исползуемые нами бины, мы прописали тут вручную

-------------------------------------------------------------

B65

Injecting Values frome Properties File

@PropertySource("classpath:sport.properties") - подключем файл со свойствами

@Value("${foo.email}")
private String email;

B66

Создаем properties файл sport.properties
Загружаем этот файл в спринг конфиг(SportConfig.java) @PropertySource("classpath:sport.properties")

B67

В файл SwimCoach добавляем код

	@Value("${foo.email}")
	private String email;
	
	@Value("foo.team")
	private String team;

создаем гетеры

правим и используем SwimJavaConfigDemoApp

----------------------------------------------------------------------

MVC 68
-------
Spring MVC
-----------

MVC 69

View Template
Самое частое JSP + JSTL
JavaServer Pages
JavaServer Pages Standard Tag Library

Можно использовать Thymeleaf, Groovy, Velocity, Freemarker и etc

MVC 70

MVC 71

MVC 72

Настраиваем библиотеки

MVC 73

создаем структуру файлов

MVC 74

Для создания контролера используем анотацию
@Controller

Контролер будет возвращать строку, указывающию на нашу view страницу

@RequestMapping("/")
Анотация, которая перенаправляет входящие запросы на наши ява методы

Ява методы возвращают строки, которые должны совпадать с назаванием view файлов

MVC 75

Создаем наш первый контроллер в файле HomeController.java

MVC 76

Запускаем Томкат сервер... :)

---------------------------------------------------------

17.06.2020

MVC 77

Reading HTML Form data

MVC 78

Создаем HelloWorldController контролер
Методы в нем
И соответствующие view формы

MVC 79

Делаем еще один метод в контроллере
	@RequestMapping("/processForm")
	public String prcessForm() {
		return "helloworld"; 
	}
	
Нам известно что после отправки формы будет запрос processForm от браузера
Так же в браузере будут доступны параметры из предыдущей формы ${param.studentName}

MVC 80

Проверяем все

---------------------------------------------------------------

MVC 81

Adding Data to the Spring Model

MVC 82

Правим код в HelloWorldController

@RequestMapping("/processFormVersionTwo")
	public String letsShoutDude(HttpServletRequest request, Model model) {
		
		//read the request parameter from the HTML form
		String theName = request.getParameter("studentName");
		
		// convert the data to all caps		
		theName = theName.toUpperCase();
		
		// create the message
		String result = "Yo! " + theName;
		
		// add message to the model
		model.addAttribute("message", result);

MVC 83

---------------------------------------------------------------

MVC 84

Reading HTML From Data whith @RequestParam Annotation

Все тоже самое что и выше, но используем другой код.

MVC 85

@RequestMapping("/processFormVersionThree")
	public String processFormVersionThree(@RequestParam("studentName") String theName, Model model) {		
	
		// convert the data to all caps		
		theName = theName.toUpperCase();
		
		// create the message
		String result = "Hey My Friend from v3! " + theName;
		
		// add message to the model
		model.addAttribute("message", result);
		
		
		return "helloworld";
	}
	
--------------------------------------------------------------------



