package com.test.usermanagement.entity;


import javax.persistence.*;



    @Entity
    @Table(name = "Users_info")
    public class UserEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "FirstName")
        private String firstName;

        @Column(name = "LastName")
        private String lastName;

        @Column(name = "EmailId")
        private String emailId;



        @Column(name = "Mobile")
        private String mobile;
        @Column(name = "Password")
        private String password;
        @Column(name = "Job_Role")
        private String jobRole;
        @Column(name = "user_name")
        private String userName;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }



        public String resultMessage;

        public String getResultMessage() {
            return resultMessage;
        }

        public void setResultMessage(String resultMessage) {
            this.resultMessage = resultMessage;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmailId() {
            return emailId;
        }

        public void setEmailId(String emailId) {
            this.emailId = emailId;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getJobRole() {
            return jobRole;
        }

        public void setJobRole(String jobRole) {
            this.jobRole = jobRole;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }



