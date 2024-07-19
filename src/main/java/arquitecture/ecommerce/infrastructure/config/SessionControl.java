package arquitecture.ecommerce.infrastructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import arquitecture.ecommerce.application.usecases.ShopCarService;
import arquitecture.ecommerce.domain.models.ShopCar;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@Component
public class SessionControl implements HttpSessionListener {
    
    @Autowired
    private ShopCarService shopCarService;

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ShopCar shopCar = (ShopCar) se.getSession().getAttribute("shopCar");

        if (shopCar != null && !shopCar.isCompleted()) {
            shopCarService.deactivateShopCar(shopCar);
        }
    }

}
