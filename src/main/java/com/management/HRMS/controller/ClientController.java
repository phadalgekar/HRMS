package com.management.HRMS.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.management.HRMS.entity.Client;
import com.management.HRMS.repository.ClientRepository;

@Controller
public class ClientController {

	@Autowired
	private ClientRepository clientRepo;
	
    @GetMapping("/addClient")
    public String showAddClientForm(Model model) {
    	model.addAttribute("client", new Client());
    	return "add_client";
    }

    @PostMapping("/saveClient")
    public String saveClient(Client client) {
    	clientRepo.save(client);
    	return "add_client";
 
    }
    
    @GetMapping("/viewclient")
	public String viewClientList(Model model) {
		// Retrieve the list of clients from the database
		List<Client> clients = clientRepo.findAll();
		
		// Add the list of clients to the model for rendering in the view
		model.addAttribute("clients", clients);
		
		return "view_client"; 
	}
    
    @GetMapping("/editClient/{clid}")
	public String showEditClientForm(@PathVariable Long clid, Model model) {
		// Retrieve the client with the given ID from the database
		Client client = clientRepo.findById(clid).orElse(null);
		
		model.addAttribute("client", client);
		
		return "edit_client";
	}
    
    @PostMapping("/updateClient")
	public String updateClient(@ModelAttribute Client client) {
		clientRepo.save(client);
		return "redirect:/viewclient";
	}
    
    @GetMapping("/deleteClient/{clid}")
	public String deleteClient(@PathVariable Long clid) {
		// Delete the client with the given ID from the database
		clientRepo.deleteById(clid);
		
		return "redirect:/viewclient";
	}
    @GetMapping("/viewClientBy/{clid}")
    public String viewClientById(@PathVariable Long clid, Model model) {
        Optional<Client> clientOptional = clientRepo.findById(clid);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            model.addAttribute("client", client);
            return "view_client_by_id"; 
        } else {
            return "client_not_found"; 
        }
    }
}
