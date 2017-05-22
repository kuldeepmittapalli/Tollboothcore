package play.tollboothcore.config;

import com.google.inject.AbstractModule;

import play.tollboothcore.service.ChargeGatewayService;
import play.tollboothcore.service.ChargeGatewayServiceImpl;



public class ApplicationContextModule extends AbstractModule {

    @Override
    protected void configure() {
        bind( Global.class ).asEagerSingleton();
        bind(ChargeGatewayService.class).to(ChargeGatewayServiceImpl.class);

    }

}