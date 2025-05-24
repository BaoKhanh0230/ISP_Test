package com.hospital.controller;

import com.hospital.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientDashboardController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        return "patientDashboard";
    }

    @GetMapping("/searchDoctor")
    public String searchDoctor(@RequestParam String query, Model model) {
        List<com.hospital.model.Doctor> doctors = appointmentService.searchDoctors(query);
        model.addAttribute("doctors", doctors);
        return "patientDashboard :: doctorSearchResults";
    }
}