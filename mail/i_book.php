<?php
// Check for empty fields
if(empty($_POST['bookName'])      ||
   empty($_POST['bookFirstname'])     ||
   empty($_POST['bookPhone'])     ||
   empty($_POST['bookArrival'])   ||
   empty($_POST['bookDeparture'])       ||
   empty($_POST['numberRoom'])     ||
   empty($_POST['bookRoom'])     ||
   !filter_var($_POST['bookEmail'],FILTER_VALIDATE_EMAIL))
   {
	echo "No arguments Provided!";
	return false;
   }
	
$name = $_POST['bookName'];
$firstname = $_POST['bookFirstname'];
$phone = $_POST['bookPhone'];
$arrival = $_POST['bookArrival'];
$departure = $_POST['bookDeparture'];
$email_address = $_POST['bookEmail'];
$room = $_POST['bookRoom'];
$numberRoom = $_POST['numberRoom'];
	
// Create the email and send the message
$to = 'residencedeshautsdemenaye@outlook.com'; // Add your email address inbetween the '' replacing yourname@yourdomain.com - This is where the form will send a message to.
$email_subject = "Reservation de chambre RHM de:  $name";
$email_body = "Vous avez reçu une demande de réservation de la part d'un client.\n\n"."Voici les détails:\n \n\nNom: $name \n Prénom: $firstname\n Email: $email_address\n Phone: $phone\n Type de chambre: $room\n Quantité: $numberRoom\n Date d'arrivé: $arrival\n Date de départ: $departure";
$headers = "Réservation de chambre du site web\n"; // This is the email address the generated message will be from. We recommend using something like noreply@yourdomain.com.
$headers .= "Reply-To: $email_address";	
mail($to,$email_subject,$email_body,$headers);
return true;			
?>