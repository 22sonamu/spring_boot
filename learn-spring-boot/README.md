# Spring Boot의 목표

-----------

1. **프로덕션 환경**에서 사용가능한 어플리케이션을 **빠르게** 빌드할 수 있도록 돕는 것
    
    - 빠르게
      
        - Spring Initializr (start.spring.io)
        - Spring Boot Starter Projects : 프로젝트의 의존성을 빠르게 정의할수있다.
        - Spring Boot Auto Configuration : 클래스 경로에 있는 의존성에 따라 자동으로 설정이 주입된다.
        - Spring Boot DevTools : 수동으로 서버를 다시 시작하지 않아도 어플리케이션을 변경할 수 있다.
   
    - 프로덕션 환경에 사용 가능하도록

        - Logging
        - Different Configuration for Different Environments : ex. dev , prod 환경 
          
            - Profies, ConfigurationProperties
        - Monitoring 


# 빠른 개발을 위해 Spring Boot 가 제공하는 것

-------

### Spring Boot Starter Projects



원래 어플리케이션을 빌드하기 위해서는 여러가지 프레임워크가 필요하다 (ex. Spring, Spring MVC, Tomcat, Json conversion....)
이런 프레임워크들을 그룹화해서 어플리케이션을 쉽게 빌드할 수 있게 해주는 것이 Spring Boot Starter Project이다.
xml에 필요한 의존성들을 추가해서 그룹화할수 있다.


ex) spring boot starter web
~~~xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
~~~

웹 개발에 필요한 

- spring-boot-starter
- jackson
- spring-core
- spring-mvc
- spring-boot-starter-tomcat

을 포함하고있는 의존성이다.

### Spring boot auto configuration 

- Auto Configuration은 다음에 기반하여 결정된다.
  
    - Class path에 있는 프레임워크 (@EnableAutoConfiguration)
      
        - class path ? : /src/main/java/ 혹은 /src/main/resources 
    - 제공된 기존 설정

- 예시 ) Spring starter web 
  
    - Default Error Pages(ErrorMvcAutoConfiguration)
    - Bean <-> JSON (JacksonHttpMessageConvertersConfiguration)
    - Embedded Servlet Container - Tomcat is the default (EmbeddedWebServerFactoryCustomizerAutoConfiguration) : 톰캣을 이용하여 어플리케이션 실행

ErrorMvcAutoConfiguration.class
~~~java
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.springframework.boot.autoconfigure.web.servlet.error;

import jakarta.servlet.Servlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.framework.autoproxy.AutoProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type;
import org.springframework.boot.autoconfigure.template.TemplateAvailabilityProvider;
import org.springframework.boot.autoconfigure.template.TemplateAvailabilityProviders;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.util.HtmlUtils;

@AutoConfiguration(
        before = {WebMvcAutoConfiguration.class}
)
@ConditionalOnWebApplication(
        type = Type.SERVLET
)
@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
@EnableConfigurationProperties({ServerProperties.class, WebMvcProperties.class})
public class ErrorMvcAutoConfiguration {
    private final ServerProperties serverProperties;

    public ErrorMvcAutoConfiguration(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }

