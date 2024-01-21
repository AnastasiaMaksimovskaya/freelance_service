package com.free.freelance_service.service;

import com.free.freelance_service.entity.ExchangeCurrency;
import com.free.freelance_service.enums.CurrencyEnum;
import com.free.freelance_service.repo.ExchangeCurrencyRepo;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Service
public class ExchangeCurrencyService {
    private static final String API_KEY = "ce9be156d2a9c1913a236fc25d9f2c98";

    @Autowired
    private ExchangeCurrencyRepo exchangeCurrencyRepo;

    @Scheduled(cron = "0 00 23 * * *")
    public void getConvertedToUSD() {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            HttpResponse<String> response = Unirest.get("http://data.fixer.io/api/" +
                            formatter.format(Date.from(new Date().toInstant().minus(1, ChronoUnit.DAYS))) +
                            "?access_key="+ API_KEY +
                            "&base=EUR" +
                            "&symbols="+ StringUtils.join(CurrencyEnum.values(), ","))
                    .asString();
            String body = response.getBody();
            JSONObject json = new JSONObject(body);
            JSONObject rates = json.getJSONObject("rates");
            List<ExchangeCurrency> updated = new ArrayList<>();

            for (String key : rates.keySet()) {
                Double value = rates.getDouble(key);
                ExchangeCurrency exchangeCurrency = new ExchangeCurrency(key, value);
                updated.add(exchangeCurrency);
            }
            exchangeCurrencyRepo.saveAll(updated);
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }
}
