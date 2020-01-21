# Bus-Reservation-System
TravelBookingSystem:
• Implemented a Spring MVC Hibernate project in which passengers’ can book a bus ticket based on the destination ,starting point ,departure date and the class in which they want to travel

• There are three roles in this application: Admin role, Guest role and the Customer role

• Guest Role: The User can view the information of the train details without signup or login using the guest role.

• Admin Role : Admin can add bus details based on the departure date, departure time , arrival date ,arrival time and travel class

• User Role: Once the user views bus detail’s , he can add it to his cart, proceed for payment .Once the user’s payment is done he can download his receipt using pdf download or send the receipt to his email using Send receipt to email option

Functionalities:
• The admin can add all the buses based on the departure date, departure day, arrival date, arrival time , price of ticket and travel class.

• Once the user searches for the starting point ,destination point, travel class and departure time. The list of all trains with the specified values can be viewed.

• Once the user wants to checkout , it checks for if he is a valid user or not

• If he is not a valid user, the user needs to sign up and then log in

• Once after logging in, when the user proceeds for payment, he enters all his payment details

• Once the payment is done , he can view his cart

• The billing statement can be downloaded as a pdf

• The billing statement can also be mailed to his email id.

• The admin can also view all the passengers list

• Once the payment is made, the number of seats in the train reduces and the next user can see the reduced number of seats

• Validations have been performed using Validators for train payment ticket, bus controller and the passenger who is travelling by train

• For adding to cart, Used AJAX for asynchronously calling the JSP page

• Once added to the cart, the user can also delete the item of the cart

• For the input fields, the validations have also been performed restricting its range of input values

Technologies:
The technologies used are: Spring MVC

Hibernate

AJAX

BOOTSTRAP

MySQL

JSP

MAVEN

JSON

CSS

JAVASCRIPT
