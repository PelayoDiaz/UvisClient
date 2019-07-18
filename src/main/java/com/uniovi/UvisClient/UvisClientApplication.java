package com.uniovi.UvisClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.uniovi.UvisClient.communication.BlockChainSessionHandler;
import com.uniovi.UvisClient.communication.Updater;
import com.uniovi.UvisClient.entities.dto.BlockChainDto;
import com.uniovi.UvisClient.entities.dto.Node;
import com.uniovi.UvisClient.repositories.BlockChainRepository;

@SpringBootApplication
public class UvisClientApplication {
	
	public static final String LISTENER = "/app/chain/sendChain";
	
    public static void main( String[] args ) {
    	if (args.length>=1) {
    		String url = "ws://localhost:"+args[0]+"/uvischain";
    		Node initNode = new Node(url);
    		BlockChainRepository.getInstance().setActualNode(initNode);
    		Updater updater = new Updater(new BlockChainDto(), BlockChainRepository.getInstance().getActualNode().getUrl(), new BlockChainSessionHandler(), LISTENER);
    		updater.start();
    		SpringApplication.run(UvisClientApplication.class, args);
    	}
    }
}
