package com.example.kursworkapplication.data;

import com.example.kursworkapplication.OrdersActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrdersData {
    private static ArrayList<Order> orders  = new ArrayList<Order>();

    public OrdersData(){
    }

    public Order getOrder(Long id){
        for(Order order : orders){
            if (order.getId() == id){
                return order;
            }
        }
        return null;
    }
    public List<Order> findAllOrders(String userLogin){
        return orders;
    }
    public Order addOrder(int calorie, String wishes, String userLogin){
        Order order = new Order();
        order.setCalorie(calorie);
        order.setUserLogin(userLogin);
        order.setWishes(wishes);
        long id = 1;
        for(Order ord : orders){
            if (ord.getId() >= id){
                id = ord.getId() + 1;
            }
        }
        order.setId(id);
        orders.add(order);
        return order;
    }
    public Order updateOrder(long id, int calorie, String wishes, String userLogin){
        Order order = getOrder(id);
        if (order == null){
            return null;
        }
        order.setCalorie(calorie);
        order.setUserLogin(userLogin);
        order.setWishes(wishes);;
        for(int i = 0; i < orders.size(); ++i){
            if (orders.get(i).getId() == id){
                orders.set(i, order);
                break;
            }
        }
        return order;
    }
    public Order deleteOrder(Long id){
        Order order = getOrder(id);
        if (order == null){
            return null;
        }
        for(int i = 0; i < orders.size(); ++i){
            if (orders.get(i).getId() == id){
                orders.remove(i);
                return order;
            }
        }
        return null;
    }
}
