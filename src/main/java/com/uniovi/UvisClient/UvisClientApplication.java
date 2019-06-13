package com.uniovi.UvisClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.uniovi.UvisClient.entities.dto.Node;

@SpringBootApplication
public class UvisClientApplication {
	
	public static Node initNode;
	
    public static void main( String[] args ) {
    	if (args.length>=1) {
    		String url = "ws://localhost:"+args[0]+"/uvischain";
    		initNode = new Node(url);
    		SpringApplication.run(UvisClientApplication.class, args);
    	}
    }
}
