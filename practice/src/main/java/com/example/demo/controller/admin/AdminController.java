package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;
import com.example.demo.repository.ContactRepository;
import com.example.demo.service.ContactService;

@Controller
@RequestMapping("/admin")

public class AdminController {
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
    private ContactRepository contactRepository;
	
	//一覧取得メソッド
	@GetMapping("/contacts")
	public String list(Model model) {
		List<Contact> contacts = contactService.findAllContacts();
		model.addAttribute("contacts", contacts);
		return "admin/contacts/list";
	}

	//詳細表示
	@GetMapping("/contacts/{id}")
    public String show(@PathVariable Long id, Model model) {
        Contact c = contactService.findContactById(id)
                        .orElseThrow(() -> new RuntimeException("Not found: " + id));
        model.addAttribute("contact", c);
        return "admin/contacts/show";
    }
	
	//削除処理
    @PostMapping("/contacts/{id}/delete")
    public String delete(@PathVariable Long id) {
        contactService.deleteContact(id);
        return "redirect:/admin/contacts";
    }
	
    //更新処理
    @PostMapping("/contacts/{id}/edit")
    public String update(
            @PathVariable("id") Long id,
            @ModelAttribute("contactForm") ContactForm form) {

        Contact contact = contactService.findContactById(id)
            .orElseThrow(() -> new RuntimeException("Contact not found: " + id));

        contact.setLastName(form.getLastName());
        contact.setFirstName(form.getFirstName());
        contact.setEmail(form.getEmail());
        contact.setPhone(form.getPhone());
        contact.setZipCode(form.getZipCode());
        contact.setAddress(form.getAddress());
        contact.setBuildingName(form.getBuildingName());
        contact.setContactType(form.getContactType());
        contact.setBody(form.getBody());

        contactRepository.save(contact);

        return "redirect:/admin/contacts/" + id;
    }
    //編集処理
    @GetMapping("/contacts/{id}/edit")
    public String editForm(
        @PathVariable Long id,Model model) {
        Contact contact = contactService.findContactById(id)
            .orElseThrow(() -> new RuntimeException("Contact not found: " + id));
        model.addAttribute("contact", contact);
        return "admin/contacts/edit";
    }
    
    //作成処理
    @GetMapping("/contacts/new")
    public String newForm(Model model) {
    	model.addAttribute("contactForm", new ContactForm());
    	return "admin/contacts/new";
    }
    
    //登録処理
    @PostMapping("/contacts")
    public String create(@ModelAttribute("contactForm") ContactForm form) {
    	contactService.saveContact(form);
    	return "redirect:/admin/contacts";
    }

}
