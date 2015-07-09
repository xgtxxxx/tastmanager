Ext.define('Task.view.task.Edit', {
	extend : 'Ext.window.Window',
	alias : 'widget.taskedit',

	requires : [ 'Ext.form.Panel' ],
	modal : true,
	title : 'Edit Task',
	layout : 'fit',
	submitUrl : '',
	autoShow : true,
	width : 600,
	buttonAlign : 'center',
	initComponent : function() {
		Ext.apply(Ext.form.field.VTypes, {
    		checkdate : function(val, field) {
                if(new Date(val)>new Date()){
                	return false;
                }
                return true;
            },
            checkdateText: 'Date can not after today!'
    	});
		this.items = [ {
			xtype : 'form',
			url : this.submitUrl,
			padding : '5 5 0 5',
			border : false,
			style : 'background-color: #fff;',
			layout : 'column',
			defaults : {
				border : false
			},
			items : [
					{
						columnWidth : .5,
						layout : 'form',
						items : [
								{
									xtype : 'hidden',
									name : 'id'

								},
								{
									xtype : 'hidden',
									id : '_teamId',
									name : 'teamName'

								},
								{
									xtype : 'textfield',
									name : 'taskId',
									allowBlank : false,
									fieldLabel : 'Task ID'
								},
								{
									xtype : 'combo',
									name : 'releaseVersion',
									fieldLabel : 'Release Version',
									displayField : 'name',
									valueField : 'name',
									minChars : 1,
									allowBlank : false,
									hiddenName : 'releaseVersion',
									store :  Ext.create('Ext.data.Store', {
										 fields: ['id','name'],
							    	     proxy: {
							    	         type: 'ajax',
							    	         url: contextPath+'/version/getActiveVersion',
							    	         reader: {
							    	             type: 'json',
							    	             rootProperty: 'recordlist'
							    	         }
							    	     },
							    	     autoLoad: true
							    	 }),
									queryMode : 'remote'
								}, {
									xtype : 'datefield',
									name : 'assignDate',
									vtype : 'checkdate',
									allowBlank : false,
									format : 'Y-m-d',
									fieldLabel : 'Assigned Date'
								}, {
									xtype : 'combo',
									name : 'team',
									id   : '_team_combo',
									fieldLabel : 'Team',
									displayField : 'name',
									editable : false,
									valueField : 'id',
									allowBlank : false,
									hiddenName : 'team',
									store : Ext.create('Task.store.Team', {
										proxy : {
											type : 'ajax',
											url : contextPath+"/team/getAll",
											extraParams : {
												isOwn : "true"
											},
											reader : {
												type : 'json',
												rootProperty : 'recordlist',
												totalProperty : 'totalcount'
											}
										},
										listeners : {
											'load' : function(s, records, successful, eOpts ){
												if(records.length>0)
												Ext.getCmp("_team_combo").setValue(records[0].data.id);
											}
										}
									}),
									listeners : {
										'change' : function(f,n,o){
											Ext.getCmp("_teamId").setValue(f.getRawValue());
										}
									},
									queryMode : 'remote'
								}]
					}, {
						columnWidth : .5,
						layout : 'form',
						items : [ {
							xtype : 'combo',
							name : 'status',
							fieldLabel : 'Status',
							allowBlank : false,
							editable : false,
							displayField : 'description',
							valueField : 'id',
							hiddenName : 'status',
							store : 'Status',
							queryMode : 'remote',
							listeners : {
								'change' : function(t,n,o){
									if(n==1||n==2){
										t.up("form").items.items[1].items.items[1].setValue(100);
									}
									if(n==1){
										t.up("form").items.items[1].items.items[2].setValue(new Date());
									}
								}
							}
						}, {
							xtype : 'numberfield',
							name : 'progress',
							allowBlank : false,
							value      : 0,
							fieldLabel : 'Progress(%)'
						}, {
							xtype : 'datefield',
							name : 'fixedDate',
							format : 'Y-m-d',
							vtype : 'checkdate',
							fieldLabel : 'Fixed Date'
						} ]
					}, {
						columnWidth : 1,
						layout : 'form',
						items : [ {
							xtype : 'textareafield',
							name : 'description',
							grow : true,
							allowBlank : false,
							fieldLabel : 'Title<br/>(Description)',
							anchor : '100%'
						}, {
							xtype : 'textareafield',
							name : 'commentsAndQuestions',
							grow : true,
							fieldLabel : 'Comments/<br/>Questions',
							anchor : '100%'
						}, {
							xtype : 'textareafield',
							name : 'pendingIssues',
							fieldLabel : 'Pending Issues',
							anchor : '100%'
						}, {
							xtype : 'textareafield',
							name : 'feedback',
							hidden : true,
							fieldLabel : 'Feedback',
							anchor : '100%'
						}]
					} ]
		} ];

		this.buttons = [ {
			text : 'Save',
			action : 'save'
		}, {
			text : 'Cancel',
			scope : this,
			handler : this.close
		} ];

		this.callParent(arguments);
	},
	clearId : function(){
		this.items.items[0].items.items[0].items.items[0].reset();
	}
});
