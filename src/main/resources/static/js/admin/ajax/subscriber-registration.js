$(function() {
	// Initialize form validation on the registration form.
	// It has the name attribute "registration"
	$("form[name='subscriber-registration-form']").validate({
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
            }
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
			    }
		},
		// Make sure the form is submitted to the destination defined
		// in the "action" attribute of the form when valid
		submitHandler : function(form) {
			console.log("success");
			createSubscriber();
			// updateUserDetails();
		}
	});
	
	function createSubscriber() {
	    // Get form
	    var form = $('#subscriber-registration-form')[0];
	    var formData = new FormData(form);
	    for(var pair of formData.entries()){
	    	console.log(pair[0]+':'+pair[1]);
	    }
	
	 
		$.ajax({
			type:"POST",
			url:window.location.origin + "/api/admin/create-subscriber",
			data:formData,
			cache:false,
			dataType:"json",
			contentType:false,
			processData:false,
			success:function(result) {
				if(result.status == "Done") {
					//var response = JSON.stringify(result);
//					var successMsg = "<div class='alert alert-success' role='alert'>"+result.message+"</div>";
//					$("#user-create-success-msg").html(successMsg);
					swal("Good job!", result.message, "success");
					//window.location.replace(window.location.origin+"/admin/user-list");
					//console.log("--success-- "+response);
				} else {
					console.log("--fail-- "+result);
//					var failMsg = "<div class='alert alert-danger' role='alert'>"+result.message+"</div>";
//					$("#user-create-success-msg").html(failMsg);
					swal("Faile operation!", result.message, "error");
					//alert("update failure");
				}
			}
		});
	}
});