package com.soap.api.students.endpoint;

import com.soap.api.students.dto.*;
import com.soap.api.students.exception.ServiceFaultException;
import com.soap.api.students.model.StudentDetails;
import com.soap.api.students.service.StudentService;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;

@Endpoint
public class StudentEndpoint {
    private static final String NAMESPACE_URI = "http://api.soap.com/students/dto";

    @Autowired
    private StudentService studentService;

    @Resource
    private WebServiceContext ctx;

    @SneakyThrows
    @PayloadRoot(namespace = NAMESPACE_URI,
            localPart = "StudentDetailsRequest")
    @ResponsePayload
    public StudentDetailsResponse getStudentRequest(@RequestPayload StudentDetailsRequest request) throws Exception {
        StudentDetailsResponse response = new StudentDetailsResponse();
        Student student = new Student();
        StudentDetails studentDetails;
        try {
            studentDetails = studentService.createStudent(request);
            BeanUtils.copyProperties(studentDetails, student);
        } catch (Exception ex) {
            String msg = "Internal server issue";
            ServiceStatus status = new ServiceStatus();
            status.setStatusCode("500");
            status.setMessage(msg);
            throw new ServiceFaultException(msg, status);
        }
        response.setStudent(student);

        return response;
    }

    @SneakyThrows
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getStudentByIdRequest")
    @ResponsePayload
    public GetStudentByIdResponse getStudent(@RequestPayload GetStudentByIdRequest request) {
        GetStudentByIdResponse response = new GetStudentByIdResponse();
        StudentInfo studentInfo = new StudentInfo();
        StudentDetails studentDetails = studentService.getStudentById(request.getId());
        if (null == studentDetails) {
            String msg = String.format("Details of student with id %s is not found", request.getId());
            ServiceStatus status = new ServiceStatus();
            status.setStatusCode("404");
            status.setMessage(msg);
            throw new ServiceFaultException(msg, status);
        }
        BeanUtils.copyProperties(studentDetails, studentInfo);
        response.setStudentInfo(studentInfo);
        return response;
    }

    @SneakyThrows
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteStudentByIdRequest")
    @ResponsePayload
    public DeleteStudentByIdResponse deleteStudent(@RequestPayload DeleteStudentByIdRequest request) {
        ServiceStatus serviceStatus = new ServiceStatus();
        DeleteStudentByIdResponse response = new DeleteStudentByIdResponse();
        StudentDetails studentDetails = studentService.getStudentById(request.getId());
        if (null != studentDetails) {
            serviceStatus.setStatusCode("200");
            serviceStatus.setMessage("Success");
        }else {
            String msg = String.format("Details of student with id %s is not found", request.getId());
            ServiceStatus status = new ServiceStatus();
            status.setStatusCode("404");
            status.setMessage(msg);
            throw new ServiceFaultException(msg, status);
        }
        studentService.deleteStudentById(studentDetails);
        response.setServiceStatus(serviceStatus);
        return response;
    }

    @SneakyThrows
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateStudentByIdRequest")
    @ResponsePayload
    public UpdateStudentByIdResponse updateStudent(@RequestPayload UpdateStudentByIdRequest request) {
        ServiceStatus serviceStatus = new ServiceStatus();
        UpdateStudentByIdResponse response = new UpdateStudentByIdResponse();
        StudentDetails studentDetails = studentService.getStudentById(request.getId());
        if (null != studentDetails) {
            serviceStatus.setStatusCode("200");
            serviceStatus.setMessage("Success");
        }else {
            String msg = String.format("Details of student with id %s is not found", request.getId());
            ServiceStatus status = new ServiceStatus();
            status.setStatusCode("404");
            status.setMessage(msg);
            throw new ServiceFaultException(msg, status);
        }
        BeanUtils.copyProperties(request, studentDetails);
        studentService.UpdateStudentById(studentDetails);
        response.setServiceStatus(serviceStatus);
        return response;
    }
}
