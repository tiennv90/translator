package com.flipmind.localizationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.flipmind.localizationservice.models.Document;
import com.flipmind.localizationservice.models.DocumentString;
import com.flipmind.localizationservice.models.Locale;
import com.flipmind.localizationservice.models.Project;
import com.flipmind.localizationservice.models.Status;
import com.flipmind.localizationservice.models.Tenant;
import com.flipmind.localizationservice.models.TranslatedString;
import com.flipmind.localizationservice.models.Translation;
import com.flipmind.localizationservice.repositories.DocumentRepository;
import com.flipmind.localizationservice.repositories.DocumentStringRepository;
import com.flipmind.localizationservice.repositories.LocaleRepository;
import com.flipmind.localizationservice.repositories.ProjectRepository;
import com.flipmind.localizationservice.repositories.TenantRepository;
import com.flipmind.localizationservice.repositories.TranslatedStringRepository;
import com.flipmind.localizationservice.repositories.TranslationRepository;
import com.mangofactory.swagger.models.dto.ApiKey;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class TranslatorApplication implements CommandLineRunner {

	@Autowired
	private TenantRepository tenantRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private DocumentRepository documentRepository;
	
	@Autowired
	private LocaleRepository localeRepository;
	
	@Autowired
	private TranslationRepository translationRepository;
	
	@Autowired
	private DocumentStringRepository documentStringRepository;
	
	@Autowired
	private TranslatedStringRepository translatedStringRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(TranslatorApplication.class, args);
	}

	@Bean
	public Docket translatorApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("full-localizationservice-api").apiInfo(apiInfo())
				.select().build();
	}

	
	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo(
				"Localization Service",
				"The Localization Service acts as a centralized repository for all our resource bundles used by our Java Spring and Angular apps",
				"1.0", "", "", "@flipmind", "");
		return apiInfo;

	}
	
	@Bean
	public ApiKey apiKey() {
		return new ApiKey("api_key", "header");
	}
	
	@Bean
	public ReloadableResourceBundleMessageSource messageResource() {
		ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
		resource.setBasename("classpath:messages");
		resource.setCacheSeconds(1);
		return resource;
	}

	@Override
	public void run(String... args) throws Exception {
		
//		Tenant tenant = new Tenant();
//		tenant.setApiKey("aaa");
//		tenant.setName("Flip Mind");
//		tenant.setUserName("admin");
//		tenant.setPassword("password");
//		
//		tenant = tenantRepository.save(tenant);
//
//		Project p = new Project();
//		p.setSlug("project1");
//		p.setTitle("My First Project");
//		p.setTenant(tenant);
		
//		Project p = projectRepository.findOne(new Long(1));
//		Document document = new Document();
//		document.setTitle("Test Document 1");
//		document.setSlug("document1");
//		document.setPotContent("msgid 'Enter a comma separated list of user names.' msgstr ''");
//		document.setProject(p);
//		
//		Document document2 = new Document();
//		document2.setTitle("Test Document 2");
//		document2.setSlug("document2");
//		document2.setPotContent("msgid 'Enter a comma separated list of user names.' msgstr ''");
//		document2.setProject(p);
//		
//		documentRepository.save(document);
//		documentRepository.save(document2);
		
//		Locale locale = new Locale();
//		locale.setTitle("United States Of America");
//		locale.setPluralFormsExpression("form");
//		locale.setLocaleCode("en_us");
//		
//		locale = localeRepository.save(locale);
		
//		Locale locale = localeRepository.findOne(new Long(1));
//		Document document = documentRepository.findOne(new Long(2));
		
//		Translation translation = new Translation();
//		translation.setStatus(Status.DRAFT);
//		translation.setDocument(document);
//		translation.setLocale(locale);
//		
//		translationRepository.save(translation);
		
//		DocumentString documentString = new DocumentString();
//		documentString.setStringKey("login.login.attempts");
//		documentString.setStringValue("You have made 0 login attempts");
//		documentString.setDescription("content for login page");
//		documentString.setDocument(document);
//		
//		DocumentString documentString2 = new DocumentString();
//		documentString2.setStringKey("login.password.error");
//		documentString2.setStringValue("Please enter your password");
//		documentString2.setDescription("content for login page");
//		documentString2.setDocument(document);
//		
//		documentStringRepository.save(documentString);
//		documentStringRepository.save(documentString2);
		
//		Translation translation = translationRepository.findOne(new Long(2));
//		
//		TranslatedString translatedString = new TranslatedString();
//		translatedString.setKey("login.login.attempts");
//		translatedString.setValue("You have made 0 login attempts");
//		translatedString.setTranslation(translation);
//
//		TranslatedString translatedString2 = new TranslatedString();
//		translatedString2.setKey("login.password.error");
//		translatedString2.setValue("Please enter your password");
//		translatedString2.setTranslation(translation);
//		
//		translatedStringRepository.save(translatedString);
//		translatedStringRepository.save(translatedString2);
	}
	
	
}
