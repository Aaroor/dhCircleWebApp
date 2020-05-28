$(function() {
	// Initialize form validation on the registration form.
	// It has the name attribute "registration"
	$("form[name='user-login']").validate({
		// Specify validation rules
		rules : {
			// The key name on the left side is the name attribute
			// of an input field. Validation rules are defined
			// on the right side
			user_name : "required",
			password : "required"
		},
		// Specify validation error messages
		messages : {
			user_name : "Please enter user name",
			password : "Please enter password"
		},
		// Make sure the form is submitted to the destination defined
		// in the "action" attribute of the form when valid
		submitHandler : function(form) {
			form.submit();
		}
	});
});