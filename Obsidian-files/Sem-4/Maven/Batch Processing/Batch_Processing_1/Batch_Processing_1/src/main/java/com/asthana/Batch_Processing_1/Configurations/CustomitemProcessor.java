package com.asthana.Batch_Processing_1.Configurations;

import org.springframework.batch.item.ItemProcessor;

import com.asthana.Batch_Processing_1.Entity.Iris;

public class CustomitemProcessor implements ItemProcessor<Iris,Iris> {

    public Iris process(@SuppressWarnings("null") Iris item) throws Exception {
        double total = item.getSepal_length()+item.getSepal_width()+item.getPetal_length()+item.getPetal_width();
        item.setTotal(total);
        return item;
    }

}
