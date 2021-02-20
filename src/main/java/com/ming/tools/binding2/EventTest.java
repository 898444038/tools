package com.ming.tools.binding2;

public class EventTest {

    public static void main(String[] args) {
        EventProducer producer = new EventProducer();
        producer.addListener(new EventConsumer());
        producer.setValue(2);
        producer.setValue(3);
    }

}
