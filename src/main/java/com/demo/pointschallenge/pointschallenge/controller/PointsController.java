package com.demo.pointschallenge.pointschallenge.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.pointschallenge.pointschallenge.dvo.Transactions;

@RestController
public class PointsController {
    
    
    //api for rewards
    @PostMapping("/api/points")
    public Map<String,Map<String,Integer>> calculateRewards(@RequestBody List<Transactions> transactionList){
        Map<String,Map<String,Integer>> monthlyRewardsMap=new HashMap<>();
        transactionList.forEach((transaction)->{
            String customername=transaction.getCustomerName();
            String transDate=transaction.getTransactionDate();
            int rewardPoints=calculatePoints(transaction.getTransacationAmount());

            monthlyRewardsMap.computeIfAbsent(customername, k -> new HashMap<String,Integer>());
            monthlyRewardsMap.get(customername).merge(transDate, rewardPoints, Integer::sum);

         }
            
        );
         // code prints the computed results to sysout -- put here for easy understanding
        monthlyRewardsMap.keySet().forEach(customer->{
                System.out.println("customer"+customer);
                Map<String,Integer> detailsMap=monthlyRewardsMap.get(customer);
                detailsMap.keySet().forEach((month)->{
                    System.out.println(month+"-"+detailsMap.get(month));
                });
            });
        return monthlyRewardsMap;
    }
    //method for calculating reward points 
    private int calculatePoints(double transacationAmount) {
        int points=0;
        if(transacationAmount>50){
            points+=transacationAmount-50; 
        }
        if(transacationAmount>100){
            points+= transacationAmount-100;
        }
        return points;
    }

}
