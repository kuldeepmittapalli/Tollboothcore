package play.tollboothcore.config;

import java.lang.reflect.Method;
import java.util.concurrent.CompletionStage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import play.Application;
import play.GlobalSettings;
import play.mvc.Action;
import play.mvc.Http.Request;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;



public class Global extends GlobalSettings{
	
	ApplicationContext context = null;
	@Override
	public void beforeStart(Application app) {
		
		// TODO Auto-generated method stub
		System.out.println("This is beforeStart");
		super.beforeStart(app);
	}

	@Override
	public void onStart(Application app) {
		// TODO Auto-generated method stub
		System.out.println("This is onStart");
		 context = new AnnotationConfigApplicationContext(TollboothSpringConfiguration.class);
		super.onStart(app);
	}

	@Override
	public void onStop(Application app) {
		// TODO Auto-generated method stub
		super.onStop(app);
	}

	@Override
	public CompletionStage<Result> onError(RequestHeader request, Throwable t) {
		// TODO Auto-generated method stub
		return super.onError(request, t);
	}

	@Override
	public Action onRequest(Request request, Method actionMethod) {
		// TODO Auto-generated method stub
		System.out.println("Inject all");

		context.getBean(TollboothSpringConfiguration.class);
		return super.onRequest(request, actionMethod);
	}

	@Override
	public CompletionStage<Result> onHandlerNotFound(RequestHeader request) {
		// TODO Auto-generated method stub
		return super.onHandlerNotFound(request);
	}

	@Override
	public CompletionStage<Result> onBadRequest(RequestHeader request, String error) {
		// TODO Auto-generated method stub
		return super.onBadRequest(request, error);
	}
	
	

	

}
