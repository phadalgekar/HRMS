package com.management.HRMS.controller;

import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.management.HRMS.entity.Employee;
import com.management.HRMS.repository.EmployeeRepository;
import com.management.HRMS.service.EmailMultipleService;
import com.management.HRMS.service.EmailService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/add")
	public String showAddEmployeeForm(Model model) {
		model.addAttribute("employee", new Employee());
		return "add_employee";
	}

	@Autowired
	private EmailService emailService; 
	// Inject EmailService

	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee,
			@RequestParam("img1File") MultipartFile img1File) {
		try {
			if (!img1File.isEmpty()) {
				employee.setImg1(img1File.getBytes());
			}

			// If you have additional image fields in the merged entity, you can handle them
			// here too.

		} catch (IOException e) {
			e.printStackTrace();
		}
		employeeRepository.save(employee);

		// Send email
		String toEmail = employee.getEmail();
		String subject = "Employee Registration Confirmation";
		String body = "Dear " + employee.getFname() + ",\nThank you for registering as an employee.\n\n"
				+ "Your email: " + employee.getEmail() + "\n" + "Employee ID: " + employee.getEid();

		emailService.sendRegistrationEmail(toEmail, subject, body);

		return "redirect:/viewemp";
	}

	@GetMapping("/viewemp")
	public String viewEmployees(Model model,
	                            @RequestParam(defaultValue = "0") int page,
	                            @RequestParam(defaultValue = "4") int size,
	                            @RequestParam(required = false) String keyword,
	                            @RequestParam(required = false) String sortField) {

	    Pageable pageable;

	    if (sortField != null && !sortField.isEmpty()) {
	        Sort sort = Sort.by(sortField);
	        pageable = PageRequest.of(page, size, sort);
	    } else {
	        pageable = PageRequest.of(page, size);
	    }

	    Page<Employee> employees;

	    if (keyword != null && !keyword.isEmpty()) {
	        employees = employeeRepository.findByFnameContainingIgnoreCaseOrLnameContainingIgnoreCase(keyword, keyword, pageable);
	    } else {
	        employees = employeeRepository.findAll(pageable);
	    }

	    model.addAttribute("employees", employees);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", employees.getTotalPages());
	    model.addAttribute("keyword", keyword);
	    model.addAttribute("sortField", sortField);

	    return "view_employees";
	}


	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable("id") Long id) {
		Employee employee = employeeRepository.findById(id).orElse(null);
		if (employee != null) {
			employeeRepository.delete(employee);
		}
		return "redirect:/viewemp";
	}

	// ... Other imports and class definition ...

	@GetMapping("/editEmployee/{eid}")
	public String showEditEmployeeForm(@PathVariable("eid") Long eid, Model model) {
		Employee employee = employeeRepository.findById(eid).orElse(null);
		if (employee != null) {
			// Check if img1 is not null before converting to Base64
			if (employee.getImg1() != null) {
				// Convert the byte[] image to Base64 string
				String img1Base64 = Base64.getEncoder().encodeToString(employee.getImg1());
				employee.setImg1Base64(img1Base64);
			}

			model.addAttribute("employee", employee);
			return "edit_employee";
		} else {
			return "redirect:/viewemp";
		}
	}

	@PostMapping("/saveEditedEmployee")
	public String saveEditedEmployee(@ModelAttribute("employee") Employee editedEmployee,
			@RequestParam("img1File") MultipartFile img1File) {
		try {
			if (!img1File.isEmpty()) {
				editedEmployee.setImg1(img1File.getBytes());
			}

			// If you have additional image fields in the Employee entity, you can handle
			// them
			// here too.

		} catch (IOException e) {
			e.printStackTrace();
		}
		employeeRepository.save(editedEmployee);
		return "redirect:/viewemp";
	}

	@GetMapping("/viewEmployee/{eid}")
	public String viewEmployee(@PathVariable("eid") Long eid, Model model) {
		Employee employee = employeeRepository.findById(eid).orElse(null);
		if (employee != null) {
			// Check if img1 is not null before converting to Base64
			if (employee.getImg1() != null) {
				// Convert the byte[] image to Base64 string
				String img1Base64 = Base64.getEncoder().encodeToString(employee.getImg1());
				employee.setImg1Base64(img1Base64);
			}

			model.addAttribute("employee", employee);
			return "viewByID";
		} else {
			return "redirect:/viewemp";
		}
	}

	/*
	 * @GetMapping("/map") public String showEmployeeMap(Model model) {
	 * List<Employee> employees = employeeRepository.findAll();
	 * model.addAttribute("employees", employees); return "map"; }
	 */
  //for multiple emails
	@Autowired
    private EmailMultipleService emailMultiService; // Inject the EmailMultipleService

	@GetMapping("/emailForm")
    public String getEmployees(Model model) {
        List<Employee> employees = employeeRepository.findAll();
        List<String> distinctEmails = employees.stream()
                .map(Employee::getEmail)
                .distinct()
                .collect(Collectors.toList());

        model.addAttribute("distinctEmails", distinctEmails);
        model.addAttribute("selectedEmails", new ArrayList<String>()); // Initialize the selectedEmails list
        return "emailForm";
    }

	 @PostMapping("/send-email")
	    public String sendEmail(@RequestParam("selectedEmails") List<String> selectedEmails,
	                            @RequestParam("emailSubject") String emailSubject,
	                            @RequestParam("emailBody") String emailBody,
	                            @RequestParam("img1File") MultipartFile img1File,
	                            @RequestParam("docFile") MultipartFile docFile) {
	        try {
	            // Handle image and document files if they are not empty
	            byte[] imgBytes = null;
	            byte[] docBytes = null;

	            if (!img1File.isEmpty()) {
	                imgBytes = img1File.getBytes();
	            }

	            if (!docFile.isEmpty()) {
	                docBytes = docFile.getBytes();
	            }

	            // Call the EmailMultipleService to send emails
	            emailMultiService.sendRegistrationEmail(selectedEmails, emailSubject, emailBody, imgBytes, docBytes);

	            // Optionally, you can add a success message or log here
	            System.out.println("Emails sent successfully.");

	        } catch (Exception e) {
	            // Handle exceptions that might occur during email sending
	            System.err.println("Error sending emails: " + e.getMessage());
	            // You might want to redirect to an error page or display an error message to the user
	        }

	        return "redirect:/emailForm";
	    }

		@GetMapping("/left")
		public String leftMenuBar(Model model) {
			model.addAttribute("employee", new Employee());
			return "leftbar";
		}
		
		@GetMapping("/exp")
		public String experiment(Model model) {
			model.addAttribute("employee", new Employee());
			return "experiment";
		}


}