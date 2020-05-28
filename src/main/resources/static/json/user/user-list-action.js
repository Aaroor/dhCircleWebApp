$(document).ready(function() {
    var table = $('#user-datatable').DataTable();
//    setInterval( function () {
//        table.ajax.reload();
//    }, 30000 );
     
    $('#user-datatable tbody').on('click', 'td a', function () {
        //var data = table.row( this ).data();
    	console.log(this.className)
    	if(this.className == 'action-icon remove'){
    		//alert(this.id);
    		swal({
    			  title: "Are you sure?",
    			  text: "Once deleted, you will not be able to recover this user!",
    			  icon: "warning",
    			  buttons: true,
    			  dangerMode: true,
    			})
    			.then((willDelete) => {
    			  if (willDelete) { 
    				  removeUser(this.id,this);
    			  } else {
    			    //swal("Your imaginary file is safe!");
    			  }
    			});
    	}
       // var data1 = table.column( this ).data();
       // console.log(data1);
       // alert( 'You clicked on '+data.id+'\'s row' );
    } );
    
    function removeUser(id,parent) {
	    // Get form
	    var formData = new FormData();
	    formData.append("id", id);
	    for(var pair of formData.entries()){
	    	console.log(pair[0]+':'+pair[1]);
	    }
	
	 
		$.ajax({
			type:"POST",
			url:window.location.origin + "/api/admin/remove-user",
			data:formData,
			cache:false,
			dataType:"json",
			contentType:false,
			processData:false,
			success:function(result) {
				if(result.status == "Done") {
					
					swal("Poof! This user record has been deleted!", {
	    			      icon: "success",
	    			    });
					 table
			            .row( $(parent).parents('tr') )
			            .remove()
			            .draw();
					
					//window.location.replace(window.location.origin+"/admin/user-list");
					//console.log("--success-- "+response);
				} else {
					console.log("--fail-- "+result);
					alert("update failure");
				}
			}
		});
	}
} );