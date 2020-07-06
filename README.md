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

MVC 86

Add to controller @RequestMapping
До этого мы все это использовали на методы, а не на классы
Если использовать на класс, то получается вложенная структура. Родительский на класс и дочерний на методах

MVC 87

Создаем SillyController и создаем конфиликтную ситуацию с ранее сущесвовавшим контролером

MVC 88

Меняем HelloWorldController
@Controller
@RequestMapping("/hello")

и теперь все адреса будут начинаться с /hello и конфликта больше нет

-----------------------------------------------------------------

MVC 89

Spring MVC Form Tags

Overview

--------------------------------------------

19.06.2020

MVC 90

Text fields tags

@ModelAttribute("student") такой же "student" должен/может быть и в jsp

MVC 91

Создаем новый клас Student
Создаем StudentController
-в контролере в Model добавляем объект Student

MVC 92

создаем вью файл student-form.jsp
первая строка <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
создаем форму
	<form:form action="processForm" modelAttribute="student">
	
		First name: <form:input path="firstName"/>
	
		<br><br>
		
		Last name: <form:input path="lastName"/>
	
		<br><br>
	
		<input type="submit" value="Submit" />
	
	</form:form>
На нашем классе,
Когда форма загружается вызываются методы student.getFirstName() и student.getLastName() 
Когда форма отправляется вызываются методы student.setFirstName() и student.setLastName() 

Данные с нашей формы попадут в следующий код	@RequestMapping("/processForm")
	public String processForm(@ModelAttribute("student") Student theStudent) {
		
		//log the input data
		System.out.println(theStudent.getFirstName());
		System.out.println(theStudent.getLastName());
		return "student-confirmation";
	}

MVC 93

создаем страницу student-confirmation.jsp 
${student.firstName} ${student.lastName}  данные конструкции обращаются к методам класса Student

-----------------------------------------------

MVC 94

Drop Down List

MVC 95

Правим вью страницу student-form

		<form:select path="country">
		
			<form:option value="Brazil" label="Brazil" />
			<form:option value="France" label="France" />
			<form:option value="Germany" label="Germany" />
			<form:option value="India" label="India" />
		
		</form:select>
		
В клас Student добавляем country и get и set методы

В страницу student-confirmation добавляем
Country: ${student.country}

MVC 96

сделаем добавлаение списка, из кода

в Student добавляем поле private LinkedHashMap<String, String> countryOptions;

Правим форму в student-form

		<form:select path="country">
		
			<form:options items="${student.countryOptions}" />
		
		</form:select>

-----------------------------------------------

MVC 97

Radio Buttons

MVC 98

Изменяем student-form

		Java <form:radiobutton path="favoriteLanguage" value="Java"/>
		C# <form:radiobutton path="favoriteLanguage" value="C#"/>
		PHP <form:radiobutton path="favoriteLanguage" value="PHP"/>
		Ruby <form:radiobutton path="favoriteLanguage" value="Ruby"/>
		
добавляем в класс Student строку private String favoriteLanguage;
и создаем get and set methods

обновляем вью student-confirmation
Favorite Language: ${student.favoriteLanguage}

-------------------------------------------------------

MVC 99

Check Box

MVC 100

Изменяем student-form

		Operating Systems:
		
		Linux <form:checkbox path="operationgSystems" value="Linux"/>
		Mac OS <form:checkbox path="operationgSystems" value="Mac OS"/>
		MS Windows <form:checkbox path="operationgSystems" value="MS Windows"/>
		

добавляем в класс Student строку 
private String[] operationgSystems;

	
В student-confirmation добавляем код
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
	Operating Systems
	
	<ul>
		<c:forEach var="temp" items="${student.operationgSystems}">
			<li>${temp}</li>
		</c:forEach>
	</ul>
	
MVC 101

--------------------------------------

20.06.2020

MVC 102

Spring Form Validation
----------------------------

MVC 103

Java's standard Bean Validation API
Будем использовать Hibernate

MVC 104

www.hibernate.org
Hibernate Validator - скачиваем
копируем из архива файлы в наш проект

MVC 105

Validation rule

@NotNull(message="message for errore")
@Size(min=1, messge="message for errore")
private String lastName;

в html добавляем
<form:errors path="lastName" cssClass="error" />

так же меняется "возвратная страница" /processForm, в ней можно контролировать ошибку тегом @Valid

MVC 106

