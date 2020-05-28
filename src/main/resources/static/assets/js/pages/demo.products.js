$(document).ready(function() {
    "use strict";
    console.log("hi 1");
    $("#user-datatable").DataTable({
    	dom: 'Bfrtip',
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
                      columns: [1,2,3,5]
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
                      columns: [1,2,3,5]
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
                      columns: [1,2,3,5]
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
                      columns: [1,2,3,5]
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
                      columns: [1,2,3,5]
                  }	
          	}
          ],  
        language: {
            paginate: {
                previous: "<i class='mdi mdi-chevron-left'>",
                next: "<i class='mdi mdi-chevron-right'>"
            },
            info: "Showing products _START_ to _END_ of _TOTAL_",
            lengthMenu: 'Display <select class=\'custom-select custom-select-sm ml-1 mr-1\'><option value="5">5</option><option value="10">10</option><option value="20">20</option><option value="-1">All</option></select> users'
        },
        pageLength: 5,
        ajax: {
	        url: '/api/admin/all-user-list',
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
            data: 'userName'
        }, {
            orderable: !0,
            data: 'phoneNumber'
        }, {
            orderable: !0,
            data: 'email'
        }, {
            orderable: !0,
            data: null,
            render: function ( data,type,row,meta){
            	if(data.active){
            		return "<input type='checkbox' id='user-active"+data.id+"' checked data-switch='bool'/>"
            		+"<label for='user-active"+data.id+"' data-on-label='On' data-off-label='Off'></label>";
            		//return "<span class='badge badge-success'>Active</span>";
            	}else{
            		return "<input type='checkbox' id='user-active"+data.id+"' data-switch='bool'/>"
            		+"<label for='user-active"+data.id+"' data-on-label='On' data-off-label='Off'></label>";
            	}
            }
        },
        {
            orderable: !0,
            data: 'roles',
            render: function ( data,type,row,meta){
            	return data[0].role;
            }
        },{
        	orderable:!1,
        	data: 'id',
        	className:"table-action",
        	render: function ( data, type, row, meta ) {
        	      return "<a href='/home/"+data+"'"
					+"class='action-icon'> <i class='mdi mdi-eye'></i></a> "
					+"<a href='javascript:void(0);' class='action-icon'>"
					+"<i class='mdi mdi-square-edit-outline'></i></a> "
					+"<a href='javascript:void(0);' class='action-icon'> "
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