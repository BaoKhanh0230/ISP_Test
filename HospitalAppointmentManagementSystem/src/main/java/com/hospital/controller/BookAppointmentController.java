package com.hospital.controller;

import com.hospital.model.Appointment;
import com.hospital.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient/book")
public class BookAppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public String showBookAppointmentForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("specialties", appointmentService.getAllSpecialties());
        model.addAttribute("doctors", appointmentService.getAllDoctors());
        return "bookAppointment";
    }

    @PostMapping
    public String submitAppointment(@ModelAttribute Appointment appointment, Model model) {
        appointment.setPatientId(1);
        appointmentService.saveAppointment(appointment);
        return "redirect:/patient/appointments";
    }
}