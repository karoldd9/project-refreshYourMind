package com.hibernate.classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name, surname, jobTitle, address;
    private int age;

    @Column(name="salary")
    private int salary;

    @Override
    public String toString() {
        return "Employee{" +
                "\n\tid=" + id +
                "\n\tname='" + name + '\'' +
                "\n\tsurname='" + surname + '\'' +
                "\n\tjobTitle='" + jobTitle + '\'' +
                "\n\taddress='" + address + '\'' +
                "\n\tage=" + age +
                "\n\tsalary=" + salary +
                "\n}";
    }
}
