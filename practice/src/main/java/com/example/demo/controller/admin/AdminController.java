package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Contact;
import com.example.demo.service.ContactService;

@Controller
@RequestMapping("/admin")

public class AdminController {
	
	@Autowired
	private ContactService contactService;
	
	//一覧取得メソッド
	@GetMapping("/contacts")
	public String list(Model model) {
		List<Contact> contacts = contactService.findAllContacts();
		model.addAttribute("contacts", contacts);
		return "admin/contacts/list";
	}

	//あとで詳細・編集用メソッドもここに追加
}
