$(function() {
	// Initialize form validation on the registration form.
	// It has the name attribute "registration"
	$("form[name='add-course-topic-form']").validate({
		// Specify validation rules
		rules : {
			// The key name on the left side is the name attribute
			// of an input field. Validation rules are defined
			// on the right side
			topicName : "required",
		},
		// Specify validation error messages
		messages : {
			topicName : "Please enter course topic",
		},
		// Make sure the form is submitted to the destination defined
		// in the "action" attribute of the form when valid
		submitHandler : function(form) {
			console.log("success");
			addCourseCategory();
		}
	});
	
	function addCourseCategory() {
	    // Get form
	    var form = $('#add-course-topic-form')[0];
	    var formData = new FormData(form);
	    for(var pair of formData.entries()){
	    	console.log(pair[0]+':'+pair[1]);
	    }
	
	 
		$.ajax({
			type:"POST",
			url:window.location.origin + "/api/admin/create-course-category",
			data:formData,
			cache:false,
			dataType:"json",
			contentType:false,
			processData:false,
			success:function(result) {
				if(result.status == "Done") {
					console.log("--success-- "+result);
					form.reset();
					swal("Good job!", "Successfully create course category!", "success");
					
					//window.location.replace(window.location.origin+"/admin/user-list");
					//console.log("--success-- "+response);
				} else {
					console.log("--fail-- "+result);
					swal("Failure !", result.message, "error");
				}
			},
			error: function(jqXHR, exception) {
				var error_message;
	            if (jqXHR.status === 0) {
	            	error_message ='Not connect.\n Verify Network.';
	            } else if (jqXHR.status == 404) {
	            	error_message='Requested page not found. [404]';
	            } else if (jqXHR.status == 500) {
	            	error_message='Internal Server Error [500].';
	            } else if (exception === 'parsererror') {
	            	error_message='Requested JSON parse failed.';
	            } else if (exception === 'timeout') {
	            	error_message='Time out error.';
	            } else if (exception === 'abort') {
	            	error_message='Ajax request aborted.';
	            } else {
	            	error_message='Uncaught Error.\n' + jqXHR.responseText;
	            }
	            swal("Error !", error_message, "error");
	        }
		});
	}
});