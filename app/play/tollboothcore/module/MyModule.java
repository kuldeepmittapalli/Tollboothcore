package play.tollboothcore.module;



import play.api.Configuration;
import play.api.Environment;
import play.api.inject.Binding;
import scala.collection.Seq;

public class MyModule extends play.api.inject.Module {
   



	@Override
	public Seq<Binding<?>> bindings(Environment arg0, Configuration arg1) {
		// TODO Auto-generated method stub
		return seq(
                bind(MyApi.class).toSelf()
        );

	}
}