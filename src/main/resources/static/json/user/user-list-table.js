$(document).ready( function () {
	 $(document).on("change", 'input[type=checkbox]', function(event) { 
		 if(this.id.startsWith("user-active")){
	    		console.log(this.id);
	    		var isShow = false;
	    		if($(this).prop("checked") == true){isShow = true;}
	    		else{isShow = false;}
	    		userActive(this.id.substring(11),isShow);
	     }
	 });
	 
	 function userActive(userId,isShow){
	    	var formData = {
	    			id : userId,
	    			active : isShow
	    	}
	    	
	    	$.ajax({
	 	        type : "POST",
	 	        contentType : "application/json",
	 	        url : window.location.origin + "/api/admin/change-user-status",
	 	        data : JSON.stringify(formData),
	 	        dataType : 'json',
	 	        success : function(result) {
	 	          if(result.status == "Done"){
	 	        	  console.log("Success");
	 	        	var status;  
	 	        	if(isShow) {
	 	        		status = "Active";
	 	        	}else{
	 	        		status = "Inactive";
	 	        	}
	 	        	var addStatus = "<strong>Status :</strong> <span class='ml-2'>"+status+"</span>";
				    $("#user-status-"+userId).html(addStatus);
	 	        	swal("Good job!",result.message, "success");
	 	          }else{
	 	        	  alert("Error!")
	 	          }
	 	          console.log(result);
	 	        },
	 	        error : function(e) {
	 	          alert("Error!")
	 	          console.log("ERROR: ", e);
	 	        }
	 	  });
	    	
	    }
});