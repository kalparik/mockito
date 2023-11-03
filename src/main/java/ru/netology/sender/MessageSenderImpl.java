package ru.netology.sender;

import java.util.HashMap;
import java.util.Map;

import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import static ru.netology.entity.Country.USA;

public class MessageSenderImpl implements MessageSender {

    public static final String IP_ADDRESS_HEADER = "x-real-ip";
    private final GeoService geoService;

    private final LocalizationService localizationService;

    public MessageSenderImpl(GeoService geoService, LocalizationService localizationService) {
        this.geoService = geoService;
        this.localizationService = localizationService;
    }

    public String send(Map<String, String> headers) {
        String ipAddress = String.valueOf(headers.get(IP_ADDRESS_HEADER)); // в этой строке null превращается в "null"
        if (ipAddress != null && !ipAddress.isEmpty()) { // поэтому данное условие всегда справедливо
            Location location = geoService.byIp(ipAddress); // при пустом IP/мапе здесь возникает ошибка
            System.out.printf("Отправлено сообщение: %s%n", localizationService.locale(location.getCountry()));
            return localizationService.locale(location.getCountry());
        }
        return localizationService.locale(Country.USA); // при данном написании кода эта ситуация невозможна
    }
}
