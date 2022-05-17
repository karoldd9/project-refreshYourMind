package com.hibernate;

import com.hibernate.classes.Device;
import com.hibernate.classes.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class BasicDataSave {
    public static void main(String[] args) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Employee employee = new Employee();
        //employee.setId(1);
        employee.setName("Adam");
        employee.setSurname("Kowalski");
        employee.setJobTitle("Programmer");
        employee.setSalary(100000);

        Device device = new Device();
        device.setDeviceName("Computer");
        device.setCost(1500.50);

        session.persist(employee);
        session.persist(device);
        transaction.commit();


        System.out.println("Employee and device saved");


        Employee employeeCrud = new Employee();
        employeeCrud.setName("Java");
        employeeCrud.setSurname("17");
        employeeCrud.setJobTitle("Java Programmer");
        employeeCrud.setAge(101);
        employeeCrud.setAddress("Java Street 17");
        employeeCrud.setSalary(16500);


        sessionFactory.close();
        session.close();
    }
}
