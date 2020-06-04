$(document).ready(function() {
    "use strict";
    
    $("#course-category-datatable").DataTable({
    	dom: 'Blfrtip',
          buttons: [
          	{ 
          		extend:    'copyHtml5',
                  text:      '<i class="fa fa-files-o"></i>',
                  titleAttr: 'Copy',
                  exportOptions : {
                      modifier : {
                          // DataTables core
                          order : 'index', // 'current', 'applied',
                          //'index', 'original'
                          page : 'all', // 'all', 'current'
                          search : 'none' // 'none', 'applied', 'removed'
                      },
                      columns: [1,2,3]
                 }	
              },
          	{ 
            	  extend:    'csvHtml5',
                  text:      '<i class="fa fa-file-text-o"></i>',
                  titleAttr: 'CSV',
                  exportOptions : {
                      modifier : {
                          // DataTables core
                          order : 'index', // 'current', 'applied',
                          //'index', 'original'
                          page : 'all', // 'all', 'current'
                          search : 'none' // 'none', 'applied', 'removed'
                      },
                      columns: [1,2,3]
                 }
            },
          	{ 
            	extend:    'excelHtml5',
                text:      '<i class="fa fa-file-excel-o"></i>',
                titleAttr: 'Excel', 
          		exportOptions : {
                      modifier : {
                          // DataTables core
                          order : 'index', // 'current', 'applied',
                          //'index', 'original'
                          page : 'all', // 'all', 'current'
                          search : 'none' // 'none', 'applied', 'removed'
                      },
                      columns: [1,2,3]
                }	
          	},
          	{ 
          		extend:    'pdfHtml5',
                text:      '<i class="fa fa-file-pdf-o"></i>',
                titleAttr: 'PDF',
                title: 'Dhcircle User List',
                customize: function(doc) {
                    doc.styles.title = {
                      color: 'green',
                      alignment: 'left'
                    }

                  },
          		exportOptions : {
                      modifier : {
                          // DataTables core
                          order : 'index', // 'current', 'applied',
                          //'index', 'original'
                          page : 'all', // 'all', 'current'
                          search : 'none' // 'none', 'applied', 'removed'
                      },
                      columns: [1,2,3]
                  }	
          	},
          	{ 
          		extend: 'print',
          		text:      '<i class="fa fa-print" aria-hidden="true"></i>',
                titleAttr: 'Print',
                title: 'Dhcircle User List',
          		exportOptions : {
                      modifier : {
                          // DataTables core
                          order : 'index', // 'current', 'applied',
                          //'index', 'original'
                          page : 'all', // 'all', 'current'
                          search : 'none' // 'none', 'applied', 'removed'
                      },
                      columns: [1,2,3]
                  }	
          	}
          ],  
        language: {
            paginate: {
                previous: "<i class='mdi mdi-chevron-left'>",
                next: "<i class='mdi mdi-chevron-right'>"
            },
            info: "Showing users _START_ to _END_ of _TOTAL_",
            lengthMenu: 'Display <select class=\'custom-select custom-select-sm ml-1 mr-1\'><option value="5">5</option><option value="10">10</option><option value="20">20</option><option value="-1">All</option></select> users'
        },
        pageLength: 5,
        ajax: {
	        url: '/api/admin/all-course-category-list',
	        dataSrc: ''
	    },
        columns: [{
            orderable: !1,
            data:null,
            targets: 0,
            render: function(e, o, t, l) {
                return "display" === o && (e = '<div class="custom-control custom-checkbox"><input type="checkbox" class="custom-control-input dt-checkboxes"><label class="custom-control-label">&nbsp;</label></div>'), e
            },
            checkboxes: {
                selectRow: !0,
                selectAllRender: '<div class="custom-control custom-checkbox"><input type="checkbox" class="custom-control-input dt-checkboxes"><label class="custom-control-label">&nbsp;</label></div>'
            }
        },{
            orderable: !0,
            data: 'topicName'
        }, {
            orderable: !0,
            data: 'createdAt'
        }, {
            orderable: !0,
            data: 'updatedAt',
            render: function(data, type, full, meta) {
                var date = new Date(data);
                var month = date.getMonth() + 1;
                if(type == 'display'){
                	return (month.toString().length > 1 ? month : "0" + month) + "/" + date.getDate() + "/" + date.getFullYear()+" : "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
                }
                else{
                	return data;
                }
            }
        
        }, {
            orderable: !0,
            data: 'enabled',
            render: function ( data,type,row,meta){
            	console.log('enable : '+JSON.stringify(row));
            	if(type == 'display'){
	            	if(row.enabled){
	            		return "<input type='checkbox' id='user-active"+row.id+"' checked data-switch='bool'/>"
	            		+"<label for='user-active"+row.id+"' data-on-label='Yes' data-off-label='No'></label>";
	            		//return "<span class='badge badge-success'>Active</span>";
	            	}else{
	            		return "<input type='checkbox' id='user-active"+row.id+"' data-switch='bool'/>"
	            		+"<label for='user-active"+row.id+"' data-on-label='Yes' data-off-label='No'></label>";
	            	}
            	}else{
            		return data;
            	}
            }
        },
        {
        	orderable:!1,
        	data: 'id',
        	className:"table-action",
        	render: function ( data, type, row, meta ) {
        	      return "<a href='#' data-toggle='modal' data-target='#standard-modal_"+data+"'"
					+"class='action-icon'> <i class='mdi mdi-eye'></i></a> "
					+"<a href='/admin/edit-user/"+data+"' class='action-icon' id='"+data+"'>"
					+"<i class='mdi mdi-square-edit-outline'></i></a> "
					+"<a href='#' class='action-icon remove' id='"+data+"'>"
					+"<i class='mdi mdi-delete'></i></a>";
        	}
        }],
        select: {
            style: "multi"
        },
        order: [
            [1, "asc"]
        ],
        drawCallback: function() {
            $(".dataTables_paginate > .pagination").addClass("pagination-rounded")
        }
    });
    
  
});