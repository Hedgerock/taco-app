package com.hedgerock.spirng.spring_in_action.reactor.introduction;

import reactor.core.publisher.Mono;

public class ReactorExample {

    public static void main(String[] args) {

//        String name = "Craig";
//        String capitalName = name.toUpperCase();
//
//        String greeting = "Hello " + capitalName + " !";
//
//        System.out.println(greeting);


        Mono.just("Craig")
                .map(String::toUpperCase)
                .map(name -> "Hello " + name + " !")
                .subscribe(System.out::println);

    }

}
