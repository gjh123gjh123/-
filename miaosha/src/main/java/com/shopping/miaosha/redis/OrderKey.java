package com.shopping.miaosha.redis;

public class OrderKey extends BasePrefix {
    public OrderKey(String prefix) {
        super(prefix);
    }
    public static OrderKey getmiaoshaoorderbyuidgid=new OrderKey("moug");
}
