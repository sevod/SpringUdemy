# SpringUdemy
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
spring-demo-annotations
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

B42

@Components - Default Names
по умолчанию имена бинов, одинаковы с названием классов но начинаютс маленькой буквы

B43

Изменим TennisCoach класс.
В строке @Component("thatSillyCoach") оставляем только анотацию @Component

меняем код для вызова этого бина
		Coach theCoach = context.getBean("tennisCoach", Coach.class);

----------------------------------
