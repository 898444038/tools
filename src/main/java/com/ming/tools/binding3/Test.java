package com.ming.tools.binding3;

public class Test implements MyEventListener {
    public Test() {
        MyEventSource mes = new MyEventSource();
        mes.addMyEventListener(this);
        mes.setName("niu");
        mes.setName("niu2");
    }

    public static void main(String args[]) {
        new Test();
    }

    public void handleEvent(MyEvent me) {
        System.out.println("me.getSource():"+me.getSource());
        System.out.println("me.getsName():"+me.getsName());
    }
}