Создаем новый класс Customer
в нем анотации

	@NotNull(message="is required")
	@Size(min=1)	
	private String lastName;

MVC 107

создаем новое вью customer-form

MVC 108

Создаем новый класс CustomerController


MVC 109

Дописываем CustomerController
	
	@RequestMapping("/processForm")
	public String processForm(
			@Valid @ModelAttribute("customer") Customer theCustomer,
			BindingResult theBindingResult) {
		
		if (theBindingResult.hasErrors()) {
			return "customer-form";
		}else {
			return "customer-confirmation";			
		}
		
	}


MVC 110

создаем customer-confirmation.jsp

MVC 111

Problem white space

MVC 112

@InitBinder

MVC 113

добавляем код который будет удалять пробелы

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

---------------------------------------


21.06.2020

MVC 114

Validate a Number

@Min
@Max

MVC 115

Правим Customer.java

	@Min(value=0, message="must be greater then or equal to zero")
	@Max(value=0, message="must be less then or equal to 10")
	private int freePasses;

Правим customer-form.jsp
	
	Free passes: <form:input path="freePasses"/>
	<form:errors path="freePasses" cssClass="error" />
	
-------------------------------------------------------

MVC 116

Regular Expressions Validation

MVC 117

Правим Customer.java

	@Pattern(regexp="^[a-zA-Z0-9]{5}", message="only 5 chars/digits")
	private String postalCode;
	
Правим customer-form.jsp
		
		Postal Code: <form:input path="postalCode"/>
		<form:errors path="postalCode" cssClass="error" />
		
-----------------------------------------------------------

MVC 118

Integer Field Requered

Правим Customer.java
Добавляем на нужные поля

	@NotNull(message="is required")

Так же меняем int на Integer

----------------------------------------------------------------

MVC 119

String input for Integer Field

у нас есть проблемы если в числовое поле вводить строку

создадим новую папку resources
создадим новый файл messages.properties
в этом файле прописываем правила обработки ошибок

MVC 120

расположение и заполнение данного файла принципиально!

правим spring-mvc-demo-servlet.xml файл

	<!-- Load custom message resources -->
	
	<bean id="messageSource"
		class="org.springframeworkcontext.support.ResourceBundleMessageSource">
		
		<property name="basenames" value="resources/messages"></property>
	
	</bean>
	
MVC 121

Если в CustomerController добавить строку, то мы в сообщении увидим те названия которые можно использовать в файле выше (messages.properties)

		System.out.println(theBindingResult);

----------------------------------------

MVC 122

Custom Form Validation

наша будущая анотоция будет выглядеть примерно так:
	
	@CourseCode(value="LUV", message="must start with LUV")

MVC 123

Для определения своей собсвенной анотации используем код

	@Constraint(validatedBy = CourseCodeConstaintValidator.clss) //вспомогательный класс где будет наша логика
	@Target({ElementType.METHOD, ElementType.FIELD}) //тип анотации метод или поле
	@Retention(RetentionPolicy.RUNTIME) //время жизни анотации
	public @interface CourseCode { //	где @interface это специальная анотация, а CourseCode название нашей анотации
		
		//два метода внутри нашей будущей анотации
		public String value() default "LUV";
		
		public String message() default "must start with LUV"
		}
		
------------------------------------------------------------

#Hibernate
----------

22.06.2020
	
H129

H130

JDBC - Java Database Connectivity

H131

H132

Instal MySQL

23.06.3030

H133

Загружаем данные из файла в скл

hbstudent
hbstudent

H134

Setting up Hibernate in Eclipse

Hibernate ORM

ORM - Object-Relational Mapping

Качаем хайбернейт с сайта

качаем драйвер sql с dev.mysql.com Connector/J Platfrom Independent


H135

Test JDBC connection

JDBC - Java Database Connectivity 

Создадим новый пакет и класс TestJdbc для теста

H136

------------------------------------------------

H137

Создаем hibernate.cfg.xml файл.


H138

Hibernate Annotations

Entity Class
@Entity
@Table(name="student")		//в данном случае student не обязательно, поскольку совпадает с классом. если не указать возмет из класса
public class Student{

	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;

}

H139

Создадим класс Student в нем пропишем все что нужно для связи hibernate с sql

H140

Creating and Saving Java Objects

H141

Создадим новый класс и новый пакет CreateStudentDemo

в hibernate существует два понятия
-session factory
-session

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
				
