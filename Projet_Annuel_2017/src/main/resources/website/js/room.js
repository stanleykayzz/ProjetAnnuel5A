$(document).ready(function(){
  init_reservation();
});

function init_reservation(){
  document.getElementById("bookingForm").style.display = "none";
  $("#showBookingButton").unbind("click");
  $("#showBookingButton").click(function(){
    document.getElementById("bookingForm").style.display = "block";
    document.getElementById("showBookingButton").parentElement.removeChild(document.getElementById("showBookingButton"));
  });
}
