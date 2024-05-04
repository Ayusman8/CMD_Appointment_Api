package com.tg.cmd.cmd_appointment_service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import com.tg.cmd.cmd_appointment_service.exception.AppointmentAlreadyExistsException;
import com.tg.cmd.cmd_appointment_service.exception.AppointmentNotFoundException;
import com.tg.cmd.cmd_appointment_service.models.Appointment;
import com.tg.cmd.cmd_appointment_service.models.AppointmentStatus;
import com.tg.cmd.cmd_appointment_service.repositories.AppointmentRepository;
import com.tg.cmd.cmd_appointment_service.services.AppointmentService;
import com.tg.cmd.cmd_appointment_service.services.impl.AppointmentServiceImpl;

class CmdAppointmentsServiceApplicationTests {

	private AppointmentService service = new AppointmentServiceImpl();

}
