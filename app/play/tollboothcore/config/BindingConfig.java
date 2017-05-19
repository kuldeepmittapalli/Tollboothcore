package play.tollboothcore.config;

import com.google.inject.AbstractModule;

import play.tollboothcore.service.ChargeGatewayService;
import play.tollboothcore.service.ChargeGatewayServiceImpl;

public class BindingConfig extends AbstractModule {
  @Override 
  protected void configure() {

     /*
      * This tells Guice that whenever it sees a dependency on a TransactionLog,
      * it should satisfy the dependency using a DatabaseTransactionLog.
      */
    bind(ChargeGatewayService.class).to(ChargeGatewayServiceImpl.class);


  }
}