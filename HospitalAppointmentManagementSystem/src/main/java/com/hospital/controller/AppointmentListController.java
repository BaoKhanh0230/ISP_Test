package com.hospital.controller;

import com.hospital.model.Appointment;
import com.hospital.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/patient/appointments")
public class AppointmentListController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public String showAppointmentList(Model model) {
        List<Appointment> appointments = appointmentService.getAllAppointmentsByPatient(1);
        model.addAttribute("appointments", appointments);
        return "appointmentList";
    }

    @GetMapping("/details/{id}")
    public String showAppointmentDetails(@PathVariable int id, Model model) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        model.addAttribute("appointment", appointment);
        return "appointmentDetails";
    }
}