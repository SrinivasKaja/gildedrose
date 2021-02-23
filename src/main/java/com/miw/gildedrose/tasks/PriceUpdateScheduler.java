package com.miw.gildedrose.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.miw.gildedrose.manager.ItemDetailsManager;

@Component
@EnableScheduling
public class PriceUpdateScheduler {
	
	@Autowired
	private ItemDetailsManager itemDetailsManager;
	
	@Scheduled(cron = "0 * * * * *")
	public synchronized void priceUpdateOnNoOfViews() {
		System.out.println("Starting the Scheduler at" + System.currentTimeMillis());
		itemDetailsManager.updatePriceEvryHour();
		System.out.println("Completed the Scheduler at" + System.currentTimeMillis());
	}
}
