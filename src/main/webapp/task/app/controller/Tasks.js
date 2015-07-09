Ext.define('Task.controller.Tasks', {
    extend: 'Ext.app.Controller',

    stores: [
        'Tasks@Task.store',
        'Status@Task.store',
        'Team@Task.store'
    ],

    models: [
        'Task@Task.model',
        'Status@Task.model',
        'Team@Task.model'
    ],

    views: [
        'Edit@Task.view.task',
        'Search@Task.view.task',
        'SearchBar@Task.view.task',
        'List@Task.view.task',
        'TaskPanel@Task.view.task',
        'TaskTab@Task.view.task',
        'Preview@Task.view.task'
    ],

    init: function() {
        this.control({
//            'taskpanel > tasklist': {
//                itemdblclick: this.editTask
//            },
            'taskedit button[action=save]': {
                click: this.updateTask
            },
            'searchbar button[action=search]': {
                click: this.searchTask
            },
            'searchbar button[action=add]': {
                click: this.addTask
            },
            'searchbar button[action=listMonthly]': {
                click: this.showMonthlyReport
            },
            'searchbar button[action=export]': {
                click: this.exportTask
            },
            'searchbar button[action=send]': {
                click: this._send
            }
        });
    },

    editTask: function(view, record) {
    	var submittime = record.get('submitTime'),
    		date = Ext.Date.parse(record.get('submitTime'), "Y-m-d H:i:s", true),
    		now = new Date(),
    		grid = view.grid;

    	if (grid.taskType === 'own' && Ext.Date.format(date, 'Y-m-d') == Ext.Date.format(now, 'Y-m-d')) {
    		var edit = Ext.create('Task.view.task.Edit', {
    			submitUrl : contextPath + '/task/updateTask',
    			_grid : grid,
    			renderTo : grid.ownerCt.getEl()
    		}).show();
    		
    		edit.down('form').loadRecord(record);
    	}
    },

    updateTask: function(button) {
        var win    = button.up('window'),
            form   = win.down('form').getForm();
            
       	if (form.isValid()) {
       		form.submit({
                success: function(form, action) {
                   Ext.Msg.alert('Success', action.result ? action.result.message : 'Success!');
                   win.close();
                   win._grid.getStore().reload();
                },
                failure: function(form, action) {
                    Ext.Msg.alert('Failed', action.result ? action.result.message : 'No response');
                }
            });
       	}
    },
    
    addTask : function(button) {
    	if (isDev) {
    		var	grid = button.up("searchbar").getParent();
    		Ext.create('Task.view.task.Edit', {
    			title : 'Add Task',
    			_grid : grid,
    			renderTo : grid.ownerCt.getEl(),
    			submitUrl : contextPath + '/task/addTask'
    		}).show();
    	}
    },
    
    searchTask : function(button) {
    	button.up("searchbar").doSearch();
    },
    
    showMonthlyReport : function(button){
    	Ext.Loader.setConfig({  
		   enabled: true,  
		   paths : { 
		      'Monthly' : 'monthly'  
		   }  
		});  
		Ext.require(["Monthly.ReportWin"]); 
		Ext.create("Monthly.ReportWin",{}).show();
    },
    
    exportTask : function(button){
		var win = Ext.create("Ext.window.Window",{
			title : 'Choose the type to export',
			width : 300,
			height : 120,
			modal  : true,
			items  : [{
				xtype : 'form',
				border: false,
				items : {
					margin : '10 0 25 5	',
	    			allowBlank : false,
	    			border: false,
	    			name : 'type',
	    			hiddenName : 'type',
	    			fieldLabel : 'Export Type',
	    			xtype : 'combo',
	    			editable : false,
	    			displayField : 'field1',
	    			valueField : 'field2',
	    			store : [ [ 'pdf', 'PDF' ],[ 'excel', 'EXCEL' ], [ 'html', 'HTML' ]],
	    			value : 'pdf',
	    			queryMode : 'local'
				}
			}],
			buttonAlign : 'center',
			buttons : [{
				text : 'Do Export',
				handler : function(){
					var values = button.up("searchbar").getParams();
					var type = win.down("combo").getValue();
					var taskId = values.taskId;
					var team = values.team;
					var status = values.status;
					var owner = values.owner;
					var startDate = values.startDate;
					var endDate = values.endDate;
					var submitDate = values.submitDate;
					var isOwn = values.isOwn;
					var url=contextPath+"/task/exportTask?type="+type+"&taskId="+taskId+"&team="+team+"&status="+status+"&owner="+owner+"&startDate="+startDate+"&endDate="+endDate+"&submitDate="+submitDate+"&isOwn="+isOwn;
					if(type=="html"){
						window.open(url);
					}else{
						window.location.href=url;
					}
					win.close();
				}
			},{
				text : 'Cancel',
				handler : function(){
					win.close();
				}
			}]
		});
		win.show();
    },
    _send : function(button){
    	var bar = button.up("searchbar");
    	var	grid = bar.getParent();
    	if(bar.getOwn()){
    		Ext.create("Task.view.task.Preview",{
    			url : contextPath+"/email/sendPerEmail"
    		}).show();
    	}else{
    		var team = bar.getTeam();
    		var params = {
    				teamId : team
    		};
    		Ext.create("Task.view.task.Preview",{
    			url : contextPath+"/email/sendTeamEmail",
    			params : params
    		}).show();
    	}
    },
    doSend :function(url,params,p){
    	var myMask = new Ext.LoadMask({
    	    msg    : 'Please wait...',
    	    target : p
    	});
    	Ext.Msg.confirm("notice","are you sure?",function(v){
    		if(v=='yes'){
    			myMask.show();
    			Ext.Ajax.request({
    				url: url, 
    				params :params,
    				success: function(response, option) {
    					var obj = Ext.util.JSON.decode(response.responseText);
    					myMask.hide();
    					Ext.Msg.alert("notice",obj.message);
    				},
    				failure: function() {
    					myMask.hide();
    					Ext.Msg.alert("notice","error!");	
    				}
    			});
    		}
    	});
    }
});
