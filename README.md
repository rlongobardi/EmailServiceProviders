#This API service that accepts the necessary information and sends emails.

##The application should provide an abstraction between two different email service providers.
##If one of the services goes down, your service can quickly failover to a different provider
##without affecting your customers.
Email Providers:
Mailgun - Simple Send Documentation
SendGrid - Simple Send Documentation
Above services are free to try. You may choose alternative email providers that have API
integration. Your solution should cater for multiple email recipients, CCs and BCCs but there
is no need to support HTML email body types (plain text is OK)



##Build
Please execute this command : 'mvn clean install'
##Run Locally
mvn spring-boot:run    

-This service is currently deployed here on AWS : http://mailmanager-stm.us-east-1.elasticbeanstalk.com
-The main API via POST is /api/email/send
-There is another API that allows you to admin and switch dynamically which email provider between MainGun or SendGrid, 
 remember 1 is MailGun, 2 is SendGrid.  
To switch provider email you can use for example this call: 'api/admin/switcher?providerid=1'