"hibernate.cfg.xml" путь к конфигурационому файлу, при отсутствии идет в дефолный путь 
addAnnotatedClass(Student.class) указываем "map" класс, в котором описано все взаимодействие с hibernate

Создаем сесию 
	// create session
	Session session = factory.getCurrentSession();
	
Делаем запись

	try {
			// use the session object to save Java object
			System.out.println("Creating new student object... ");
			Student tempStudent = new Student("Paul", "Wall", "paul@sevod.org");
			
			// create a student object			
			session.beginTransaction();
			
			// save the student object
			System.out.println("Saving the student...");
			session.save(tempStudent);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
				
			
		} finally {
			factory.close();
		}
		
---------------------------------------------------------------

25.06.2020

H142

Primary keys

@ID
@GeneratedValue(strategy=GenerationType.IDENTITY) //указываем в явном виде стратегию для ID в БД

Типы GenerationType

AUTO
IDENTITY
SEQUENCE
TABLE

Можно вручную создать стратегию для гернерации ID

H143

Обьяснение внутренностей mySql

Правим класс Student
@GeneratedValue(strategy = GenerationType.IDENTITY)

Создаем PrimaryKeyDemo

H144

Changing the Starting index

очистить таблицу
truncate hb_student_tracker.student

---------------------------------

27.06.2020

H145 

Hibernate Reading Objects

Создадим ReadStudentDemo. Все опыты проведм в нем

Student myStudent = session.get(Student.class, tempStudent.getId());

----------------------------------------

H146

Querying Objects with Hibernate

HQL - Hibernate Query Language

H147

Создадим класс QueryStudentDemo

Получаем список всех студентов

	List<Student> theStudents = session.createQuery("from Student").list(); 
	
Список студентов у которых lastName = 'Doe'
	
	theStudents = session.createQuery("from Student s where s.lastName='Doe'").list();
	
H148

	theStudents = session.createQuery("from Student s where s.lastName='Doe' or s.firstName='Daffy'").getResultList();
	
	theStudents = session.createQuery("from Student s where s.email LIKE '%sevod.org'").getResultList();
	
------------------------------------------------

02.07.2020

H149

Updating Objects Hibernate

H150

Создаем класс UpdateStudentDemo

Update выполняем просто методом set класса после чего делаем 

	myStudent.setFirstName("Scooby");

	
можно выполнить групповое обновление с помощью HQL

			//update email for all students			
			session.createQuery("update Student set email='foo@gmail.com'")
				.executeUpdate();	
				
-----------------------------------------------------------------

H151

Deleting Objects Hibernate

H152

Создаем клас DeleteStudentDemo

	// delete the student
	session.delete(myStudent);
			
Удаление через HQL
	// delete the student HQL			
	session.createQuery("delete from Student where id=2").executeUpdate();

------------------------------------------------------------

H153 

Advanced Mappings
	-Multiple Tables
	-Relationships between Tables
	
H154
	
Database concepts

	-Primary key - id для строки
	-Foreign key - для перелинковки таблиц, поле в табилице которое ссылается на primaty key другой таблицы

	-cascade применяемое к одной таблице, применяется ко всем связанным таблицам
		-cascade delete

H155

OneToOne Hibernate

H156

H157

CascadeType

@OneToOne(cascade=CascadeType.ALL)

@OneToOne(cascade={CascadeType.DETACH,
					CascadeType.MERGE,
					CascadeType.PERSIST,
					CascadeType.REFRESH,
					CascadeType.REMOVE})


06.07.2020

H158

Создаем структуру ДБ	

Database -> Reversengineer			

H159

Копипастом создаем новую базу

hb-01-one-to-one-uni

Поправим настройки подключения. Переподключим все в новую базу hb-01-one-to-one-uni

Правим файл hibernate.cfg.xml

H160

Создаем и настраиваем InstrucorDetail class

H161

Создаем и настраиваем Instrucor class

Для установки взаимоотношений с классом InstrucorDetail создаем следующиюу конструкцию

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "instructor_detail_id")
	private InstructorDetail instructorDetail;

H162

Все действия ведем в CreateDemo

создаем Instrucor и InstructorDetail

Для их асоциации создаем конструкцию 

	// associate the objects			
	tempInstructor.setInstructorDetail(tempInstructorDetail);

H163

Для сохранения в БД используем код

	// save the instructor			
	session.save(tempInstructor);
	
этот же код сохранит и tempInstructorDetail, поскольку у нас эти классы связаны в видео H161

-----------------------------------------