$(function() {
	// Initialize form validation on the registration form.
	// It has the name attribute "registration"
	$("form[name='user-registration-form']").validate({
		// Specify validation rules
		rules : {
			// The key name on the left side is the name attribute
			// of an input field. Validation rules are defined
			// on the right side
			userName : "required",
			firstName : "required",
			lastName : "required",
			email : "required",
			phoneNumber : "required",
			password : "required",
			confirmPassword : {
				required: true,
                equalTo: "#password"
            },
			userRole : "required"
		},
		// Specify validation error messages
		messages : {
			userName : "Please enter user name",
			email : "Please enter email",
			password : "Please enter password",
			lastName : "Please enter last name",
			userName : "Please enter user name",
			firstName : "Please enter your first name",
			lastName : "Please enter your last name",
			email : "Please enter your email",
			phoneNumber : "Please enter your phone number",
			password : "Please enter your password",
			confirmPassword : {
			      required: "Please enter confirm password",
			      equalTo: "Enter Confirm Password Same as Password"
			    },
			userRole : "Please seelct proper user role"	
		},
		// Make sure the form is submitted to the destination defined
		// in the "action" attribute of the form when valid
		submitHandler : function(form) {
			console.log("success");
			createUser();
			// updateUserDetails();
		}
	});
	
	function createUser() {
	    // Get form
	    var form = $('#user-registration-form')[0];
	    var formData = new FormData(form);
	    for(var pair of formData.entries()){
	    	console.log(pair[0]+':'+pair[1]);
	    }
	
	 
		$.ajax({
			type:"POST",
			url:window.location.origin + "/api/admin/create-user",
			data:formData,
			cache:false,
			dataType:"json",
			contentType:false,
			processData:false,
			success:function(result) {
				if(result.status == "Done") {
					console.log("--success-- "+result);
					//var response = JSON.stringify(result);
					//var successMsg = "<div class='alert alert-success' role='alert'>Successfully created a user</div>";
					//$("#user-create-success-msg").html(successMsg);
					form.reset();
					swal("Good job!", "Successfully created a user!", "success");
					
					//window.location.replace(window.location.origin+"/admin/user-list");
					//console.log("--success-- "+response);
				} else {
					console.log("--fail-- "+result);
					alert("update failure");
				}
			}
		});
	}
});