    @Bean
    @ConditionalOnMissingBean(
            value = {ErrorAttributes.class},
            search = SearchStrategy.CURRENT
    )
    public DefaultErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes();
    }

    @Bean
    @ConditionalOnMissingBean(
            value = {ErrorController.class},
            search = SearchStrategy.CURRENT
    )
    public BasicErrorController basicErrorController(ErrorAttributes errorAttributes, ObjectProvider<ErrorViewResolver> errorViewResolvers) {
        return new BasicErrorController(errorAttributes, this.serverProperties.getError(), errorViewResolvers.orderedStream().toList());
    }

    @Bean
    public ErrorPageCustomizer errorPageCustomizer(DispatcherServletPath dispatcherServletPath) {
        return new ErrorPageCustomizer(this.serverProperties, dispatcherServletPath);
    }

    @Bean
    public static PreserveErrorControllerTargetClassPostProcessor preserveErrorControllerTargetClassPostProcessor() {
        return new PreserveErrorControllerTargetClassPostProcessor();
    }

    static class ErrorPageCustomizer implements ErrorPageRegistrar, Ordered {
        private final ServerProperties properties;
        private final DispatcherServletPath dispatcherServletPath;

        protected ErrorPageCustomizer(ServerProperties properties, DispatcherServletPath dispatcherServletPath) {
            this.properties = properties;
            this.dispatcherServletPath = dispatcherServletPath;
        }

        public void registerErrorPages(ErrorPageRegistry errorPageRegistry) {
            ErrorPage errorPage = new ErrorPage(this.dispatcherServletPath.getRelativePath(this.properties.getError().getPath()));
            errorPageRegistry.addErrorPages(new ErrorPage[]{errorPage});
        }

        public int getOrder() {
            return 0;
        }
    }

    static class PreserveErrorControllerTargetClassPostProcessor implements BeanFactoryPostProcessor {
        PreserveErrorControllerTargetClassPostProcessor() {
        }

        public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
            String[] errorControllerBeans = beanFactory.getBeanNamesForType(ErrorController.class, false, false);
            String[] var3 = errorControllerBeans;
            int var4 = errorControllerBeans.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String errorControllerBean = var3[var5];

                try {
                    beanFactory.getBeanDefinition(errorControllerBean).setAttribute(AutoProxyUtils.PRESERVE_TARGET_CLASS_ATTRIBUTE, Boolean.TRUE);
                } catch (Throwable var8) {
                }
            }

        }
    }

    private static class StaticView implements View {
        private static final MediaType TEXT_HTML_UTF8;
        private static final Log logger;

        private StaticView() {
        }

        public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
            if (response.isCommitted()) {
                String message = this.getMessage(model);
                logger.error(message);
            } else {
                response.setContentType(TEXT_HTML_UTF8.toString());
                StringBuilder builder = new StringBuilder();
                Object timestamp = model.get("timestamp");
                Object message = model.get("message");
                Object trace = model.get("trace");
                if (response.getContentType() == null) {
                    response.setContentType(this.getContentType());
                }

                builder.append("<html><body><h1>Whitelabel Error Page</h1>").append("<p>This application has no explicit mapping for /error, so you are seeing this as a fallback.</p>").append("<div id='created'>").append(timestamp).append("</div>").append("<div>There was an unexpected error (type=").append(this.htmlEscape(model.get("error"))).append(", status=").append(this.htmlEscape(model.get("status"))).append(").</div>");
                if (message != null) {
                    builder.append("<div>").append(this.htmlEscape(message)).append("</div>");
                }

                if (trace != null) {
                    builder.append("<div style='white-space:pre-wrap;'>").append(this.htmlEscape(trace)).append("</div>");
                }

                builder.append("</body></html>");
                response.getWriter().append(builder.toString());
            }
        }

        private String htmlEscape(Object input) {
            return input != null ? HtmlUtils.htmlEscape(input.toString()) : null;
        }

        private String getMessage(Map<String, ?> model) {
            Object path = model.get("path");
            String message = "Cannot render error page for request [" + path + "]";
            if (model.get("message") != null) {
                message = message + " and exception [" + model.get("message") + "]";
            }

            message = message + " as the response has already been committed.";
            message = message + " As a result, the response may have the wrong status code.";
            return message;
        }

        public String getContentType() {
            return "text/html";
        }

        static {
            TEXT_HTML_UTF8 = new MediaType("text", "html", StandardCharsets.UTF_8);
            logger = LogFactory.getLog(StaticView.class);
        }
    }

    private static class ErrorTemplateMissingCondition extends SpringBootCondition {
        private ErrorTemplateMissingCondition() {
        }

        public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
            ConditionMessage.Builder message = ConditionMessage.forCondition("ErrorTemplate Missing", new Object[0]);
            TemplateAvailabilityProviders providers = new TemplateAvailabilityProviders(context.getClassLoader());
            TemplateAvailabilityProvider provider = providers.getProvider("error", context.getEnvironment(), context.getClassLoader(), context.getResourceLoader());
            return provider != null ? ConditionOutcome.noMatch(message.foundExactly("template from " + provider)) : ConditionOutcome.match(message.didNotFind("error template view").atAll());
        }
    }

    @Configuration(
            proxyBeanMethods = false
    )
    @ConditionalOnProperty(
            prefix = "server.error.whitelabel",
            name = {"enabled"},
            matchIfMissing = true
    )
    @Conditional({ErrorTemplateMissingCondition.class})
    protected static class WhitelabelErrorViewConfiguration {
        private final StaticView defaultErrorView = new StaticView();

        protected WhitelabelErrorViewConfiguration() {
        }

        @Bean(
                name = {"error"}
        )
        @ConditionalOnMissingBean(
                name = {"error"}
        )
        public View defaultErrorView() {
            return this.defaultErrorView;
        }

        @Bean
        @ConditionalOnMissingBean
        public BeanNameViewResolver beanNameViewResolver() {
            BeanNameViewResolver resolver = new BeanNameViewResolver();
            resolver.setOrder(2147483637);
            return resolver;
        }
    }

    @Configuration(
            proxyBeanMethods = false
    )
    @EnableConfigurationProperties({WebProperties.class, WebMvcProperties.class})
    static class DefaultErrorViewResolverConfiguration {
        private final ApplicationContext applicationContext;
        private final WebProperties.Resources resources;

        DefaultErrorViewResolverConfiguration(ApplicationContext applicationContext, WebProperties webProperties) {
            this.applicationContext = applicationContext;
            this.resources = webProperties.getResources();
        }

        @Bean
        @ConditionalOnBean({DispatcherServlet.class})
        @ConditionalOnMissingBean({ErrorViewResolver.class})
        DefaultErrorViewResolver conventionErrorViewResolver() {
            return new DefaultErrorViewResolver(this.applicationContext, this.resources);
        }
    }
}

~~~


### Spring Boot DevTools

- 코드 변경 시, 자동으로 서버를 다시 시작하고 코드 변경사항을 적용한다.
- pom.xml 에 수정사항이 있을 떈 수동으로 재시작해야한다.

# 프로덕션 환경에서 사용 가능한 어플리케이션을 쉽게 만들 수 있도록 Spring boot가 제공하는 것들


-----------


### Profile을 사용하여 어플리케이션 설정을 관리


### 로깅

- off : 전체 로깅을 끈다.
- error : 오류와 예외만 출력
- warning
- info
- debug
- trace : 로그에 있는 모든 정보



