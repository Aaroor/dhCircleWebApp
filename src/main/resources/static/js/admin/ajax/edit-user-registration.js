$(function() {
	// Initialize form validation on the registration form.
	// It has the name attribute "registration"
	$("form[name='edit-user-registration-form']").validate({
		// Specify validation rules
		rules : {
			// The key name on the left side is the name attribute
			// of an input field. Validation rules are defined
			// on the right side
			userName : "required",
			firstName : "required",
			lastName : "required",
			email : "required",
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
			phoneNumber : "Please enter your phone number"
		},
		// Make sure the form is submitted to the destination defined
		// in the "action" attribute of the form when valid
		submitHandler : function(form) {
			console.log("success");
			//createUser();
			editSystemUser();
		}
	});
	
	function editSystemUser() {
	    // Get form
	    var form = $('#edit-user-registration-form')[0];
	    var formData = new FormData(form);
	    for(var pair of formData.entries()){
	    	console.log(pair[0]+':'+pair[1]);
	    }
	
	 
		$.ajax({
			type:"POST",
			url:window.location.origin + "/api/admin/edit-system-user",
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
					//form.reset();
					swal("Good job!", "Successfully update a user!", "success");
					
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